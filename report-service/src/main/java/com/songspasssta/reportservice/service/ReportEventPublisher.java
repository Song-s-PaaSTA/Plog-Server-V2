package com.songspasssta.reportservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReportEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.name}")
    private String topicName;

    public void publishReportCreatedEvent(Long memberId) {
        log.info("Publishing report created event for memberId: {}", memberId);
        kafkaTemplate.send(topicName, memberId.toString());
    }
}
