package com.example.carweb.repo;

import com.example.carweb.model.entity.Picture;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    Set<Picture> findAllByCarId(Long id);
}
