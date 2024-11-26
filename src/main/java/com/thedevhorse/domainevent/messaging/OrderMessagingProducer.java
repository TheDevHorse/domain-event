package com.thedevhorse.domainevent.messaging;

import com.thedevhorse.domainevent.domain.DomainEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagingProducer implements OrderMessagePublisher {

    @Override
    public void producer(DomainEvent domainEvent) {

    }
}
