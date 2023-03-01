package com.example.battleships.web;

import com.example.battleships.domain.entity.Ship;
import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.domain.model.UserWithShipsModel;
import com.example.battleships.domain.model.binding.BattleShipsModel;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.service.BattleService;
import com.example.battleships.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final LoggedUser loggedUser;
    private final BattleService battleService;
    private final UserService userService;

    private final ShipRepository shipRepository;

    public HomeController(LoggedUser loggedUser, BattleService battleService, UserService userService, ShipRepository shipRepository) {
        this.loggedUser = loggedUser;
        this.battleService = battleService;
        this.userService = userService;
        this.shipRepository = shipRepository;
    }


    @GetMapping("home")
    public ModelAndView getHome(ModelAndView modelAndView) {
        UserWithShipsModel loggedUser = battleService.getLoggedUserWithShips(this.loggedUser.getId());
        UserWithShipsModel notLoggedUser = battleService.getLoggedUserWithShips(userService.findByIdNot(this.loggedUser.getId()).getId());

        modelAndView.setViewName("home");
        modelAndView.addObject("loggedUser", loggedUser);
        modelAndView.addObject("notLoggedUser", notLoggedUser);

        return modelAndView;
    }


    @GetMapping()
    public String getIndex() {
        return "index";
    }

    @PostMapping("battle")
    public String getHome(@ModelAttribute(name = "battleShipModel") BattleShipsModel battleShipsModel) {
        return "redirect:home";
    }

    @ModelAttribute(name = "battleShipModel")
    public BattleShipsModel battleShipsModel() {
        return new BattleShipsModel();
    }

    @ModelAttribute(name = "allShips")
    public List<Ship> ships() {
        return shipRepository.findAll();
    }

}
