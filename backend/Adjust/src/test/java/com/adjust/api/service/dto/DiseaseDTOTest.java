package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class DiseaseDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiseaseDTO.class);
        DiseaseDTO diseaseDTO1 = new DiseaseDTO();
        diseaseDTO1.setId(1L);
        DiseaseDTO diseaseDTO2 = new DiseaseDTO();
        assertThat(diseaseDTO1).isNotEqualTo(diseaseDTO2);
        diseaseDTO2.setId(diseaseDTO1.getId());
        assertThat(diseaseDTO1).isEqualTo(diseaseDTO2);
        diseaseDTO2.setId(2L);
        assertThat(diseaseDTO1).isNotEqualTo(diseaseDTO2);
        diseaseDTO1.setId(null);
        assertThat(diseaseDTO1).isNotEqualTo(diseaseDTO2);
    }
}
