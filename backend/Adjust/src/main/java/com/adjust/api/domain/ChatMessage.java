package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ChatMessage.
 */
@Entity
@Table(name = "chat_message")
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Lob
    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Lob
    @Column(name = "voice")
    private byte[] voice;

    @Column(name = "voice_content_type")
    private String voiceContentType;

    @Column(name = "seen")
    private Boolean seen;

    @ManyToOne
    @JsonIgnoreProperties(value = "messages", allowSetters = true)
    private Conversation conversation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public ChatMessage sender(String sender) {
        this.sender = sender;
        return this;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public ChatMessage receiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public ChatMessage text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public ChatMessage image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public ChatMessage imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public byte[] getVoice() {
        return voice;
    }

    public ChatMessage voice(byte[] voice) {
        this.voice = voice;
        return this;
    }

    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    public String getVoiceContentType() {
        return voiceContentType;
    }

    public ChatMessage voiceContentType(String voiceContentType) {
        this.voiceContentType = voiceContentType;
        return this;
    }

    public void setVoiceContentType(String voiceContentType) {
        this.voiceContentType = voiceContentType;
    }

    public Boolean isSeen() {
        return seen;
    }

    public ChatMessage seen(Boolean seen) {
        this.seen = seen;
        return this;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public ChatMessage conversation(Conversation conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChatMessage)) {
            return false;
        }
        return id != null && id.equals(((ChatMessage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChatMessage{" +
            "id=" + getId() +
            ", sender='" + getSender() + "'" +
            ", receiver='" + getReceiver() + "'" +
            ", text='" + getText() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", voice='" + getVoice() + "'" +
            ", voiceContentType='" + getVoiceContentType() + "'" +
            ", seen='" + isSeen() + "'" +
            "}";
    }
}
