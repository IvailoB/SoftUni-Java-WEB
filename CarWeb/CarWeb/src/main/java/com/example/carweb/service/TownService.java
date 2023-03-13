package com.example.carweb.service;

import com.example.carweb.model.entity.Town;
import com.example.carweb.repo.TownRepository;
import org.springframework.stereotype.Service;

@Service
public class TownService {
    private final TownRepository townRepository;

    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    public Town findTownByPostcode(String townName, Integer postcode) {
        Town town = townRepository.findTownByPostcode(postcode).orElse(null);
        if (town == null){
            town = new Town(townName,postcode);
            townRepository.save(town);
        }

        return town;

    }
}
