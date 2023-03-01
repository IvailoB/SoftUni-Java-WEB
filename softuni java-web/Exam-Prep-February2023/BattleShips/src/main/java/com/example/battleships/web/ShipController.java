package com.example.battleships.web;


import com.example.battleships.domain.model.binding.ShipAddModel;
import com.example.battleships.service.ShipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ships")
public class ShipController {

    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping("/add")
    public String addShip() {
        return "ship-add";
    }

    @PostMapping("/add")
    public String postAdd(@Valid @ModelAttribute(name = "shipAddModel") ShipAddModel shipAddModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("shipAddModel", shipAddModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.shipAddModel", bindingResult);

            return "redirect:add";
        }

        this.shipService.addShip(shipAddModel);

        return "redirect:/home";
    }


    @ModelAttribute(name = "shipAddModel")
    public ShipAddModel shipAddModel() {
        return new ShipAddModel();
    }

}
