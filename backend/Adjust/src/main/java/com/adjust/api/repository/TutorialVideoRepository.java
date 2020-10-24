package com.adjust.api.repository;

import com.adjust.api.domain.TutorialVideo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TutorialVideo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TutorialVideoRepository extends JpaRepository<TutorialVideo, Long> {
}
