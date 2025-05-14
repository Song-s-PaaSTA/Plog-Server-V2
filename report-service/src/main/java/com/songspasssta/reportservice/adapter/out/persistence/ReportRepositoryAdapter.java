package com.songspasssta.reportservice.adapter.out.persistence;

import com.songspasssta.common.PersistenceAdapter;
import com.songspasssta.common.exception.EntityNotFoundException;
import com.songspasssta.common.exception.ExceptionCode;
import com.songspasssta.reportservice.application.port.out.ReportRepositoryPort;
import com.songspasssta.reportservice.domain.Report;
import com.songspasssta.reportservice.domain.type.RegionType;
import com.songspasssta.reportservice.domain.type.ReportType;
import com.songspasssta.reportservice.dto.response.ReportList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReportRepositoryAdapter implements ReportRepositoryPort {

    private final ReportRepository reportRepository;

    @Override
    public List<Report> findAllByMemberId(Long memberId) {
        return reportRepository.findAllByMemberId(memberId);
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        reportRepository.deleteByMemberId(memberId);
    }

    @Override
    public Page<ReportList> findReportsWithFilter(Long memberId, List<RegionType> regionTypes, List<ReportType> reportTypes, String sort, Pageable pageable) {
        return reportRepository.findReportsWithFilter(memberId, regionTypes, reportTypes, sort, pageable);
    }

    @Override
    public void save(Report report) {
        reportRepository.save(report);
    }

    @Override
    public void delete(Report report) {
        reportRepository.delete(report);
    }

    @Override
    public Report findById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionCode.REPORT_NOT_FOUND, "ID가 " + id + "인 신고글을 찾을 수 없습니다."));
    }
}
