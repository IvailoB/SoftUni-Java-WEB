package com.example.carweb.service;

import com.example.carweb.model.dtos.AddCarDTO;
import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.entity.Town;
import com.example.carweb.model.view.CarsWithUsernamesDTO;
import com.example.carweb.repo.CarRepository;
import com.example.carweb.util.LoggedUser;
import org.springframework.stereotype.Service;

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

    public Set<Car> getCarFromCurrentUser(Long id) {
        return carRepository.findAllBySeller_id(id);
    }

    public Set<CarsWithUsernamesDTO> getCarsFromOtherUsers(Long id) {

        Set<Car> carsBySellerIdNot = carRepository.findCarsBySellerIdNot(id);

        return carsBySellerIdNot.stream()
                .map(car -> {
                    CarsWithUsernamesDTO carsWithUsernamesDTO = new CarsWithUsernamesDTO();

                    carsWithUsernamesDTO.setId(car.getId());
                    carsWithUsernamesDTO.setMake(car.getMake());
                    carsWithUsernamesDTO.setModel(car.getModel());
                    carsWithUsernamesDTO.setStatus(car.getStatus());
                    carsWithUsernamesDTO.setPrice(car.getPrice());
                    carsWithUsernamesDTO.setPictures(car.getPictures());

                    return carsWithUsernamesDTO;
                })
                .collect(Collectors.toSet());
    }

    public void addCar(AddCarDTO addCarDTO) {
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

//        pictureService.savePicture(addCarDTO.getPicture());
//
//        Set<Picture> carPictures = pictureService.findAllByCarId(car.getId());
//        car.setPictures(carPictures);

        carRepository.save(car);
    }

    public void buyCarById(Long id) {
        carRepository.deleteById(id);
    }

    public Car findCarById(Long carId) {
        return carRepository.findById(carId).orElse(null);
    }
}
