package org.scaler.ecomorderservice.controllers;

import org.scaler.ecomorderservice.dtos.CreateOrderDto;
import org.scaler.ecomorderservice.exceptions.OrderDoesNotExistException;
import org.scaler.ecomorderservice.models.Order;
import org.scaler.ecomorderservice.services.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable("orderId") Long orderId) throws OrderDoesNotExistException {
        return ResponseEntity.ok(orderService.getOrderByOrderId(orderId));
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getOrderByUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader)
            throws OrderDoesNotExistException {
        String token = getToken(authorizationHeader);
        if (token == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(orderService.getOrdersByUserId(token));
    }

    @PostMapping
    public void createOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                            @RequestBody CreateOrderDto createOrderDto) {
        String token = getToken(authorizationHeader);
        if (token == null)
            return;

        orderService.createOrder(token, createOrderDto.getProductId(), createOrderDto.getQuantity(),
                createOrderDto.getAmount(), createOrderDto.getDiscount());
    }

    private String getToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer"))
            return authorizationHeader.substring(7);

        return null;
    }
}
