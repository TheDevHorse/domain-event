package com.thedevhorse.domainevent.messaging;

import com.thedevhorse.domainevent.domain.DomainEvent;

public interface OrderMessagePublisher {

    void producer(DomainEvent domainEvent);
}
