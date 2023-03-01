package com.example.shippinglist.service;

import com.example.shippinglist.model.enums.CategoryName;
import com.example.shippinglist.model.service.ProductServiceModel;
import com.example.shippinglist.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void add(ProductServiceModel map);

    BigDecimal getTotalSum(String id);

    List<ProductViewModel> findAllProductsByCategoryNameAndUserId(CategoryName categoryName, String id);

    void buyById(String id);

    void buyAll();


}
