package com.adjust.api.service.mapper;


import com.adjust.api.domain.*;
import com.adjust.api.service.dto.MealsForNutritionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MealsForNutrition} and its DTO {@link MealsForNutritionDTO}.
 */
@Mapper(componentModel = "spring", uses = {NutritionMapper.class})
public interface MealsForNutritionMapper extends EntityMapper<MealsForNutritionDTO, MealsForNutrition> {

    @Mapping(source = "nutrition.id", target = "nutritionId")
    MealsForNutritionDTO toDto(MealsForNutrition mealsForNutrition);

    @Mapping(source = "nutritionId", target = "nutrition")
    MealsForNutrition toEntity(MealsForNutritionDTO mealsForNutritionDTO);

    default MealsForNutrition fromId(Long id) {
        if (id == null) {
            return null;
        }
        MealsForNutrition mealsForNutrition = new MealsForNutrition();
        mealsForNutrition.setId(id);
        return mealsForNutrition;
    }
}
