package com.songspasssta.reportservice.adapter.in.web;

import com.songspasssta.common.response.SuccessResponse;
import com.songspasssta.reportservice.application.port.in.CreateReportCommand;
import com.songspasssta.reportservice.application.port.in.ReportUseCase;
import com.songspasssta.reportservice.application.port.in.UpdateReportCommand;
import com.songspasssta.reportservice.dto.mapper.ReportCommandMapper;
import com.songspasssta.reportservice.dto.request.ReportSaveRequest;
import com.songspasssta.reportservice.dto.request.ReportUpdateRequest;
import com.songspasssta.reportservice.dto.response.MyReportListResponse;
import com.songspasssta.reportservice.dto.response.ReportDetailResponse;
import com.songspasssta.reportservice.dto.response.ReportListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.songspasssta.common.auth.GatewayConstants.GATEWAY_AUTH_HEADER;

/**
 * 신고글 API 컨트롤러 (CRUD)
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reports")
public class ReportApiController {

    private final ReportUseCase reportUseCase;
    /**
     * 신고글 저장
     */
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<SuccessResponse<?>> save(@RequestHeader(GATEWAY_AUTH_HEADER) Long memberId,
                                     @RequestPart("requestDto") @Valid ReportSaveRequest requestDto,
                                     @RequestPart(value = "reportImgFile", required = false) MultipartFile reportImgFile) {
        CreateReportCommand createCommand = ReportCommandMapper.toCreateCommand(memberId, requestDto, reportImgFile);
        reportUseCase.save(createCommand);
        return ResponseEntity.ok().body(SuccessResponse.ofEmpty());
    }

    /**
     * 모든 신고글 조회
     */
    @GetMapping
    public ResponseEntity<SuccessResponse<ReportListResponse>> findAllReports(
            @RequestHeader(GATEWAY_AUTH_HEADER) final Long memberId,
            @RequestParam(value = "region", required = false) List<String> regions,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "status", required = false) List<String> statuses,
            Pageable pageable) {

        ReportListResponse response = reportUseCase.findAllReports(memberId, regions, sort, statuses, pageable);
        return ResponseEntity.ok().body(SuccessResponse.of(response));
    }

    /**
     * 신고글 상세 조회
     */
    @GetMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<ReportDetailResponse>> findReportById(@PathVariable("reportId") Long reportId,
                                                                                @RequestHeader(GATEWAY_AUTH_HEADER) Long memberId) {
        ReportDetailResponse response = reportUseCase.findReportById(reportId, memberId);
        return ResponseEntity.ok().body(SuccessResponse.of(response));
    }

    /**
     * 내 신고글 목록 조회
     */
    @GetMapping("/mine")
    public ResponseEntity<SuccessResponse<MyReportListResponse>> findMyReports(@RequestHeader(GATEWAY_AUTH_HEADER) Long memberId) {
        MyReportListResponse response = reportUseCase.findMyReports(memberId);
        return ResponseEntity.ok().body(SuccessResponse.of(response));
    }

    /**
     * 신고글 삭제
     */
    @DeleteMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<?>> deleteReport(@PathVariable("reportId") Long reportId,
                                             @RequestHeader(GATEWAY_AUTH_HEADER) Long memberId) {
        reportUseCase.deleteReport(reportId, memberId);
        return ResponseEntity.ok().body(SuccessResponse.ofEmpty());
    }

    /**
     * 신고글 수정
     */
    @PatchMapping("/{reportId}")
    public ResponseEntity<SuccessResponse<?>> updateReport(@PathVariable("reportId") Long reportId,
                                             @RequestHeader(GATEWAY_AUTH_HEADER) Long memberId,
                                             @RequestPart("requestDto") @Valid ReportUpdateRequest requestDto,
                                             @RequestPart(value = "reportImgFile", required = false) MultipartFile reportImgFile) {

        UpdateReportCommand updateCommand = ReportCommandMapper.toUpdateCommand(memberId, reportId, requestDto, reportImgFile);
        reportUseCase.updateReport(updateCommand);
        return ResponseEntity.ok().body(SuccessResponse.ofEmpty());
    }

    /**
     * 사용자 신고글 전체 삭제
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteAllByMemberId(@RequestParam("memberId") Long memberId) {
        reportUseCase.deleteAllByMemberId(memberId);
        return ResponseEntity.noContent().build();
    }
}