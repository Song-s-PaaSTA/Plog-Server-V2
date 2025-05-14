package com.songspasssta.reportservice.adapter.out.persistence;

import com.songspasssta.common.PersistenceAdapter;
import com.songspasssta.reportservice.application.port.out.BookmarkRepositoryPort;
import com.songspasssta.reportservice.domain.Bookmark;
import com.songspasssta.reportservice.dto.response.BookmarkReports;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class BookmarkRepositoryAdapter implements BookmarkRepositoryPort {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public boolean existsByReportIdAndMemberIdAndBookmarkedTrue(Long reportId, Long memberId) {
        return bookmarkRepository.existsByReportIdAndMemberIdAndBookmarkedTrue(reportId, memberId);
    }

    @Override
    public Optional<Bookmark> findByReportIdAndMemberId(Long reportId, Long memberId) {
        return bookmarkRepository.findByReportIdAndMemberId(reportId, memberId);
    }

    @Override
    public void deleteAllByReportId(Long reportId) {
        bookmarkRepository.deleteAllByReportId(reportId);
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        bookmarkRepository.deleteByMemberId(memberId);
    }

    @Override
    public List<BookmarkReports> findAllByMemberIdAndBookmarked(Long memberId) {
        return bookmarkRepository.findAllByMemberIdAndBookmarked(memberId);
    }

    @Override
    public void save(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }
}
