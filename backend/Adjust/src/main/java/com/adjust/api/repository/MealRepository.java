package com.adjust.api.repository;

import com.adjust.api.domain.Meal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Meal entity.
 */
@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    @Query(value = "select distinct meal from Meal meal left join fetch meal.nutritions",
        countQuery = "select count(distinct meal) from Meal meal")
    Page<Meal> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct meal from Meal meal left join fetch meal.nutritions")
    List<Meal> findAllWithEagerRelationships();

    @Query("select meal from Meal meal left join fetch meal.nutritions where meal.id =:id")
    Optional<Meal> findOneWithEagerRelationships(@Param("id") Long id);
}
