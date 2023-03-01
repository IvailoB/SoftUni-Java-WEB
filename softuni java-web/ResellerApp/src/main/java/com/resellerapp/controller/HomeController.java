package com.resellerapp.controller;

import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.model.view.OffersWithUsernamesDTO;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping(name = "/")
public class HomeController {

    private final LoggedUser loggedUser;

    private final UserService userService;

    private final OfferService offerService;

    public HomeController(LoggedUser loggedUser, UserService userService, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.offerService = offerService;
    }


    @GetMapping()
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = userService.findUserById(loggedUser.getId());

        model.addAttribute("currentUserInfo", user);

        Set<Offer> offersFromCurrentUser = this.offerService.getOfferFromCurrentUser(this.loggedUser.getId());
        model.addAttribute("userOffers", offersFromCurrentUser);

        Set<OffersWithUsernamesDTO> offersFromOtherUsers = this.offerService.getPostsFromOtherUsers(this.loggedUser.getId());
        model.addAttribute("offersFromOtherUsers", offersFromOtherUsers);


        return "home";
    }

}
