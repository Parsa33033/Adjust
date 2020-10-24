package com.adjust.api.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.adjust.api.domain.ChatMessage} entity.
 */
public class ChatMessageDTO implements Serializable {
    
    private Long id;

    private String sender;

    private String receiver;

    @Lob
    private String text;

    @Lob
    private byte[] image;

    private String imageContentType;
    @Lob
    private byte[] voice;

    private String voiceContentType;
    private Boolean seen;


    private Long conversationId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public byte[] getVoice() {
        return voice;
    }

    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    public String getVoiceContentType() {
        return voiceContentType;
    }

    public void setVoiceContentType(String voiceContentType) {
        this.voiceContentType = voiceContentType;
    }

    public Boolean isSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatMessageDTO)) {
            return false;
        }

        return id != null && id.equals(((ChatMessageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChatMessageDTO{" +
            "id=" + getId() +
            ", sender='" + getSender() + "'" +
            ", receiver='" + getReceiver() + "'" +
            ", text='" + getText() + "'" +
            ", image='" + getImage() + "'" +
            ", voice='" + getVoice() + "'" +
            ", seen='" + isSeen() + "'" +
            ", conversationId=" + getConversationId() +
            "}";
    }
}
