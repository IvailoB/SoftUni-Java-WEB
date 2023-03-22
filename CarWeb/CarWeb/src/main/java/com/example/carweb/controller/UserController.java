package com.example.carweb.controller;

import com.example.carweb.model.dtos.LoginDTO;
import com.example.carweb.model.dtos.RegisterDTO;
import com.example.carweb.model.entity.User;
import com.example.carweb.model.view.UserProfileView;
import com.example.carweb.service.UserService;
import com.example.carweb.util.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerDTO", registerDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", result);

            return "redirect:/users/register";
        }

        this.userService.register(registerDTO);
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        User user = userService.getUserByUsername(principal.getName());
        UserProfileView profileView = new UserProfileView();

        profileView.setUsername(user.getUsername());
        profileView.setEmail(user.getEmail());
        profileView.setFirstName(user.getFirstName());
        profileView.setLastName(user.getLastName());

        model.addAttribute("user", profileView);
        return "profile";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    @ModelAttribute
    public RegisterDTO registerDTO() {
        return new RegisterDTO();
    }

    @ModelAttribute
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }
}
