package com.example.battleships.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithShipsModel {

    private UserModel userModel;
    private List<ShipModel> shipModels;

}

