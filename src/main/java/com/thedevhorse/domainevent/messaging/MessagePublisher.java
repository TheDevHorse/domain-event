package com.thedevhorse.domainevent.messaging;

import com.thedevhorse.domainevent.domain.event.DomainEvent;

public interface MessagePublisher {

    void publisher(DomainEvent domainEvent);
}
