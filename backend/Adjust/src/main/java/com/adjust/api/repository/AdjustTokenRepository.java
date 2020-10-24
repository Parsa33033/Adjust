package com.adjust.api.repository;

import com.adjust.api.domain.AdjustToken;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdjustToken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjustTokenRepository extends JpaRepository<AdjustToken, Long> {
}
