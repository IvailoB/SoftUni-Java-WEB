package com.example.battleships.service;

import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.domain.model.ShipModel;
import com.example.battleships.domain.model.UserModel;
import com.example.battleships.domain.model.UserWithShipsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BattleService {
    private final UserService userService;
    private final ShipService shipService;

    @Autowired
    public BattleService(UserService userService, ShipService shipService) {
        this.userService = userService;
        this.shipService = shipService;
    }


    public UserWithShipsModel getLoggedUserWithShips(Long id) {
        UserModel user = this.userService.findById(id);
        List<ShipModel> ships = this.shipService.findAllByUserId(id);

        return UserWithShipsModel.builder()
                .userModel(user)
                .shipModels(ships)
                .build();
    }
}
