package com.example.coffeeshop.service.imp;

import com.example.coffeeshop.repository.CategoryRepo;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.model.entity.Category;
import com.example.coffeeshop.model.entity.CategoryEnum;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @Override
    public void initStyles() {
        if (this.categoryRepo.count() != 0) {
            return;
        }

        Arrays.stream(CategoryEnum.values())
                .forEach(_category -> {
                    Category category = new Category();
                    category.setName(_category);

                    if (category.getName().equals(CategoryEnum.DRINK)) {
                        category.setNeededTime(1);
                    }

                    if (category.getName().equals(CategoryEnum.COFFEE)) {
                        category.setNeededTime(2);
                    }
                    if (category.getName().equals(CategoryEnum.OTHER)) {
                        category.setNeededTime(5);
                    }
                    if (category.getName().equals(CategoryEnum.CAKE)) {
                        category.setNeededTime(10);
                    }
                    this.categoryRepo.save(category);
                });
    }
}
