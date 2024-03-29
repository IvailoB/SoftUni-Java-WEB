package com.example.carweb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email; // –  username of the user.

    @Column(nullable = false)
    private String password; //– password of the user.

    @Column
    private String firstName; //–  first name of the user.

    @Column(nullable = false)
    private String lastName; //–  last name of the user.

    @Column
    private Boolean isActive; //– true OR false.

    @OneToOne
    private Picture profilePicture;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles; //–  user's role (UserEntity or Admin).

    public User(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
