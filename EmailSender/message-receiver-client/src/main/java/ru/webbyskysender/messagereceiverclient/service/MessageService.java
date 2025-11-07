package ru.webbyskysender.messagereceiverclient.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.webbyskysender.messagereceiverclient.dto.Message;
import ru.webbyskysender.messagereceiverclient.kafka.MessageKafkaProducer;

@Service
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private final MessageKafkaProducer messageKafkaProducer;

    public MessageService(MessageKafkaProducer messageKafkaProducer) {
        this.messageKafkaProducer = messageKafkaProducer;
    }

    public void saveMessage(Message message){
        //saving to Database...
        messageKafkaProducer.sendMessageToKafka(message);
        log.info("The message successfully saved: {}", message);
    }
}
