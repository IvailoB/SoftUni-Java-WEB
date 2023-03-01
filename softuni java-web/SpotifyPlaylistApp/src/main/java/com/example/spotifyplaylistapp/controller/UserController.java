package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.LoginDto;
import com.example.spotifyplaylistapp.model.dto.RegisterDto;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final LoggedUser loggedUser;
    private final UserService userService;

    private final ModelMapper modelMapper;


    public UserController(LoggedUser loggedUser, UserService userService, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("registerDto")) {
            model.addAttribute("registerDto", new RegisterDto());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid RegisterDto registerDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !registerDto.getPassword()
                .equals(registerDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDto", bindingResult);

            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(registerDto, User.class));

        return "redirect:login";
    }


    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("loginDto")) {
            model.addAttribute("loginDto", new LoginDto());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid LoginDto loginDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDto", bindingResult);

            return "redirect:login";
        }

        User user = userService.findUser(loginDto.getUsername(), loginDto.getPassword());

        if (user == null) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:login";
        }

        userService.login(user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        if (!this.loggedUser.isLogged()) {
            return "redirect:/users/login";
        }

        this.userService.logout();
        return "redirect:/";
    }
}
