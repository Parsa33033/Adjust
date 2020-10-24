package com.adjust.api.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicalProfileMapperTest {

    private MedicalProfileMapper medicalProfileMapper;

    @BeforeEach
    public void setUp() {
        medicalProfileMapper = new MedicalProfileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicalProfileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalProfileMapper.fromId(null)).isNull();
    }
}
