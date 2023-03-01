package com.example.shippinglist.web;


import com.example.shippinglist.model.binding.ProductAddBondingModel;
import com.example.shippinglist.model.service.ProductServiceModel;
import com.example.shippinglist.model.service.UserServiceModel;
import com.example.shippinglist.service.ProductService;
import com.example.shippinglist.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ProductController(ProductService productService, ModelMapper modelMapper, UserService userService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("productAddBondingModel")) {
            model.addAttribute("productAddBondingModel", new ProductAddBondingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ProductAddBondingModel productAddBondingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBondingModel", productAddBondingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBondingModel", bindingResult);

            return "redirect:add";
        }

        UserServiceModel loggedUser = (UserServiceModel) httpSession.getAttribute("user");

        ProductServiceModel productServiceModel = modelMapper
                .map(productAddBondingModel, ProductServiceModel.class);

        productServiceModel.setUser(userService.findById(loggedUser.getId()));

        productService.add(productServiceModel);

        return "redirect:/";
    }


    @GetMapping("/buy/{id}")
    public String buyById(@PathVariable String id) {

        productService.buyById(id);

        return "redirect:/";
    }

    @GetMapping("/buy/add")
    public String buyAll() {
        productService.buyAll();

        return "redirect:/";
    }
}
