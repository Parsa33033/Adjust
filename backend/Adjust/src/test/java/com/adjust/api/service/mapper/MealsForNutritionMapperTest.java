package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MealsForNutritionMapperTest {

    private MealsForNutritionMapper mealsForNutritionMapper;

    @BeforeEach
    public void setUp() {
        mealsForNutritionMapper = new MealsForNutritionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mealsForNutritionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mealsForNutritionMapper.fromId(null)).isNull();
    }
}
