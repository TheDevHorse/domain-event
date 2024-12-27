package com.thedevhorse.domainevent;

import com.thedevhorse.domainevent.domain.Order;
import com.thedevhorse.domainevent.domain.event.OrderCreated;
import com.thedevhorse.domainevent.domain.event.OrderMessage;
import com.thedevhorse.domainevent.messaging.MessagePublisher;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = DomainEventApplication.class)
class DomainEventApplicationTests {

    @Autowired
    MessagePublisher messagePublisher;

    @Test
    void givenOrderCreatedEvent_whenOrderMessageIsCalled_ThenMessageIsPublished() {
        // Given
        UUID orderId = UUID.randomUUID();

        OrderCreated orderCreated = new OrderCreated(
                orderId,
                OrderMessage.message(Order.create(orderId)),
                Instant.now()
        );

        // When
        messagePublisher.publisher(orderCreated);

        // Then
        String kafkaHost = "PLAINTEXT_HOST://localhost:9092";

        Map<String, Object> consumerProps = new HashMap<>(KafkaTestUtils.consumerProps(kafkaHost, "1", "true"));

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList("order-created"));

        ConsumerRecords<String, String> result = KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(10));


        assertThat(result.count()).isGreaterThan(0);

        consumer.close();

    }
}
