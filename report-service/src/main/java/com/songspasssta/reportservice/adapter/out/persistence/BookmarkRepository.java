package com.songspasssta.reportservice.adapter.out.persistence;

import com.songspasssta.reportservice.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>,
        BookmarkRepositoryCustom {

    boolean existsByReportIdAndMemberIdAndBookmarkedTrue(Long reportId, Long memberId);

    @Query("SELECT b FROM Bookmark b WHERE b.report.id = :reportId AND b.memberId = :memberId AND b.bookmarked = true")
    Optional<Bookmark> findByReportIdAndMemberId(@Param("reportId") Long reportId, @Param("memberId") Long memberId);

    void deleteAllByReportId(Long reportId);

    void deleteByMemberId(Long memberId);
}
