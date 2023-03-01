package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ConditionService {
    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public void initCondition() {
        if (conditionRepository.count() != 0) {
            return;
        }

        Arrays.stream(ConditionEnum.values())
                .forEach(categoryNameEnum -> {

                    Condition condition = new Condition();
                    condition.setName(categoryNameEnum);
                    switch (categoryNameEnum) {
                        case EXCELLENT -> condition.setDescription("In perfect condition");
                        case GOOD -> condition.setDescription("Some signs of wear and tear or minor defects");
                        case ACCEPTABLE ->
                                condition.setDescription("The item is fairly worn but continues to function properly");

                    }
                    conditionRepository.save(condition);
                });
    }

    public Condition findCondition(ConditionEnum conditionEnum) {
        return conditionRepository.findByName(conditionEnum).orElse(null);
    }
}
