package com.adjust.api.repository;

import com.adjust.api.domain.MedicalProfile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MedicalProfile entity.
 */
@Repository
public interface MedicalProfileRepository extends JpaRepository<MedicalProfile, Long> {

    @Query(value = "select distinct medicalProfile from MedicalProfile medicalProfile left join fetch medicalProfile.diseases",
        countQuery = "select count(distinct medicalProfile) from MedicalProfile medicalProfile")
    Page<MedicalProfile> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct medicalProfile from MedicalProfile medicalProfile left join fetch medicalProfile.diseases")
    List<MedicalProfile> findAllWithEagerRelationships();

    @Query("select medicalProfile from MedicalProfile medicalProfile left join fetch medicalProfile.diseases where medicalProfile.id =:id")
    Optional<MedicalProfile> findOneWithEagerRelationships(@Param("id") Long id);
}
