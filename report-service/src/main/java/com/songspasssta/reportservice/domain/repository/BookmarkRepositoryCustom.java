package com.songspasssta.reportservice.domain.repository;

import com.songspasssta.reportservice.dto.response.BookmarkReports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookmarkRepositoryCustom {
    List<BookmarkReports> findAllByMemberIdAndBookmarked(Long memberId);

    List<BookmarkReports> findAllByMemberIdAndBookmarked_second(Long memberId);
}
