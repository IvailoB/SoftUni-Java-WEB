package com.example.shippinglist.repository;

import com.example.shippinglist.model.entity.Category;
import com.example.shippinglist.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(CategoryName category);
}
