package com.example.carweb.repo;

import com.example.carweb.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Set<Car> findAllBySeller_id(Long id);

    Set<Car> findCarsBySellerIdNot(Long id);
}
