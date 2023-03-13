package com.example.carweb.controller;

import com.example.carweb.model.dtos.AddCarDTO;
import com.example.carweb.service.CarService;
import com.example.carweb.util.LoggedUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public CarController(CarService carService, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.carService = carService;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addCar(){
        return "add-car";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddCarDTO addCarDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("addCarDTO", addCarDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addCarDTO", bindingResult);

            return "redirect:add";
        }

        addCarDTO.setId(loggedUser.getId());

        carService.addCar(addCarDTO);

        return "redirect:/home";
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
