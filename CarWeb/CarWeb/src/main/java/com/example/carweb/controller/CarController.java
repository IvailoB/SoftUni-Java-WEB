package com.example.carweb.controller;

import com.example.carweb.model.dtos.AddCarDTO;
import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.view.CarDetailView;
import com.example.carweb.service.CarService;
import com.example.carweb.service.TownService;
import com.example.carweb.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final UserService userService;
    private final TownService townService;

    private final ModelMapper modelMapper;

    public CarController(CarService carService, UserService userService, TownService townService, ModelMapper modelMapper) {
        this.carService = carService;
        this.userService = userService;
        this.townService = townService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String addCar() {
        return "add-car";
    }

    @GetMapping("/details/{id}")
    public String carDetail(@PathVariable("id") Long id, Model model) {
        Car car = carService.findCarById(id);

        CarDetailView carDetailView = new CarDetailView(car.getId(),
                car.getMake(), car.getModel(), car.getColor(),
                car.getPrice(), car.getDescription(), car.getYear(),
                car.getKilometers(), car.getCoupeEnum(), car.getEngineEnum(),
                car.getStatus(), car.getTransmission(), car.getSeller().getUsername(),
                car.getTown().getName(),
                car.getPictures().stream()
                        .map(Picture::getUrl).collect(Collectors.toSet())
        );

        model.addAttribute("car", carDetailView);

        return "car-detail";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddCarDTO addCarDTO,
                             Principal principal) throws IOException {

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

        car.setSeller(userService.getUserByUsername(principal.getName()));
        car.setTown(townService.findTownByPostcode(addCarDTO.getTownName(), addCarDTO.getPostcode()));

        carService.createCar(car, addCarDTO.getPicture());

        return "redirect:/cars/details/" + car.getId();
    }

    @GetMapping("/buy/{id}")
    public String buyOffer(@PathVariable Long id) {
        carService.buyCarById(id);

        return "redirect:/home";
    }

    @ModelAttribute
    public AddCarDTO addCarDTO() {
        return new AddCarDTO();
    }
}
