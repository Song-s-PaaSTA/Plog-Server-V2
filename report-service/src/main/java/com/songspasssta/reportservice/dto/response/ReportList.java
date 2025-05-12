package com.songspasssta.reportservice.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportList {
    private Long reportId;
    private String reportImgUrl;
    private String reportStatus;
    private String roadAddr;
    private Long bookmarkCount;
    private boolean bookmarkedByUser;

    @QueryProjection
    public ReportList(Long reportId, String reportImgUrl, String reportStatus, String roadAddr, Long bookmarkCount, boolean bookmarkedByUser) {
        this.reportId = reportId;
        this.reportImgUrl = reportImgUrl;
        this.reportStatus = reportStatus;
        this.roadAddr = roadAddr;
        this.bookmarkCount = bookmarkCount;
        this.bookmarkedByUser = bookmarkedByUser;
    }
}
