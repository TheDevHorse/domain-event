package com.thedevhorse.domainevent.domain.event;

import com.thedevhorse.domainevent.domain.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record OrderCreated(UUID id, OrderMessage message, Instant occurred) implements DomainEvent {

}
