package com.project.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.project.journalApp.model.SentimentData;

@Service
public class SentimentConsumerService {
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "sentiment", groupId = "sentiment_group_id")
    public void consumeSentiment(SentimentData sentimentData) {
        sendEmail(sentimentData);
    }

    private void sendEmail(SentimentData sentimentData) {
        emailService.sendEmail(sentimentData.getEmail(), "Sentiment for previous week", sentimentData.getSentiment());
    }
}
