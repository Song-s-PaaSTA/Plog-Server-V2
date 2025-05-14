package com.songspasssta.reportservice.application.port.out;

public interface ReportEventPort {
    void publishReportCreatedEvent(Long memberId);
}
