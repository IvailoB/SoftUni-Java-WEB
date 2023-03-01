package com.resellerapp.controller;

import com.resellerapp.model.dto.OfferAddDTO;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final LoggedUser loggedUser;
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OfferController(LoggedUser loggedUser, OfferService offerService, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add() {
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "offer-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OfferAddDTO offerAddDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerAddDTO", offerAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddDTO", bindingResult);

            return "redirect:add";
        }

        offerAddDTO.setId(loggedUser.getId());

        offerService.addOrder(offerAddDTO);

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeOffer(@PathVariable Long id) {
        offerService.removeOfferById(id);

        return "redirect:/home";
    }

    @GetMapping("/buy/{id}")
    public String buyOffer(@PathVariable Long id) {

        offerService.addOfferByID(id);

        return "redirect:/home";
    }

    @ModelAttribute
    public OfferAddDTO orderAddBindingModel() {
        return new OfferAddDTO();
    }
}
