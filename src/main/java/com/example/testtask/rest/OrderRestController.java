package com.example.testtask.rest;

import com.example.testtask.model.Order;
import com.example.testtask.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Рест контроллер для Order(Заказ)
 * Используется для:
 * 1) Получения списка заказов
 * 2) Получения заказа по id
 * 3) Создания заказа
 * @author Shadskiy Nikolai
 * @version 1.0.0
 */

@RestController
@RequestMapping("/orders")
public class OrderRestController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("get")
    public ResponseEntity<Order> getOrderById(@RequestParam("id") Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Заказ не найден"));
        return ResponseEntity.ok(order);
    }

    @GetMapping("getall")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderRepository.save(order));
    }

}
