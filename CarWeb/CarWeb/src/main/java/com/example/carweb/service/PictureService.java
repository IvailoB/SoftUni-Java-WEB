package com.example.carweb.service;

import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Picture;
import com.example.carweb.repo.PictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;

    }


    public void createPicture(Picture picture) {
        pictureRepository.save(picture);
    }

//    public Picture savePicture(MultipartFile file) throws IOException {
//
//        Picture picture = new Picture();
//        picture.setName(file.getOriginalFilename());
//        picture.setContentType(file.getContentType());
//        picture.setData(file.getBytes());
//
//        return picture;
//    }

//    public Car findPictures(Car car, MultipartFile picture) throws IOException {
//        Picture images = savePicture(picture);
//        images.setCar(car);
//        pictureRepository.save(images);
//        car.setPictures(Collections.singleton(images));
//        return car;
//    }
}
