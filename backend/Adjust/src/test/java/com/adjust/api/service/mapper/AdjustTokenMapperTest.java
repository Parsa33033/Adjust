package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjustTokenMapperTest {

    private AdjustTokenMapper adjustTokenMapper;

    @BeforeEach
    public void setUp() {
        adjustTokenMapper = new AdjustTokenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjustTokenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjustTokenMapper.fromId(null)).isNull();
    }
}
