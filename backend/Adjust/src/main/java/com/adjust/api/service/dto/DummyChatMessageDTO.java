package com.adjust.api.service.dto;

public class DummyChatMessageDTO extends ChatMessageDTO {

    public DummyChatMessageDTO() {
    }

    public DummyChatMessageDTO(ChatMessageDTO chatMessageDTO) {
        this.setId(chatMessageDTO.getId());
        this.setConversationId(chatMessageDTO.getConversationId());
        this.setImage(chatMessageDTO.getImage());
        this.setImageContentType(chatMessageDTO.getImageContentType());
        this.setReceiver(chatMessageDTO.getReceiver());
        this.setSeen(chatMessageDTO.isSeen());
        this.setSender(chatMessageDTO.getSender());
        this.setText(chatMessageDTO.getText());
        this.setVoice(chatMessageDTO.getVoice());
        this.setVoiceContentType(chatMessageDTO.getVoiceContentType());
    }
}
