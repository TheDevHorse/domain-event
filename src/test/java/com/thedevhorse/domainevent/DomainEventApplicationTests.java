package com.thedevhorse.domainevent;

import com.thedevhorse.domainevent.domain.event.OrderCreated;
import com.thedevhorse.domainevent.messaging.MessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class DomainEventApplicationTests {

    @Autowired
    OutputDestination outputDestination;

    @Autowired
    MessagePublisher messagePublisher;

    @Test
    void givenOrderCreatedEvent_whenOrderMessageIsCalled_ThenMessageIsPublished(){
        messagePublisher.publisher(new OrderCreated(null, null, null));
        Message<byte[]> result = outputDestination.receive(100, "test-destination");
        assertThat(result).isNotNull();
        assertThat(new String(result.getPayload())).isEqualTo("Hello");
    }
}
