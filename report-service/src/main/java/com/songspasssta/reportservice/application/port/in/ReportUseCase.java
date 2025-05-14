package com.songspasssta.reportservice.application.port.in;

import com.songspasssta.reportservice.dto.request.ReportSaveRequest;
import com.songspasssta.reportservice.dto.request.ReportUpdateRequest;
import com.songspasssta.reportservice.dto.response.MyReportListResponse;
import com.songspasssta.reportservice.dto.response.ReportDetailResponse;
import com.songspasssta.reportservice.dto.response.ReportListResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReportUseCase {
    void save(Long memberId, ReportSaveRequest requestDto, MultipartFile reportImgFile);

    ReportListResponse findAllReports(Long memberId, List<String> regions, String sort, List<String> statuses, Pageable pageable);

    ReportDetailResponse findReportById(Long reportId, Long memberId);

    MyReportListResponse findMyReports(Long memberId);

    void deleteReport(Long reportId, Long memberId);

    void updateReport(Long reportId, Long memberId, ReportUpdateRequest requestDto, MultipartFile reportImgFile);

    void deleteAllByMemberId(Long memberId);
}

