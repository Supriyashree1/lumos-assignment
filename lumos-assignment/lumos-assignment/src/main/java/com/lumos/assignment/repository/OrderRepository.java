package com.lumos.assignment.repository;

import com.lumos.assignment.model.Order;
import com.lumos.assignment.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(UserEntity user);
}
