package com.lumos.assignment.service;

import com.lumos.assignment.model.Order;
import com.lumos.assignment.model.UserEntity;
import com.lumos.assignment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(String dishName, int quantity, UserEntity user) {
        Order order = new Order();
        order.setDishName(dishName);
        order.setQuantity(quantity);
        order.setUser(user);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(UserEntity user) {
        return orderRepository.findByUser(user);
    }
}
