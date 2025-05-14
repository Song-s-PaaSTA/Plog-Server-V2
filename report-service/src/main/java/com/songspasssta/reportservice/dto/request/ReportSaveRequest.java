package com.songspasssta.reportservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 신고글 저장 요청 DTO
 */
@Getter
@NoArgsConstructor
public class ReportSaveRequest {

    @NotBlank(message = "신고 설명은 필수입니다.")
    private String reportDesc;

    @NotBlank(message = "도로명 주소는 필수입니다.")
    private String roadAddr;

    @NotBlank(message = "신고글 상태는 필수입니다.")
    private String reportStatus;
}