package com.songspasssta.reportservice.application.port.in;

import com.songspasssta.reportservice.dto.response.MyReportListResponse;
import com.songspasssta.reportservice.dto.response.ReportDetailResponse;
import com.songspasssta.reportservice.dto.response.ReportListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportUseCase {
    void save(CreateReportCommand createCommand);

    ReportListResponse findAllReports(Long memberId, List<String> regions, String sort, List<String> statuses, Pageable pageable);

    ReportDetailResponse findReportById(Long reportId, Long memberId);

    MyReportListResponse findMyReports(Long memberId);

    void deleteReport(Long reportId, Long memberId);

    void updateReport(UpdateReportCommand updateCommand);

    void deleteAllByMemberId(Long memberId);
}

