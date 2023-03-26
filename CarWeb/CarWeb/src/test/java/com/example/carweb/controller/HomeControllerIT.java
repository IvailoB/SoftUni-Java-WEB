package com.example.carweb.controller;

import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.entity.User;
import com.example.carweb.model.view.CarsViewDTO;
import com.example.carweb.service.CarService;
import com.example.carweb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomeControllerIT {

    private HomeController homeController;

    @Mock
    private UserService userService;

    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        homeController = new HomeController(userService, carService);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
    }

    @Test
    public void testGetAllCars() {
        List<Car> cars = Arrays.asList(
                new Car( "make1", "model1", "color1", BigDecimal.valueOf(1000), "description1", "2010", "5000"),
                new Car( "make2", "model2", "color2", BigDecimal.valueOf(2000), "description2", "2020", "10000")
        );
        Set<Picture> pictures1 = new HashSet<>(Arrays.asList(new Picture("url1")));
        Set<Picture> pictures2 = new HashSet<>(Arrays.asList(new Picture("url2")));
        cars.get(0).setPictures(pictures1);
        cars.get(1).setPictures(pictures2);
        when(carService.getAllCars()).thenReturn(cars);
        when(carService.findFirstPictureUrl(pictures1)).thenReturn("url1");
        when(carService.findFirstPictureUrl(pictures2)).thenReturn("url2");

        String viewName = homeController.getAllCars(model);

    }

    @Test
    public void testAboutUs() {
        String viewName = homeController.aboutUs();
        assertEquals("about-us", viewName);
    }
}
