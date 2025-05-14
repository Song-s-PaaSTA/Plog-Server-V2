package com.songspasssta.reportservice.adapter.out.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songspasssta.reportservice.domain.Report;
import com.songspasssta.reportservice.domain.type.RegionType;
import com.songspasssta.reportservice.domain.type.ReportType;
import com.songspasssta.reportservice.dto.response.QReportList;
import com.songspasssta.reportservice.dto.response.ReportList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static com.songspasssta.reportservice.domain.QBookmark.bookmark;
import static com.songspasssta.reportservice.domain.QReport.report;

@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReportList> findReportsWithFilter(Long memberId,
                                                  List<RegionType> regionTypes,
                                                  List<ReportType> reportTypes,
                                                  String sort,
                                                  Pageable pageable) {

        // 신고글 북마크 수
        JPQLQuery<Long> bookmarkCountExpr = select(bookmark.count())
                .from(bookmark)
                .where(
                        bookmark.report.eq(report),
                        bookmark.bookmarked.isTrue()
                );

        // 사용자가 신고글에 북마크를 했는지 여부
        BooleanExpression bookmarkedByUser = selectOne()
                .from(bookmark)
                .where(
                        bookmark.report.eq(report),
                        bookmark.memberId.eq(memberId),
                        bookmark.bookmarked.isTrue()
                ).exists();

        List<ReportList> content = queryFactory
                .select(new QReportList(
                        report.id,
                        report.reportImgUrl,
                        report.reportType.stringValue(),
                        report.roadAddr,
                        bookmarkCountExpr,
                        bookmarkedByUser
                ))
                .from(report)
                .leftJoin(bookmark).on(bookmark.report.eq(report).and(bookmark.bookmarked.isTrue()))
                .where(
                        regionTypeIn(regionTypes),
                        reportTypeIn(reportTypes)
                )
                .groupBy(report.id)
                .orderBy("popularity".equals(sort)
                        ? bookmark.id.count().desc()
                        : report.createdAt.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Report> countQuery = queryFactory
                .select(report)
                .from(report)
                .where(
                        regionTypeIn(regionTypes),
                        reportTypeIn(reportTypes)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery :: fetchCount);
    }

    private BooleanExpression regionTypeIn(List<RegionType> regionTypes) {
        if (regionTypes == null || regionTypes.isEmpty()) {
            return null;
        }
        return report.regionType.in(regionTypes);
    }

    private BooleanExpression reportTypeIn(List<ReportType> reportTypes) {
        if (reportTypes == null || reportTypes.isEmpty()) {
            return null;
        }
        return report.reportType.in(reportTypes);
    }
}
