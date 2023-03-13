package com.example.carweb.repo;

import com.example.carweb.model.entity.UserRoleEntity;
import com.example.carweb.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
    List<UserRoleEntity> findByRole(UserRoleEnum role);
}
