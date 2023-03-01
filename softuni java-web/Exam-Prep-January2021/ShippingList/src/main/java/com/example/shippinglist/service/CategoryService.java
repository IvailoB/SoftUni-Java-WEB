package com.example.shippinglist.service;

import com.example.shippinglist.model.entity.Category;
import com.example.shippinglist.model.enums.CategoryName;

public interface CategoryService {

    void initCategories();

    Category findByName(CategoryName categoryName);
}
