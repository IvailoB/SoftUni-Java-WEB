package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "conditions")
public class Condition extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ConditionEnum name;

    @Column(nullable = false)
    private String description;
}
