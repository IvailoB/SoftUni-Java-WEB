package com.example.carweb.service;

import com.example.carweb.model.entity.UserRoleEntity;
import com.example.carweb.model.enums.UserRoleEnum;
import com.example.carweb.repo.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void init() {
        if (roleRepository.count() != 0) {
            return;
        }

        Arrays.stream(UserRoleEnum.values())
                .forEach(role -> {
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setRole(role);

                    roleRepository.save(userRoleEntity);
                });
    }
}
