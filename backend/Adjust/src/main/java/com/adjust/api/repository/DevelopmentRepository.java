package com.adjust.api.repository;

import com.adjust.api.domain.AdjustProgram;
import com.adjust.api.domain.Development;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Development entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevelopmentRepository extends JpaRepository<Development, Long> {
    List<Development> findAllByAdjustProgram(AdjustProgram adjustProgram);
}
