package com.thedevhorse.domainevent.domain.event;

import com.thedevhorse.domainevent.domain.Order;
import com.thedevhorse.domainevent.domain.Status;

import java.util.UUID;

public record OrderMessage(UUID orderId, Status status) {

    public static OrderMessage message(Order order) {
        return new OrderMessage(
                order.orderId(),
                order.status()
        );
    }
}
