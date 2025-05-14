package com.songspasssta.reportservice.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UpdateReportCommand {
    private final Long memberId;
    private final Long reportId;
    private final String reportDesc;
    private final String reportStatus;
    private final MultipartFile imageFile;
}
