package com.example.examprep2021september.service;


import com.example.examprep2021september.model.entity.Category;
import com.example.examprep2021september.model.entity.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
