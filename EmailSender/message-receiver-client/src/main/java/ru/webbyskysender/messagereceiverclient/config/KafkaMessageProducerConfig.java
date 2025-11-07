package ru.webbyskysender.messagereceiverclient.config;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import ru.webbyskysender.messagereceiverclient.dto.Message;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaMessageProducerConfig {

    @Bean
    public ProducerFactory<String, Message> producerFactory( ObjectMapper objectMapper){
        Map<String, Object> configProperties = new HashMap<>();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        JsonSerializer<Message> serializer = new JsonSerializer<>(objectMapper);
        serializer.setAddTypeInfo(false);

        return new DefaultKafkaProducerFactory<>(
            configProperties,
            new StringSerializer(),
            serializer
        );
    }

    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate(
            ProducerFactory<String, Message> producerFactory
    ){
        return new KafkaTemplate<>(producerFactory);
    }

}
