package com.thedevhorse.domainevent.messaging;

import com.thedevhorse.domainevent.domain.DomainEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderMessaging implements MessagePublisher {

    Sinks.Many<DomainEvent> sinks = Sinks.many().replay().latest();

    @Bean
    public Supplier<Flux<DomainEvent>> orderSupplier(){
        return sinks::asFlux;
    }

    @Override
    public void publisher(DomainEvent domainEvent) {
        sinks.tryEmitNext(domainEvent);
    }
}
