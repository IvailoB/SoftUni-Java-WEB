package com.example.carweb.controller;


import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.entity.User;
import com.example.carweb.model.view.CarsViewDTO;
import com.example.carweb.service.CarService;
import com.example.carweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    private final CarService carService;

    public HomeController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping
    public String getAllCars(Model model) {
        //todo razkomentirai sled kato dobavish snimki
//        var carsViewDTOStream = carService.getAllCars()
//                .stream().map(c -> new CarsViewDTO(c.getId(),
//                        c.getMake(),c.getModel(),c.getPrice(),
//                        carService.findFirstPictureUrl(c.getPictures()),c.getStatus()))
//                .collect(Collectors.toList());
//        model.addAttribute("cars", carsViewDTOStream);

        return "index";
    }



    @GetMapping("/home")
    String home(Principal principal, Model model) {

        User user = userService.getUserByUsername(principal.getName());

        model.addAttribute("currentUserInfo", user);


        Set<CarsViewDTO> carsFromLoggedUser = this.carService.getCarFromCurrentUser(user.getId());
        model.addAttribute("userCars", carsFromLoggedUser);

        Set<CarsViewDTO> carsFromOtherUsers = this.carService.getCarsFromOtherUsers(user.getId());
        model.addAttribute("carsFromOtherUsers", carsFromOtherUsers);

        return "home";
    }
}
