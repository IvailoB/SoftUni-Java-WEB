package com.example.carweb.service;

import com.example.carweb.model.entity.Picture;
import com.example.carweb.repo.PictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;

    }

    public Picture savePicture(MultipartFile file) throws IOException {

        Picture picture = new Picture();
        picture.setName(file.getOriginalFilename());
        picture.setContentType(file.getContentType());
        picture.setData(file.getBytes());
    return pictureRepository.save(picture);

    }

    public Set<Picture> findPictures(Long carId, MultipartFile picture) throws IOException {
        Set<Picture> carPictures = pictureRepository.findAllByCarId(carId).orElse(null);

        if (carPictures.size() == 0){
            Picture images = savePicture(picture);
            carPictures.add(images);
        }

        return carPictures;

    }
}
