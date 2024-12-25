package com.thedevhorse.domainevent.service;

import com.thedevhorse.domainevent.domain.Order;
import com.thedevhorse.domainevent.domain.event.OrderCreated;
import com.thedevhorse.domainevent.domain.event.OrderMessage;
import com.thedevhorse.domainevent.messaging.MessagePublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderServiceImpl implements OrderService {

    private final MessagePublisher messagePublisher;

    public OrderServiceImpl(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @Override
    public void createOrder(Order order) {
        OrderCreated orderCreated = OrderCreated.create(
                order.orderId(),
                OrderMessage.message(order),
                Instant.now()
        );
        messagePublisher.publisher(orderCreated);
    }
}
