package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.ChatMessage;
import com.adjust.api.repository.ChatMessageRepository;
import com.adjust.api.service.ChatMessageService;
import com.adjust.api.service.dto.ChatMessageDTO;
import com.adjust.api.service.mapper.ChatMessageMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ChatMessageResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ChatMessageResourceIT {

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_VOICE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VOICE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VOICE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VOICE_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_SEEN = false;
    private static final Boolean UPDATED_SEEN = true;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChatMessageMockMvc;

    private ChatMessage chatMessage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChatMessage createEntity(EntityManager em) {
        ChatMessage chatMessage = new ChatMessage()
            .sender(DEFAULT_SENDER)
            .receiver(DEFAULT_RECEIVER)
            .text(DEFAULT_TEXT)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .voice(DEFAULT_VOICE)
            .voiceContentType(DEFAULT_VOICE_CONTENT_TYPE)
            .seen(DEFAULT_SEEN);
        return chatMessage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChatMessage createUpdatedEntity(EntityManager em) {
        ChatMessage chatMessage = new ChatMessage()
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .text(UPDATED_TEXT)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .voice(UPDATED_VOICE)
            .voiceContentType(UPDATED_VOICE_CONTENT_TYPE)
            .seen(UPDATED_SEEN);
        return chatMessage;
    }

    @BeforeEach
    public void initTest() {
        chatMessage = createEntity(em);
    }

    @Test
    @Transactional
    public void createChatMessage() throws Exception {
        int databaseSizeBeforeCreate = chatMessageRepository.findAll().size();
        // Create the ChatMessage
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDto(chatMessage);
        restChatMessageMockMvc.perform(post("/api/chat-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chatMessageDTO)))
            .andExpect(status().isCreated());

        // Validate the ChatMessage in the database
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeCreate + 1);
        ChatMessage testChatMessage = chatMessageList.get(chatMessageList.size() - 1);
        assertThat(testChatMessage.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testChatMessage.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testChatMessage.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testChatMessage.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testChatMessage.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testChatMessage.getVoice()).isEqualTo(DEFAULT_VOICE);
        assertThat(testChatMessage.getVoiceContentType()).isEqualTo(DEFAULT_VOICE_CONTENT_TYPE);
        assertThat(testChatMessage.isSeen()).isEqualTo(DEFAULT_SEEN);
    }

    @Test
    @Transactional
    public void createChatMessageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chatMessageRepository.findAll().size();

        // Create the ChatMessage with an existing ID
        chatMessage.setId(1L);
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDto(chatMessage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChatMessageMockMvc.perform(post("/api/chat-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chatMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChatMessage in the database
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllChatMessages() throws Exception {
        // Initialize the database
        chatMessageRepository.saveAndFlush(chatMessage);

        // Get all the chatMessageList
        restChatMessageMockMvc.perform(get("/api/chat-messages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatMessage.getId().intValue())))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].voiceContentType").value(hasItem(DEFAULT_VOICE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].voice").value(hasItem(Base64Utils.encodeToString(DEFAULT_VOICE))))
            .andExpect(jsonPath("$.[*].seen").value(hasItem(DEFAULT_SEEN.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getChatMessage() throws Exception {
        // Initialize the database
        chatMessageRepository.saveAndFlush(chatMessage);

        // Get the chatMessage
        restChatMessageMockMvc.perform(get("/api/chat-messages/{id}", chatMessage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chatMessage.getId().intValue()))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.voiceContentType").value(DEFAULT_VOICE_CONTENT_TYPE))
            .andExpect(jsonPath("$.voice").value(Base64Utils.encodeToString(DEFAULT_VOICE)))
            .andExpect(jsonPath("$.seen").value(DEFAULT_SEEN.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingChatMessage() throws Exception {
        // Get the chatMessage
        restChatMessageMockMvc.perform(get("/api/chat-messages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChatMessage() throws Exception {
        // Initialize the database
        chatMessageRepository.saveAndFlush(chatMessage);

        int databaseSizeBeforeUpdate = chatMessageRepository.findAll().size();

        // Update the chatMessage
        ChatMessage updatedChatMessage = chatMessageRepository.findById(chatMessage.getId()).get();
        // Disconnect from session so that the updates on updatedChatMessage are not directly saved in db
        em.detach(updatedChatMessage);
        updatedChatMessage
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .text(UPDATED_TEXT)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .voice(UPDATED_VOICE)
            .voiceContentType(UPDATED_VOICE_CONTENT_TYPE)
            .seen(UPDATED_SEEN);
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDto(updatedChatMessage);

        restChatMessageMockMvc.perform(put("/api/chat-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chatMessageDTO)))
            .andExpect(status().isOk());

        // Validate the ChatMessage in the database
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeUpdate);
        ChatMessage testChatMessage = chatMessageList.get(chatMessageList.size() - 1);
        assertThat(testChatMessage.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testChatMessage.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testChatMessage.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testChatMessage.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testChatMessage.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testChatMessage.getVoice()).isEqualTo(UPDATED_VOICE);
        assertThat(testChatMessage.getVoiceContentType()).isEqualTo(UPDATED_VOICE_CONTENT_TYPE);
        assertThat(testChatMessage.isSeen()).isEqualTo(UPDATED_SEEN);
    }

    @Test
    @Transactional
    public void updateNonExistingChatMessage() throws Exception {
        int databaseSizeBeforeUpdate = chatMessageRepository.findAll().size();

        // Create the ChatMessage
        ChatMessageDTO chatMessageDTO = chatMessageMapper.toDto(chatMessage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChatMessageMockMvc.perform(put("/api/chat-messages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(chatMessageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ChatMessage in the database
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChatMessage() throws Exception {
        // Initialize the database
        chatMessageRepository.saveAndFlush(chatMessage);

        int databaseSizeBeforeDelete = chatMessageRepository.findAll().size();

        // Delete the chatMessage
        restChatMessageMockMvc.perform(delete("/api/chat-messages/{id}", chatMessage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChatMessage> chatMessageList = chatMessageRepository.findAll();
        assertThat(chatMessageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
