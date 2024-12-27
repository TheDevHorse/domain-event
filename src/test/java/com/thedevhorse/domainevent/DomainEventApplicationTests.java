package com.thedevhorse.domainevent;

import com.thedevhorse.domainevent.domain.Order;
import com.thedevhorse.domainevent.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = DomainEventApplication.class)
class DomainEventApplicationTests {

    @Autowired
    OrderService orderService;

    @Test
    void givenOrder_whenCreateOrderIsCalled_ThenOrderCreatedMessageIsPublished() {
        // Given
        Order order = Order.create(UUID.randomUUID());

        // When
        orderService.createOrder(order);

        // Then
        try (KafkaConsumer<String, String> consumer = createKafkaConsumer("order-created")) {
            ConsumerRecords<String, String> result = KafkaTestUtils.getRecords(consumer, Duration.ofSeconds(10));
            assertThat(result.count()).isPositive();
        }
    }

    private KafkaConsumer<String, String> createKafkaConsumer(String topic) {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(
                "PLAINTEXT_HOST://localhost:9092",
                "1",
                "true"
        );
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }
}
