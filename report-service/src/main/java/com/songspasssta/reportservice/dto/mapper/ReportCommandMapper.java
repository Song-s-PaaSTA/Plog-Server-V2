package com.songspasssta.reportservice.dto.mapper;

import com.songspasssta.reportservice.application.port.in.CreateReportCommand;
import com.songspasssta.reportservice.application.port.in.UpdateReportCommand;
import com.songspasssta.reportservice.dto.request.ReportSaveRequest;
import com.songspasssta.reportservice.dto.request.ReportUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

public class ReportCommandMapper {
    public static CreateReportCommand toCreateCommand(Long memberId, ReportSaveRequest request, MultipartFile imageFile) {
        return new CreateReportCommand(
                memberId,
                request.getReportDesc(),
                request.getRoadAddr(),
                request.getReportStatus(),
                imageFile
        );
    }

    public static UpdateReportCommand toUpdateCommand(Long memberId, Long reportId, ReportUpdateRequest request, MultipartFile imageFile) {
        return new UpdateReportCommand(
                memberId,
                reportId,
                request.getReportDesc(),
                request.getReportStatus(),
                imageFile
        );
    }
}
