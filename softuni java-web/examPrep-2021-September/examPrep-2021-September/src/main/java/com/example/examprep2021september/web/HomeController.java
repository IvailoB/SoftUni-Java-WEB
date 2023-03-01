package com.example.examprep2021september.web;

import com.example.examprep2021september.model.view.OrderViewModel;
import com.example.examprep2021september.security.CurrentUser;
import com.example.examprep2021september.service.OrderService;
import com.example.examprep2021september.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {

        if (currentUser.getId() == null) {
            return "index";
        }

        List<OrderViewModel> orders = orderService.findAllOrderOrderByPriceDesc();

        model.addAttribute("orders", orders);
        model.addAttribute("totalTime", orders
                .stream()
                .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(0));

        model.addAttribute("users", userService.findAllUserAndCountOfOrdersOrderByCountDesc());

        return "redirect:/home";

    }
}
