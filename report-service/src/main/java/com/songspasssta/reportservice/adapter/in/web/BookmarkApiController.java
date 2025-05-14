package com.songspasssta.reportservice.adapter.in.web;

import com.songspasssta.common.response.SuccessResponse;
import com.songspasssta.reportservice.application.port.in.BookmarkUseCase;
import com.songspasssta.reportservice.dto.response.BookmarkedReportsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.songspasssta.common.auth.GatewayConstants.GATEWAY_AUTH_HEADER;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reports")
public class BookmarkApiController {
    private final BookmarkUseCase bookmarkUseCase;

    /**
     * 북마크한 신고글 조회
     */
    @GetMapping("/bookmarks/mine")
    public ResponseEntity<SuccessResponse<BookmarkedReportsResponse>> findMyBookmarks(@RequestHeader(GATEWAY_AUTH_HEADER) Long memberId) {
        BookmarkedReportsResponse response = bookmarkUseCase.findMyBookmarks(memberId);
        return ResponseEntity.ok().body(SuccessResponse.of(response));
    }

    /**
     * 북마크 토글
     */
    @PostMapping("/{reportId}/bookmarks")
    public ResponseEntity<SuccessResponse<String>> toggleBookmark(@PathVariable Long reportId,
                                                  @RequestHeader(GATEWAY_AUTH_HEADER) Long memberId) {
        String responseMessage = bookmarkUseCase.toggleBookmark(reportId, memberId);
        return ResponseEntity.ok().body(SuccessResponse.of(responseMessage));
    }
}
