package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProgramDescriptionMapperTest {

    private ProgramDescriptionMapper programDescriptionMapper;

    @BeforeEach
    public void setUp() {
        programDescriptionMapper = new ProgramDescriptionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(programDescriptionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(programDescriptionMapper.fromId(null)).isNull();
    }
}
