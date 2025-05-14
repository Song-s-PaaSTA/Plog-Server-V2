package com.songspasssta.reportservice.application.port.out;

import com.songspasssta.reportservice.domain.Bookmark;
import com.songspasssta.reportservice.dto.response.BookmarkReports;


import java.util.List;
import java.util.Optional;

public interface BookmarkRepositoryPort {
    boolean existsByReportIdAndMemberIdAndBookmarkedTrue(Long reportId, Long memberId);

    Optional<Bookmark> findByReportIdAndMemberId(Long reportId, Long memberId);

    void deleteAllByReportId(Long reportId);

    void deleteByMemberId(Long memberId);

    List<BookmarkReports> findAllByMemberIdAndBookmarked(Long memberId);

    void save(Bookmark bookmark);
}
