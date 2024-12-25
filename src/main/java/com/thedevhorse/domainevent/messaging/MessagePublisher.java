package com.thedevhorse.domainevent.messaging;

import com.thedevhorse.domainevent.domain.DomainEvent;

public interface MessagePublisher {

    void publisher(DomainEvent domainEvent);
}
