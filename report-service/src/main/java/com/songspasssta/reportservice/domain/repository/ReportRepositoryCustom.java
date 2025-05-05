package com.songspasssta.reportservice.domain.repository;

import com.songspasssta.reportservice.domain.type.RegionType;
import com.songspasssta.reportservice.domain.type.ReportType;
import com.songspasssta.reportservice.dto.response.ReportList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportRepositoryCustom {
    Page<ReportList> findReportsWithFilter(Long memberId, List<RegionType> regionTypes,
                                           List<ReportType> reportTypes, String sort,
                                           Pageable pageable);
}
