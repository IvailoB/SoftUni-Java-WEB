package com.example.shippinglist.web;

import com.example.shippinglist.model.enums.CategoryName;
import com.example.shippinglist.model.service.UserServiceModel;
import com.example.shippinglist.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {

        Object loggedUserObject = httpSession.getAttribute("user");

        if (loggedUserObject == null) {
            return "index";
        }

        UserServiceModel loggedUser = (UserServiceModel) loggedUserObject;

        model.addAttribute("totalSum", productService.getTotalSum(loggedUser.getId()));

        model.addAttribute("drinks", productService
                .findAllProductsByCategoryNameAndUserId(CategoryName.DRINK, loggedUser.getId()));

        model.addAttribute("foods", productService
                .findAllProductsByCategoryNameAndUserId(CategoryName.FOOD, loggedUser.getId()));

        model.addAttribute("others", productService
                .findAllProductsByCategoryNameAndUserId(CategoryName.OTHER, loggedUser.getId()));

        model.addAttribute("households", productService
                .findAllProductsByCategoryNameAndUserId(CategoryName.HOUSEHOLD, loggedUser.getId()));

        return "home";
    }


}
