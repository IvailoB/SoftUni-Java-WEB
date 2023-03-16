package com.example.carweb.controller;


import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.User;
import com.example.carweb.model.view.CarsWithUsernamesDTO;
import com.example.carweb.service.CarService;
import com.example.carweb.service.UserService;
import com.example.carweb.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HomeController {

    private final LoggedUser loggedUser;
    private final UserService userService;
    private final CarService carService;

    public HomeController(LoggedUser loggedUser, UserService userService, CarService carService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping()
    public String index() {
            return "index";
    }

    @GetMapping("/home")
    String home(Principal principal, Model model) {

        User user = userService.getUserByUsername(principal.getName());

        model.addAttribute("currentUserInfo", user);


        Set<Car> carsFromLoggedUser = this.carService.getCarFromCurrentUser(this.loggedUser.getId());
        model.addAttribute("userCars", carsFromLoggedUser);

        Set<CarsWithUsernamesDTO> carsFromOtherUsers = this.carService.getCarsFromOtherUsers(this.loggedUser.getId());
        model.addAttribute("carsFromOtherUsers", carsFromOtherUsers);

        return "home";
    }
}
