package com.example.examprep2021september.service;

import com.example.examprep2021september.model.service.OrderServiceModel;
import com.example.examprep2021september.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrderOrderByPriceDesc();

    void readyOrder(Long id);
}
