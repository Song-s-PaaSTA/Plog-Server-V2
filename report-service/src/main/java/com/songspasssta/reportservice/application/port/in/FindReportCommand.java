package com.songspasssta.reportservice.application.port.in;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FindReportCommand {
    private final Long memberId;
    private final List<String> regions;
    private final String sort;
    private final String reportDesc;
    private final String startDate;
    private final String endDate;
    private final int page;
    private final int size;
}
