package com.example.shippinglist.repository;

import com.example.shippinglist.model.entity.Product;
import com.example.shippinglist.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT SUM(p.price) FROM Product p where p.user.id = :id")
    BigDecimal findTotalProductsSum(String id);

    List<Product> findAllByCategory_NameAndUser_Id(CategoryName categoryName, String id);

}
