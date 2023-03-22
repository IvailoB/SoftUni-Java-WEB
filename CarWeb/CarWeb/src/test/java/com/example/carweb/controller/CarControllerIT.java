package com.example.carweb.controller;

import com.example.carweb.model.dtos.AddCarDTO;
import com.example.carweb.model.entity.Car;
import com.example.carweb.model.entity.Town;
import com.example.carweb.model.enums.CoupeEnum;
import com.example.carweb.model.enums.EngineEnum;
import com.example.carweb.model.enums.StatusCarEnum;
import com.example.carweb.model.enums.Transmission;
import com.example.carweb.repo.CarRepository;
import com.example.carweb.service.CarService;
import com.example.carweb.service.ImageCloudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Mock
    private CarRepository carRepository;
    @Mock
    private ImageCloudService imageCloudService;

    @BeforeEach
    void setUp() {
        imageCloudService = new ImageCloudService();

        carService = new CarService(
                carRepository,
                imageCloudService
        );
    }

    @Test
    public void testAddProduct() throws Exception {
        // Create a mock car object with the given parameters
        AddCarDTO car = new AddCarDTO();
        car.setId(1L);
        car.setMake("Ford");
        car.setModel("Mustang");
        car.setColor("Red");
        car.setPrice(new BigDecimal("25000"));
        car.setDescription("This is a description of the car");
        car.setYear("2022");
        car.setKilometers("5000");
        car.setCoupe(CoupeEnum.SUV);
        car.setEngine(EngineEnum.GASOLINE);
        car.setStatus(StatusCarEnum.GOOD);
        car.setTransmission(Transmission.AUTOMATIC);
        Town town = new Town("Sofia", 123);
        car.setTownName(town.getName());
        car.setPostcode(town.getPostcode());



        // Perform the request and check the response
        mockMvc.perform(post("/cars/add")
                        .param("make", car.getMake())
                        .param("model", car.getModel())
                        .param("color", car.getColor())
                        .param("price", car.getPrice().toString())
                        .param("description", car.getDescription())
                        .param("year", car.getYear())
                        .param("kilometers", car.getKilometers())
                        .param("coupe", car.getCoupe().toString())
                        .param("engine", car.getEngine().toString())
                        .param("status", car.getStatus().toString())
                        .param("transmission", car.getTransmission().toString())
                        .param("townName", car.getTownName())
                        .param("postcode", car.getPostcode().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars/details/" + car.getId()));
    }
}
