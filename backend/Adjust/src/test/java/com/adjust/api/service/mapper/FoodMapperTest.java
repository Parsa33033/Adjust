package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FoodMapperTest {

    private FoodMapper foodMapper;

    @BeforeEach
    public void setUp() {
        foodMapper = new FoodMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(foodMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(foodMapper.fromId(null)).isNull();
    }
}
