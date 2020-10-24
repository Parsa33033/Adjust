package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class MealsForNutritionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MealsForNutrition.class);
        MealsForNutrition mealsForNutrition1 = new MealsForNutrition();
        mealsForNutrition1.setId(1L);
        MealsForNutrition mealsForNutrition2 = new MealsForNutrition();
        mealsForNutrition2.setId(mealsForNutrition1.getId());
        assertThat(mealsForNutrition1).isEqualTo(mealsForNutrition2);
        mealsForNutrition2.setId(2L);
        assertThat(mealsForNutrition1).isNotEqualTo(mealsForNutrition2);
        mealsForNutrition1.setId(null);
        assertThat(mealsForNutrition1).isNotEqualTo(mealsForNutrition2);
    }
}
