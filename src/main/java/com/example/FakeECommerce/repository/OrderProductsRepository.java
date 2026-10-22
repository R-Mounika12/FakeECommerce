package com.example.FakeECommerce.repository;

import com.example.FakeECommerce.entity.Order;
import com.example.FakeECommerce.entity.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {

    List<OrderProducts> findByOrderId(Long orderId);

    @Query("SELECT op FROM OrderProducts op JOIN FETCH op.product WHERE op.order = :order")
    List<OrderProducts> findByOrderWithProduct(Order order);

}
