package com.progcon.programacionconcurrentefinal.order_management.service;

import com.progcon.programacionconcurrentefinal.order_management.model.Order;
import com.progcon.programacionconcurrentefinal.order_management.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Crea y persiste un nuevo pedido.
     *
     * @param product el nombre del producto
     * @param quantity la cantidad solicitada
     * @return el pedido creado
     */
    @Transactional
    public Order createOrder(String product, int quantity) {
        Order order = new Order(product, quantity, LocalDateTime.now());
        return orderRepository.save(order);
    }

    /**
     * Recupera la lista de todos los pedidos.
     *
     * @return lista de pedidos
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
