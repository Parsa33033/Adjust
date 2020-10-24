package com.adjust.api.repository;

import com.adjust.api.domain.AdjustClient;
import com.adjust.api.domain.Conversation;

import com.adjust.api.domain.Specialist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Conversation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Conversation findByAdjustClientAndSpecialist(AdjustClient adjustClient, Specialist specialist);
}
