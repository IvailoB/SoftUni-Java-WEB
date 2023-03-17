package com.example.carweb.controller;

import com.example.carweb.model.dtos.AddCarDTO;
import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.view.CarDetailView;
import com.example.carweb.model.view.CarsViewDTO;
import com.example.carweb.service.CarService;
import com.example.carweb.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final UserService userService;

    private final ModelMapper modelMapper;

    public CarController(CarService carService, UserService userService, ModelMapper modelMapper) {
        this.carService = carService;
        this.userService = userService;
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
                        .map(Picture::getName).collect(Collectors.toSet())
        );

        model.addAttribute("car", carDetailView);

        return "car-detail";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddCarDTO addCarDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) throws IOException {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addCarDTO", addCarDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addCarDTO", bindingResult);

            return "redirect:add";
        }

        addCarDTO.setId(userService.getUserByUsername(principal.getName()).getId());

        Car car = carService.addCar(addCarDTO);

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
