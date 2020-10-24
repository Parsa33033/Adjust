package com.adjust.api.repository;

import com.adjust.api.domain.MealsForNutrition;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MealsForNutrition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MealsForNutritionRepository extends JpaRepository<MealsForNutrition, Long> {
}
