package com.thedevhorse.domainevent.controller;

import com.thedevhorse.domainevent.domain.Order;
import com.thedevhorse.domainevent.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void createOrder() {
        orderService.createOrder(
                Order.create(UUID.randomUUID())
        );
    }
}
