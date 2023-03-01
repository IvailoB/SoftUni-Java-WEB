package com.example.battleships.service;

import com.example.battleships.domain.entity.Ship;
import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.domain.model.CategoryModel;
import com.example.battleships.domain.model.ShipModel;
import com.example.battleships.domain.model.UserModel;
import com.example.battleships.domain.model.binding.ShipAddModel;
import com.example.battleships.repository.ShipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    private final LoggedUser loggedUser;
    private final CategoryService categoryService;

    @Autowired
    public ShipService(ShipRepository shipRepository, ModelMapper modelMapper, UserService userService, LoggedUser loggedUser, CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
    }

    public void addShip(ShipAddModel addModel) {
        UserModel loggedUser = this.userService.findById(this.loggedUser.getId());
        CategoryModel categoryModel = this.categoryService.findByName(addModel.getCategory());

        Ship shipToSave = this.modelMapper.map(ShipModel.builder()
                .name(addModel.getName())
                .category(categoryModel)
                .created(addModel.getCreated())
                .health(addModel.getHealth())
                .power(addModel.getPower())
                .user(loggedUser)
                .build(), Ship.class);

        this.shipRepository.saveAndFlush(shipToSave);
    }

    public List<ShipModel> findAllByUserId(Long id) {
       return this.shipRepository.findAllByUserId(id).orElseThrow()
                .stream()
                .map(ship -> this.modelMapper.map(ship, ShipModel.class))
                .toList();
    }
}
