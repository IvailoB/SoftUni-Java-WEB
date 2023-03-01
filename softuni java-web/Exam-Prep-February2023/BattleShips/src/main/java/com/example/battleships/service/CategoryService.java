package com.example.battleships.service;

import com.example.battleships.domain.entity.Category;
import com.example.battleships.domain.enums.CategoryType;
import com.example.battleships.domain.model.CategoryModel;
import com.example.battleships.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void postConstruct() {
        if (this.categoryRepository.count() == 0) {

            this.categoryRepository.saveAllAndFlush(Arrays.stream(CategoryType.values())
                    .map(categoryType -> CategoryModel.builder()
                            .name(categoryType)
                            .description("hello madafaka")
                            .build())
                    .map(categoryModel -> this.modelMapper.map(categoryModel, Category.class))
                    .toList());
        }
    }

    public CategoryModel findByName(CategoryType name) {
        return modelMapper.map(this.categoryRepository.findByName(name).orElseThrow(), CategoryModel.class);
    }

}
