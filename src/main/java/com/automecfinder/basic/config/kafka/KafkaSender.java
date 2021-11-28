package com.automecfinder.basic.config.kafka;

import com.automecfinder.basic.config.properties.KafkaProperties;
import com.automecfinder.basic.model.User;
import com.automecfinder.basic.util.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaSender {

    private KafkaProperties kafkaProperties;
    private KafkaTemplate<String, String> kafkaTemplate;
    private HttpUtil httpUtil;

    public void enqueue(User user) {
        httpUtil.serialize(user)
                .ifPresent(userSerialized -> {
                    log.info("Enqueueing User message into Kafka. Topic: {}, Payload: {}", kafkaProperties.getTopic(), userSerialized);

                    try {
                        kafkaTemplate.send(kafkaProperties.getTopic(), userSerialized);
                    } catch (Exception e) {
                        log.error("Error on enqueue User message into kafka: \n{}", e.getMessage());
                        throw new RuntimeException(e);
                    }
                });
    }
}
