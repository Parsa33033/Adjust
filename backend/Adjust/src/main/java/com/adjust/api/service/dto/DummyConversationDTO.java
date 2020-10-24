package com.adjust.api.service.dto;

import java.util.List;

public class DummyConversationDTO extends ConversationDTO {

    private List<DummyChatMessageDTO> messages;

    public DummyConversationDTO() {
    }

    public DummyConversationDTO(ConversationDTO conversationDTO) {
        this.setId(conversationDTO.getId());
        this.setAdjustClientId(conversationDTO.getAdjustClientId());
        this.setSpecialistId(conversationDTO.getSpecialistId());
    }

    public List<DummyChatMessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<DummyChatMessageDTO> messages) {
        this.messages = messages;
    }

}
