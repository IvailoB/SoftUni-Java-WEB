package com.example.battleships.domain.model;

import com.example.battleships.domain.enums.CategoryType;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModel {

    private Long id;

    private CategoryType name;

    private String description;
}

