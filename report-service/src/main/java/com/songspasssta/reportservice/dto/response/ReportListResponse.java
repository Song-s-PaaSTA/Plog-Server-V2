package com.songspasssta.reportservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * 신고글 필터 조회 응답 DTO
 */
public record ReportListResponse(@JsonProperty("reports") List<ReportList> reports) {

}
