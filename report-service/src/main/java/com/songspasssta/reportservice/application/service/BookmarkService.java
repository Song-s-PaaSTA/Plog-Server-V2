package com.songspasssta.reportservice.application.service;

import com.songspasssta.reportservice.application.port.in.BookmarkUseCase;
import com.songspasssta.reportservice.application.port.out.BookmarkRepositoryPort;
import com.songspasssta.reportservice.application.port.out.ReportRepositoryPort;
import com.songspasssta.reportservice.domain.Bookmark;
import com.songspasssta.reportservice.domain.Report;
import com.songspasssta.reportservice.dto.response.BookmarkReports;
import com.songspasssta.reportservice.dto.response.BookmarkedReportsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.songspasssta.reportservice.domain.Bookmark.createBookmark;

/**
 * 북마크 조회, 토글 서비스
 */
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookmarkService implements BookmarkUseCase {
    private final BookmarkRepositoryPort bookmarkRepositoryPort;
    private final ReportRepositoryPort reportRepositoryPort;

    /**
     * 특정 사용자가 북마크한 신고글 목록 조회
     */

    @Override
    public BookmarkedReportsResponse findMyBookmarks(Long memberId) {
        List<BookmarkReports> bookmarks = bookmarkRepositoryPort.findAllByMemberIdAndBookmarked(memberId);

        log.info("북마크 조회 - MemberId: {}, 북마크 개수: {}", memberId, bookmarks.size());

        return new BookmarkedReportsResponse(bookmarks);
    }

    /**
     * 북마크 토글 (북마크가 없으면 생성, 있으면 해제)
     */

    @Override
    public String toggleBookmark(Long reportId, Long memberId) {
        Report report = reportRepositoryPort.findById(reportId);
        return bookmarkRepositoryPort.findByReportIdAndMemberId(reportId, memberId)
                .map(bookmark -> toggleExistingBookmark(bookmark, reportId))
                .orElseGet(() -> createNewBookmark(memberId, report, reportId));
    }

    /**
     * 기존 북마크 상태 토글
     */
    private String toggleExistingBookmark(Bookmark bookmark, Long reportId) {
        bookmark.toggleBookmarkStatus(!bookmark.getBookmarked());
        bookmarkRepositoryPort.save(bookmark);
        return bookmark.getBookmarked()
                ? "ID가 " + reportId + "인 신고글의 북마크가 등록되었습니다."
                : "ID가 " + reportId + "인 신고글의 북마크가 해제되었습니다."; // 변경된 상태에 따라 메시지 반환
    }

    /**
     * 북마크 엔티티 생성
     */
    private String createNewBookmark(Long memberId, Report report, Long reportId) {
        Bookmark newBookmark = createBookmark(memberId, report, true);
        bookmarkRepositoryPort.save(newBookmark);
        return "ID가 " + reportId + "인 신고글의 북마크가 등록되었습니다.";
    }
}