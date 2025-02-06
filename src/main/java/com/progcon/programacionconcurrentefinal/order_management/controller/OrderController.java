package com.progcon.programacionconcurrentefinal.order_management.controller;

import com.progcon.programacionconcurrentefinal.order_management.model.Order;
import com.progcon.programacionconcurrentefinal.order_management.service.OrderService;
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

    /**
     * Endpoint para crear un nuevo pedido.
     * Ejemplo de URL: http://localhost:8080/orders?product=ProductoX&quantity=10
     *
     * @param product  nombre del producto
     * @param quantity cantidad del producto
     * @return el pedido creado
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam String product,
                                             @RequestParam int quantity) {
        Order order = orderService.createOrder(product, quantity);
        return ResponseEntity.ok(order);
    }

    /**
     * Endpoint para listar todos los pedidos.
     *
     * @return lista de pedidos
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
