package com.songspasssta.memberservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportCreatedEventConsumer {
    private final RewardService rewardService;

    @KafkaListener(topics = "report-created")
    public void consume(String message) {
        log.info("Report created event consumed: {}", message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            Long memberId = jsonNode.get("memberId").asLong();
            rewardService.updateScore(memberId);
        } catch (Exception e) {
            log.error("Error processing report created event: {}", e.getMessage());
        }
    }
}
