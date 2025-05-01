package com.songspasssta.reportservice.domain.repository;

import com.songspasssta.reportservice.domain.Bookmark;
import com.songspasssta.reportservice.dto.response.BookmarkReports;

import java.util.List;

public interface BookmarkRepositoryCustom {
    List<BookmarkReports> findAllByMemberIdAndBookmarked(Long memberId);
}
