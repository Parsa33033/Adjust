package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DiseaseMapperTest {

    private DiseaseMapper diseaseMapper;

    @BeforeEach
    public void setUp() {
        diseaseMapper = new DiseaseMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(diseaseMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(diseaseMapper.fromId(null)).isNull();
    }
}
