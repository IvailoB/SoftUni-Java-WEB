package com.example.carweb.repo;

import com.example.carweb.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    Optional<Town> findTownByPostcode(Integer postcode);
}
