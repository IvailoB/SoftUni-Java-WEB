package com.example.examprep2021september.repositories;

import com.example.examprep2021september.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameAndPassword(String username, String password);


    @Query("SELECT u FROM User u ORDER BY size(u.orders)desc")
    List<User> findAllByOrderCountByOrdersDesc();
}
