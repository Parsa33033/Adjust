package com.adjust.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Conversation.
 */
@Entity
@Table(name = "conversation")
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.EAGER)
    private Set<ChatMessage> messages = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "conversations", allowSetters = true)
    private AdjustClient adjustClient;

    @ManyToOne
    @JsonIgnoreProperties(value = "conversations", allowSetters = true)
    private Specialist specialist;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ChatMessage> getMessages() {
        return messages;
    }

    public Conversation messages(Set<ChatMessage> chatMessages) {
        this.messages = chatMessages;
        return this;
    }

    public Conversation addMessages(ChatMessage chatMessage) {
        this.messages.add(chatMessage);
        chatMessage.setConversation(this);
        return this;
    }

    public Conversation removeMessages(ChatMessage chatMessage) {
        this.messages.remove(chatMessage);
        chatMessage.setConversation(null);
        return this;
    }

    public void setMessages(Set<ChatMessage> chatMessages) {
        this.messages = chatMessages;
    }

    public AdjustClient getAdjustClient() {
        return adjustClient;
    }

    public Conversation adjustClient(AdjustClient adjustClient) {
        this.adjustClient = adjustClient;
        return this;
    }

    public void setAdjustClient(AdjustClient adjustClient) {
        this.adjustClient = adjustClient;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public Conversation specialist(Specialist specialist) {
        this.specialist = specialist;
        return this;
    }

    public void setSpecialist(Specialist specialist) {
        this.specialist = specialist;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Conversation)) {
            return false;
        }
        return id != null && id.equals(((Conversation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Conversation{" +
            "id=" + getId() +
            "}";
    }
}
