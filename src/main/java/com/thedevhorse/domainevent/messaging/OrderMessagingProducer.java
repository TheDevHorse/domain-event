package com.thedevhorse.domainevent.messaging;

import com.thedevhorse.domainevent.domain.DomainEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Component
public class OrderMessagingProducer implements OrderMessagePublisher {

    Sinks.Many<DomainEvent> sinks = Sinks.many().replay().latest();

    @Bean
    public Supplier<Flux<DomainEvent>> orderSupplier(){
        return ()-> sinks.asFlux();
    }

    @Override
    public void producer(DomainEvent domainEvent) {
        sinks.tryEmitNext(domainEvent);
    }
}
