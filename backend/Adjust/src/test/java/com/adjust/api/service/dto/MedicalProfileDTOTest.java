package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class MedicalProfileDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalProfileDTO.class);
        MedicalProfileDTO medicalProfileDTO1 = new MedicalProfileDTO();
        medicalProfileDTO1.setId(1L);
        MedicalProfileDTO medicalProfileDTO2 = new MedicalProfileDTO();
        assertThat(medicalProfileDTO1).isNotEqualTo(medicalProfileDTO2);
        medicalProfileDTO2.setId(medicalProfileDTO1.getId());
        assertThat(medicalProfileDTO1).isEqualTo(medicalProfileDTO2);
        medicalProfileDTO2.setId(2L);
        assertThat(medicalProfileDTO1).isNotEqualTo(medicalProfileDTO2);
        medicalProfileDTO1.setId(null);
        assertThat(medicalProfileDTO1).isNotEqualTo(medicalProfileDTO2);
    }
}
