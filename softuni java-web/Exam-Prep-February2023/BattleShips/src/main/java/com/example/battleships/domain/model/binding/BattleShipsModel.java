package com.example.battleships.domain.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BattleShipsModel {
    private Long loggedUser;
    private Long notLoggedUser;
}
