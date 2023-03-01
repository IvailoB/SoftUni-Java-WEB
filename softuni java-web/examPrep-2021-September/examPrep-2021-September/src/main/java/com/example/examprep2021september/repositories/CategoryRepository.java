package com.example.examprep2021september.repositories;

import com.example.examprep2021september.model.entity.Category;
import com.example.examprep2021september.model.entity.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryNameEnum name);
}
