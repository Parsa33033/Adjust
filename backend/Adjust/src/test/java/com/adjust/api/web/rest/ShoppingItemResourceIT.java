package com.adjust.api.web.rest;

import com.adjust.api.AdjustApp;
import com.adjust.api.domain.ShoppingItem;
import com.adjust.api.repository.ShoppingItemRepository;
import com.adjust.api.service.ShoppingItemService;
import com.adjust.api.service.dto.ShoppingItemDTO;
import com.adjust.api.service.mapper.ShoppingItemMapper;

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
 * Integration tests for the {@link ShoppingItemResource} REST controller.
 */
@SpringBootTest(classes = AdjustApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ShoppingItemResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_ITEM_ID = 1L;
    private static final Long UPDATED_ITEM_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final Boolean DEFAULT_ORDERABLE = false;
    private static final Boolean UPDATED_ORDERABLE = true;

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    @Autowired
    private ShoppingItemRepository shoppingItemRepository;

    @Autowired
    private ShoppingItemMapper shoppingItemMapper;

    @Autowired
    private ShoppingItemService shoppingItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShoppingItemMockMvc;

    private ShoppingItem shoppingItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShoppingItem createEntity(EntityManager em) {
        ShoppingItem shoppingItem = new ShoppingItem()
            .name(DEFAULT_NAME)
            .itemId(DEFAULT_ITEM_ID)
            .description(DEFAULT_DESCRIPTION)
            .price(DEFAULT_PRICE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .orderable(DEFAULT_ORDERABLE)
            .number(DEFAULT_NUMBER);
        return shoppingItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShoppingItem createUpdatedEntity(EntityManager em) {
        ShoppingItem shoppingItem = new ShoppingItem()
            .name(UPDATED_NAME)
            .itemId(UPDATED_ITEM_ID)
            .description(UPDATED_DESCRIPTION)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .orderable(UPDATED_ORDERABLE)
            .number(UPDATED_NUMBER);
        return shoppingItem;
    }

    @BeforeEach
    public void initTest() {
        shoppingItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createShoppingItem() throws Exception {
        int databaseSizeBeforeCreate = shoppingItemRepository.findAll().size();
        // Create the ShoppingItem
        ShoppingItemDTO shoppingItemDTO = shoppingItemMapper.toDto(shoppingItem);
        restShoppingItemMockMvc.perform(post("/api/shopping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shoppingItemDTO)))
            .andExpect(status().isCreated());

        // Validate the ShoppingItem in the database
        List<ShoppingItem> shoppingItemList = shoppingItemRepository.findAll();
        assertThat(shoppingItemList).hasSize(databaseSizeBeforeCreate + 1);
        ShoppingItem testShoppingItem = shoppingItemList.get(shoppingItemList.size() - 1);
        assertThat(testShoppingItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testShoppingItem.getItemId()).isEqualTo(DEFAULT_ITEM_ID);
        assertThat(testShoppingItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testShoppingItem.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testShoppingItem.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testShoppingItem.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testShoppingItem.isOrderable()).isEqualTo(DEFAULT_ORDERABLE);
        assertThat(testShoppingItem.getNumber()).isEqualTo(DEFAULT_NUMBER);
    }

    @Test
    @Transactional
    public void createShoppingItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shoppingItemRepository.findAll().size();

        // Create the ShoppingItem with an existing ID
        shoppingItem.setId(1L);
        ShoppingItemDTO shoppingItemDTO = shoppingItemMapper.toDto(shoppingItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShoppingItemMockMvc.perform(post("/api/shopping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shoppingItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShoppingItem in the database
        List<ShoppingItem> shoppingItemList = shoppingItemRepository.findAll();
        assertThat(shoppingItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShoppingItems() throws Exception {
        // Initialize the database
        shoppingItemRepository.saveAndFlush(shoppingItem);

        // Get all the shoppingItemList
        restShoppingItemMockMvc.perform(get("/api/shopping-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shoppingItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].itemId").value(hasItem(DEFAULT_ITEM_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].orderable").value(hasItem(DEFAULT_ORDERABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getShoppingItem() throws Exception {
        // Initialize the database
        shoppingItemRepository.saveAndFlush(shoppingItem);

        // Get the shoppingItem
        restShoppingItemMockMvc.perform(get("/api/shopping-items/{id}", shoppingItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shoppingItem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.itemId").value(DEFAULT_ITEM_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.orderable").value(DEFAULT_ORDERABLE.booleanValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER));
    }
    @Test
    @Transactional
    public void getNonExistingShoppingItem() throws Exception {
        // Get the shoppingItem
        restShoppingItemMockMvc.perform(get("/api/shopping-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShoppingItem() throws Exception {
        // Initialize the database
        shoppingItemRepository.saveAndFlush(shoppingItem);

        int databaseSizeBeforeUpdate = shoppingItemRepository.findAll().size();

        // Update the shoppingItem
        ShoppingItem updatedShoppingItem = shoppingItemRepository.findById(shoppingItem.getId()).get();
        // Disconnect from session so that the updates on updatedShoppingItem are not directly saved in db
        em.detach(updatedShoppingItem);
        updatedShoppingItem
            .name(UPDATED_NAME)
            .itemId(UPDATED_ITEM_ID)
            .description(UPDATED_DESCRIPTION)
            .price(UPDATED_PRICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .orderable(UPDATED_ORDERABLE)
            .number(UPDATED_NUMBER);
        ShoppingItemDTO shoppingItemDTO = shoppingItemMapper.toDto(updatedShoppingItem);

        restShoppingItemMockMvc.perform(put("/api/shopping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shoppingItemDTO)))
            .andExpect(status().isOk());

        // Validate the ShoppingItem in the database
        List<ShoppingItem> shoppingItemList = shoppingItemRepository.findAll();
        assertThat(shoppingItemList).hasSize(databaseSizeBeforeUpdate);
        ShoppingItem testShoppingItem = shoppingItemList.get(shoppingItemList.size() - 1);
        assertThat(testShoppingItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShoppingItem.getItemId()).isEqualTo(UPDATED_ITEM_ID);
        assertThat(testShoppingItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testShoppingItem.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testShoppingItem.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testShoppingItem.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testShoppingItem.isOrderable()).isEqualTo(UPDATED_ORDERABLE);
        assertThat(testShoppingItem.getNumber()).isEqualTo(UPDATED_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingShoppingItem() throws Exception {
        int databaseSizeBeforeUpdate = shoppingItemRepository.findAll().size();

        // Create the ShoppingItem
        ShoppingItemDTO shoppingItemDTO = shoppingItemMapper.toDto(shoppingItem);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShoppingItemMockMvc.perform(put("/api/shopping-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shoppingItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShoppingItem in the database
        List<ShoppingItem> shoppingItemList = shoppingItemRepository.findAll();
        assertThat(shoppingItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShoppingItem() throws Exception {
        // Initialize the database
        shoppingItemRepository.saveAndFlush(shoppingItem);

        int databaseSizeBeforeDelete = shoppingItemRepository.findAll().size();

        // Delete the shoppingItem
        restShoppingItemMockMvc.perform(delete("/api/shopping-items/{id}", shoppingItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShoppingItem> shoppingItemList = shoppingItemRepository.findAll();
        assertThat(shoppingItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
