package com.thedevhorse.domainevent.domain;

import java.util.UUID;

public class Order {

    private UUID orderId;

    public UUID orderId() {
        return orderId;
    }

    private Order(final UUID orderId) {
        this.orderId = orderId;
    }

    public static Order create(final UUID orderId) {
        return new Order(orderId);
    }
}
