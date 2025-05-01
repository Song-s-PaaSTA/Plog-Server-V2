package com.songspasssta.reportservice.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songspasssta.reportservice.dto.response.BookmarkReports;
import com.songspasssta.reportservice.dto.response.QBookmarkReports;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.songspasssta.reportservice.domain.QBookmark.bookmark;


@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BookmarkReports> findAllByMemberIdAndBookmarked(Long memberId) {
        return queryFactory
                .select(new QBookmarkReports(
                        bookmark.report.id,
                        bookmark.report.reportImgUrl,
                        bookmark.report.reportType.stringValue(),
                        bookmark.report.roadAddr,
                        bookmark.report.bookmarks.size()
                ))
                .from(bookmark)
                .where(bookmark.memberId.eq(memberId)
                        .and(bookmark.bookmarked.isTrue()))
                .fetch();
    }
}
