package com.example.carweb.service;

import com.example.carweb.model.entity.Picture;
import com.example.carweb.repo.PictureRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;

    }

    public void savePicture(Long carId, String picture) {

        // Read image file into byte array
        File imageFile = new File(picture);
        byte[] imageData = new byte[(int) imageFile.length()];
        try (FileInputStream fis = new FileInputStream(imageFile);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int bytesRead;
            while ((bytesRead = fis.read(imageData)) != -1) {
                baos.write(imageData, 0, bytesRead);
            }
            imageData = baos.toByteArray();

            Picture iamge = new Picture();
//            iamge.setCar(carAndPictureService.findCarById(carId));
            iamge.setName(imageData);

            pictureRepository.save(iamge);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Set<Picture> findAllByCarId(Long id) {
        return pictureRepository.findAllByCarId(id);
    }

    public Set<Picture> findAllPicturesByCarId(Long carId, String picture) {
        savePicture(carId,picture);
        return pictureRepository.findAllByCarId(carId);
    }
}
