package ru.webbyskysender.messagereceiverclient.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.webbyskysender.messagereceiverclient.dto.Message;

@Service
public class MessageKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageKafkaProducer.class);
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public MessageKafkaProducer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToKafka(Message message){
        kafkaTemplate.send("messages", message);
        log.info("The message sent to Kafka: {}", message);
    }
}
