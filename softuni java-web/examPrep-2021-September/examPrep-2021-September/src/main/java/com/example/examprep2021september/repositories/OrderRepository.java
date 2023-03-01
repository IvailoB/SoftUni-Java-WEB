package com.example.examprep2021september.repositories;

import com.example.examprep2021september.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//    @Query("SELECT o FROM Order o ORDER BY o.price DESC")
    List<Order> findAllByOrderByPriceDesc();
}
