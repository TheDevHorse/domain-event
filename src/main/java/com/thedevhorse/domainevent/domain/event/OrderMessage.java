package com.thedevhorse.domainevent.domain.event;

import com.thedevhorse.domainevent.domain.Order;

import java.util.UUID;

public record OrderMessage(UUID citizenId) {

    public static OrderMessage of(Order order) {
        return new OrderMessage(order.orderId());
    }
}

