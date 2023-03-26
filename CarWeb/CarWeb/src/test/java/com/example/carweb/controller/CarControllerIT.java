package com.example.carweb.controller;

import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Picture;
import com.example.carweb.model.entity.Town;
import com.example.carweb.model.entity.User;
import com.example.carweb.model.enums.CoupeEnum;
import com.example.carweb.model.enums.EngineEnum;
import com.example.carweb.model.enums.StatusCarEnum;
import com.example.carweb.model.enums.Transmission;
import com.example.carweb.model.view.CarDetailView;
import com.example.carweb.service.CarService;
import com.example.carweb.service.TownService;
import com.example.carweb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private UserService userService;

    @MockBean
    private TownService townService;

    @MockBean
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CarController(carService, userService, townService, modelMapper)).build();
    }








    @Test
    void testAddCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-car"));
    }

    @Test
    void testCarDetail() throws Exception {
        Car car = new Car();
        car.setId(1L);
        car.setMake("Toyota");
        car.setModel("Corolla");
        car.setColor("blue");
        car.setPrice(BigDecimal.valueOf(10000));
        car.setDescription("test");
        car.setYear("2020");
        car.setKilometers("10000");
        car.setCoupeEnum(CoupeEnum.SEDAN);
        car.setEngineEnum(EngineEnum.GASOLINE);
        car.setStatus(StatusCarEnum.GOOD);
        car.setTransmission(Transmission.AUTOMATIC);
        User user = new User();
        user.setUsername("user");
        user.setPassword("123");
        user.setLastName("userLastName");
        car.setSeller(user);
        Town town = new Town();
        town.setPostcode(123);
        town.setName("TownName");
        car.setTown(town);
        car.setPictures(new HashSet<>(Arrays.asList(new Picture(), new Picture())));

        Mockito.when(carService.findCarById(1L)).thenReturn(car);
        Mockito.when(modelMapper.map(Mockito.any(Car.class), Mockito.eq(CarDetailView.class))).thenReturn(new CarDetailView());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/details/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("car-detail"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("car"));
    }

    @Test
    void testAddConfirm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/cars/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/cars/details/{id}"));
    }

    @Test
    void testBuyOffer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars/buy/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("redirect:/home"));
    }

}
