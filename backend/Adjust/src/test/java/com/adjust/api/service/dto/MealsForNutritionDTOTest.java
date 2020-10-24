package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class MealsForNutritionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MealsForNutritionDTO.class);
        MealsForNutritionDTO mealsForNutritionDTO1 = new MealsForNutritionDTO();
        mealsForNutritionDTO1.setId(1L);
        MealsForNutritionDTO mealsForNutritionDTO2 = new MealsForNutritionDTO();
        assertThat(mealsForNutritionDTO1).isNotEqualTo(mealsForNutritionDTO2);
        mealsForNutritionDTO2.setId(mealsForNutritionDTO1.getId());
        assertThat(mealsForNutritionDTO1).isEqualTo(mealsForNutritionDTO2);
        mealsForNutritionDTO2.setId(2L);
        assertThat(mealsForNutritionDTO1).isNotEqualTo(mealsForNutritionDTO2);
        mealsForNutritionDTO1.setId(null);
        assertThat(mealsForNutritionDTO1).isNotEqualTo(mealsForNutritionDTO2);
    }
}
