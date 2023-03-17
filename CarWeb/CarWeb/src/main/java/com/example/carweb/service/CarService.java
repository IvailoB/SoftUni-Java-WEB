package com.example.carweb.service;

import com.example.carweb.model.dtos.AddCarDTO;
import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.view.CarsViewDTO;
import com.example.carweb.repo.CarRepository;
import com.example.carweb.util.LoggedUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final PictureService pictureService;
    private final UserService userService;
    private final TownService townService;
    private final LoggedUser loggedUser;

    public CarService(CarRepository carRepository, PictureService pictureService, UserService userService, TownService townService, LoggedUser loggedUser) {
        this.carRepository = carRepository;
        this.pictureService = pictureService;
        this.userService = userService;
        this.townService = townService;
        this.loggedUser = loggedUser;
    }

    public Set<CarsViewDTO> getCarFromCurrentUser(Long id) {
        Set<Car> loggedUserCars = carRepository.findAllBySeller_id(id);

        return loggedUserCars
                .stream().map(c -> new CarsViewDTO(c.getId(),
                        c.getMake(), c.getModel(), c.getPrice(),
                        findFirstPictureUrl(c.getPictures()), c.getStatus()))
                .collect(Collectors.toSet());
    }

    public Set<CarsViewDTO> getCarsFromOtherUsers(Long id) {

        Set<Car> carsBySellerIdNot = carRepository.findCarsBySellerIdNot(id);

       return getAllCars()
                .stream().map(c -> new CarsViewDTO(c.getId(),
                        c.getMake(), c.getModel(), c.getPrice(),
                        findFirstPictureUrl(c.getPictures()), c.getStatus()))
                .collect(Collectors.toSet());
    }


    public String findFirstPictureUrl(Set<Picture> pictures) {
        return pictures.stream().findFirst().map(p -> p.getName()).orElse("N/A");
    }

    public Car addCar(AddCarDTO addCarDTO) throws IOException {
        Car car = new Car();

        car.setMake(addCarDTO.getMake());
        car.setModel(addCarDTO.getModel());
        car.setPrice(addCarDTO.getPrice());
        car.setStatus(addCarDTO.getStatus());
        car.setTransmission(addCarDTO.getTransmission());
        car.setCoupeEnum(addCarDTO.getCoupe());
        car.setEngineEnum(addCarDTO.getEngine());
        car.setColor(addCarDTO.getColor());
        car.setDescription(addCarDTO.getDescription());
        car.setKilometers(addCarDTO.getKilometers());
        car.setYear(addCarDTO.getYear());

        car.setSeller(userService.findUserById(addCarDTO.getId()));
        car.setTown(townService.findTownByPostcode(addCarDTO.getTownName(), addCarDTO.getPostcode()));

        pictureService.findPictures(car,addCarDTO.getPicture());

        return carRepository.save(car);
//        pictureService.savePicture(addCarDTO.getPicture());

//        Set<Picture> carPictures = pictureService.findAllByCarId(car.getId());
//        car.setPictures(carPictures);

    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void buyCarById(Long id) {
        carRepository.deleteById(id);
    }

    public Car findCarById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }
}
