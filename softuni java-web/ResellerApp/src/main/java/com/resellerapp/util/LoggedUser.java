package com.resellerapp.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Setter
@Getter
public class LoggedUser {

    private Long id;
    private String username;


    public boolean isLogged() {
        return this.username != null && this.id != null;
    }
}
