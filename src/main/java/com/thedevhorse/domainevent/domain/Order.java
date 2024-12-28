package com.thedevhorse.domainevent.domain;

import java.util.UUID;

public class Order {

    private UUID orderId;

    private Status status;

    private Order(UUID orderId,
                  Status status) {
        this.orderId = orderId;
        this.status = status;
    }

    public static Order create(final UUID orderId,
                               final Status status) {
        return new Order(
                orderId,
                status
        );
    }

    public UUID orderId() {
        return orderId;
    }

    public Status status() {
        return status;
    }

}
