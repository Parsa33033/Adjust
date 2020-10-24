package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingItemMapperTest {

    private ShoppingItemMapper shoppingItemMapper;

    @BeforeEach
    public void setUp() {
        shoppingItemMapper = new ShoppingItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(shoppingItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shoppingItemMapper.fromId(null)).isNull();
    }
}
