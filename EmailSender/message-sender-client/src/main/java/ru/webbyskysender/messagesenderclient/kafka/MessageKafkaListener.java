package ru.webbyskysender.messagesenderclient.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.webbyskysender.messagesenderclient.dto.Message;
import ru.webbyskysender.messagesenderclient.service.EmailService;

@Service
class MessageKafkaListener {

    private static final Logger log = LoggerFactory.getLogger(MessageKafkaListener.class);
    private final EmailService emailService;

    MessageKafkaListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "messages", groupId = "message-sender-group")
    public void consumeMessage(Message message){
        log.info("Received a message: {}", message);
        emailService.sendEmail(message);
    }
}
