package com.songspasssta.reportservice.application.port.out;

import com.songspasssta.reportservice.domain.Report;
import com.songspasssta.reportservice.domain.type.RegionType;
import com.songspasssta.reportservice.domain.type.ReportType;
import com.songspasssta.reportservice.dto.response.ReportList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportRepositoryPort {
    List<Report> findAllByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);

    Page<ReportList> findReportsWithFilter(Long memberId, List<RegionType> regionTypes,
                                           List<ReportType> reportTypes, String sort,
                                           Pageable pageable);

    void save(Report report);

    void delete(Report report);

    Report findById(Long id);
}
