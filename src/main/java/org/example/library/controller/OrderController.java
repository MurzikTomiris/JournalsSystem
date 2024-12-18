package org.example.library.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.library.dto.OrderRequestDTO;
import org.example.library.dto.OrderResponseDTO;
import org.example.library.service.OrderManagmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderManagmentService orderService;

    /**
     * Создание заказа.
     */
    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponse = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(orderResponse);
    }

    /**
     * Получить список всех заказов (только для администратора).
     */
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Оплатить заказ.
     */
    @PostMapping("/pay/{orderId}")
    public ResponseEntity<OrderResponseDTO> payOrder(@PathVariable Long orderId) {
        OrderResponseDTO orderResponse = orderService.payOrder(orderId);
        return ResponseEntity.ok(orderResponse);
    }

    /**
     * Деактивировать заказ (только администратор).
     */
    @PostMapping("/deactivate/{orderId}")
    public ResponseEntity<OrderResponseDTO> deactivateOrder(@PathVariable Long orderId) {
        OrderResponseDTO orderResponse = orderService.deactivateOrder(orderId);
        return ResponseEntity.ok(orderResponse);
    }
}