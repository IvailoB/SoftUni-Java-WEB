package com.example.battleships.repository;

import com.example.battleships.domain.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    Optional<Ship> findByName(String name);

    Optional<List<Ship>> findAllByUserId(Long id);
}
