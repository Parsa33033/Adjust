package com.adjust.api.web.websocket;

import com.adjust.api.domain.AdjustClient;
import com.adjust.api.domain.Conversation;
import com.adjust.api.domain.Specialist;
import com.adjust.api.repository.AdjustClientRepository;
import com.adjust.api.repository.ConversationRepository;
import com.adjust.api.repository.SpecialistRepository;
import com.adjust.api.security.SecurityUtils;
import com.adjust.api.service.ChatMessageService;
import com.adjust.api.service.dto.ChatMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageService {
    Logger logger = LoggerFactory.getLogger(MessageService.class);

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    ChatMessageService chatMessageService;

    @Autowired
    AdjustClientRepository adjustClientRepository;

    @Autowired
    SpecialistRepository specialistRepository;


    @MessageMapping("/client/chat.message")
    @SendTo("/topic/reply")
    public void sendMessageByClient(@Payload ChatMessageDTO msg) {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        // set chat message
        msg.setSender(userLogin);
        AdjustClient adjustClient = adjustClientRepository.findAdjustClientByUsername(userLogin).get();
        Specialist specialist = specialistRepository.findByUsername(msg.getReceiver()).get();
        msg.setReceiver(specialist.getUsername());
        Conversation conversation = conversationRepository.findByAdjustClientAndSpecialist(adjustClient, specialist);
        msg.setConversationId(conversation.getId());
        msg.setText(msg.getText());
        chatMessageService.save(msg);
        simpMessagingTemplate.convertAndSend("/topic/"+ msg.getReceiver() +"/reply", msg.getText());
    }

    @MessageMapping("/specialist/chat.message")
    @SendTo("/topic/reply")
    public void sendMessageBySpecialist(@Payload ChatMessageDTO msg) {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        // set chat message
        msg.setSender(userLogin);
        Specialist specialist = specialistRepository.findByUsername(userLogin).get();
        AdjustClient adjustClient = adjustClientRepository.findAdjustClientByUsername(msg.getReceiver()).get();
        msg.setReceiver(adjustClient.getUsername());
        Conversation conversation = conversationRepository.findByAdjustClientAndSpecialist(adjustClient, specialist);
        msg.setConversationId(conversation.getId());
        msg.setText(msg.getText());
        chatMessageService.save(msg);
        simpMessagingTemplate.convertAndSend("/topic/"+ msg.getReceiver() +"/reply", msg.getText());

    }
}
