package com.adjust.api.repository;

import com.adjust.api.domain.AdjustClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the AdjustClient entity.
 */
@Repository
public interface AdjustClientRepository extends JpaRepository<AdjustClient, Long> {

    @Query(value = "select distinct adjustClient from AdjustClient adjustClient left join fetch adjustClient.tutorials",
        countQuery = "select count(distinct adjustClient) from AdjustClient adjustClient")
    Page<AdjustClient> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct adjustClient from AdjustClient adjustClient left join fetch adjustClient.tutorials")
    List<AdjustClient> findAllWithEagerRelationships();

    @Query("select adjustClient from AdjustClient adjustClient left join fetch adjustClient.tutorials where adjustClient.id =:id")
    Optional<AdjustClient> findOneWithEagerRelationships(@Param("id") Long id);

    Optional<AdjustClient> findAdjustClientByUsername(String username);
}
