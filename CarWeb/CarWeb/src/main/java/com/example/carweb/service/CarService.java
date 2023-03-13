package com.example.carweb.service;

import com.example.carweb.model.entity.Car;
import com.example.carweb.model.view.CarsWithUsernamesDTO;
import com.example.carweb.repo.CarRepository;
import com.example.carweb.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final LoggedUser loggedUser;

    public CarService(CarRepository carRepository, LoggedUser loggedUser) {
        this.carRepository = carRepository;
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
}
