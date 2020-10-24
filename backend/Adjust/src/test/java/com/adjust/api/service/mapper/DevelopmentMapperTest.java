package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DevelopmentMapperTest {

    private DevelopmentMapper developmentMapper;

    @BeforeEach
    public void setUp() {
        developmentMapper = new DevelopmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(developmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(developmentMapper.fromId(null)).isNull();
    }
}
