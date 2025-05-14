package com.songspasssta.reportservice.application.port.in;

import com.songspasssta.reportservice.dto.response.BookmarkedReportsResponse;

public interface BookmarkUseCase {
    BookmarkedReportsResponse findMyBookmarks(Long memberId);

    String toggleBookmark(Long reportId, Long memberId);
}

