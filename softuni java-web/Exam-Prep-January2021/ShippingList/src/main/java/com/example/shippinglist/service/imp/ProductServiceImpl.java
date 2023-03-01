package com.example.shippinglist.service.imp;

import com.example.shippinglist.model.entity.Product;
import com.example.shippinglist.model.enums.CategoryName;
import com.example.shippinglist.model.service.ProductServiceModel;
import com.example.shippinglist.model.view.ProductViewModel;
import com.example.shippinglist.repository.ProductRepository;
import com.example.shippinglist.service.CategoryService;
import com.example.shippinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void add(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);

        product.setCategory(categoryService.findByName(productServiceModel.getCategory()));

        productRepository.save(product);

    }

    @Override
    public BigDecimal getTotalSum(String id) {
        return productRepository.findTotalProductsSum(id);
    }

    @Override
    public List<ProductViewModel> findAllProductsByCategoryNameAndUserId(CategoryName categoryName, String id) {

        return productRepository.findAllByCategory_NameAndUser_Id(categoryName, id)
                .stream()
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        productRepository.deleteAll();

    }
}
