package com.resellerapp.model.view;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OffersWithUsernamesDTO {

    private Long id;

    private String description;

    private BigDecimal price;

    private Condition condition;

    private User user;
}
