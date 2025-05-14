package com.songspasssta.reportservice.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class CreateReportCommand {
    private final Long memberId;
    private final String reportDesc;
    private final String roadAddr;
    private final String reportStatus;
    private final MultipartFile imageFile;


    // 전체 도로명 주소에서 "서울" 등의 시/도 부분을 추출
    public String extractRegionFromAddr() {
        return (roadAddr != null && !roadAddr.isEmpty()) ? roadAddr.split(" ")[0] : null;
    }
}
