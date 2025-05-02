package com.songspasssta.reportservice.domain.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songspasssta.reportservice.domain.Bookmark;
import com.songspasssta.reportservice.domain.QBookmark;
import com.songspasssta.reportservice.dto.response.BookmarkReports;
import com.songspasssta.reportservice.dto.response.QBookmarkReports;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.songspasssta.reportservice.domain.QBookmark.bookmark;
import static com.songspasssta.reportservice.domain.QReport.report;


@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 방법1. report.bookmarks.size()로 북마크 개수 가져오기 (서브쿼리 사용)
    @Override
    public List<BookmarkReports> findAllByMemberIdAndBookmarked(Long memberId) {
        return queryFactory
                .select(new QBookmarkReports(
                        report.id,
                        report.reportImgUrl,
                        report.reportType.stringValue(),
                        report.roadAddr,
                        report.bookmarks.size()
                ))
                .from(bookmark)
                .join(bookmark.report, report)
                .where(
                        bookmark.memberId.eq(memberId),
                        bookmark.bookmarked.isTrue()
                )
                .fetch();
    }

    // 방법2. inner join + group by를 사용해서 북마크 개수를 가져올 수도 있음

    @Override
    public List<BookmarkReports> findAllByMemberIdAndBookmarked_second(Long memberId) {
        QBookmark countBookmark = new QBookmark("countBookmark");

        return queryFactory
                .select(new QBookmarkReports(
                        report.id,
                        report.reportImgUrl,
                        report.reportType.stringValue(),
                        report.roadAddr,
                        countBookmark.count().intValue()
                ))
                .from(bookmark)
                .join(bookmark.report, report)
                .leftJoin(countBookmark).on(
                        countBookmark.report.id.eq(report.id)
                )
                .where(
                        bookmark.memberId.eq(memberId),
                        bookmark.bookmarked.isTrue()
                )
                .groupBy(report.id, report.reportImgUrl, report.reportType, report.roadAddr)
                .fetch();
    }
}
