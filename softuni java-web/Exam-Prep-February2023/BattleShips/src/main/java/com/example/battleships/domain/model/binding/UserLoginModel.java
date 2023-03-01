package com.example.battleships.domain.model.binding;

import com.example.battleships.validations.checkUserExistance.ValidateLoginForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@ValidateLoginForm
public class UserLoginModel {

    private String username;
    private String password;
}
