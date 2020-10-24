package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.FoodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Food} and its DTO {@link FoodDTO}.
 */
@Mapper(componentModel = "spring", uses = {NutritionMapper.class})
public interface FoodMapper extends EntityMapper<FoodDTO, Food> {

    @Mapping(source = "nutrition.id", target = "nutritionId")
    FoodDTO toDto(Food food);

    @Mapping(source = "nutritionId", target = "nutrition")
    Food toEntity(FoodDTO foodDTO);

    default Food fromId(Long id) {
        if (id == null) {
            return null;
        }
        Food food = new Food();
        food.setId(id);
        return food;
    }
}
