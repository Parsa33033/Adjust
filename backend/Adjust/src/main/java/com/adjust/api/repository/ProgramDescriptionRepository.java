package com.adjust.api.repository;

import com.adjust.api.domain.ProgramDescription;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProgramDescription entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgramDescriptionRepository extends JpaRepository<ProgramDescription, Long> {
}
