package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.NutritionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Nutrition} and its DTO {@link NutritionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NutritionMapper extends EntityMapper<NutritionDTO, Nutrition> {


    @Mapping(target = "mealTypes", ignore = true)
    @Mapping(target = "removeMealTypes", ignore = true)
    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "removeFoods", ignore = true)
    @Mapping(target = "meals", ignore = true)
    @Mapping(target = "removeMeals", ignore = true)
    Nutrition toEntity(NutritionDTO nutritionDTO);

    default Nutrition fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nutrition nutrition = new Nutrition();
        nutrition.setId(id);
        return nutrition;
    }
}
