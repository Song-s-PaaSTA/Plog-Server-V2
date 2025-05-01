package com.songspasssta.reportservice.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkReports {
    private Long reportId;
    private String reportImgUrl;
    private String reportType;
    private String roadAddr;
    private int bookmarkCount;

    @QueryProjection
    public BookmarkReports(Long reportId, String reportImgUrl, String reportType, String roadAddr, int bookmarkCount) {
        this.reportId = reportId;
        this.reportImgUrl = reportImgUrl;
        this.reportType = reportType;
        this.roadAddr = roadAddr;
        this.bookmarkCount = bookmarkCount;
    }
}
