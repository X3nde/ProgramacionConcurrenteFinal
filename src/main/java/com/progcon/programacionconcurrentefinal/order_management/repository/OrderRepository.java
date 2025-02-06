package com.progcon.programacionconcurrentefinal.order_management.repository;

import com.progcon.programacionconcurrentefinal.order_management.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Aquí se pueden definir consultas personalizadas si fuera necesario.
}
