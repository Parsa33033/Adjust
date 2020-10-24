package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class DevelopmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DevelopmentDTO.class);
        DevelopmentDTO developmentDTO1 = new DevelopmentDTO();
        developmentDTO1.setId(1L);
        DevelopmentDTO developmentDTO2 = new DevelopmentDTO();
        assertThat(developmentDTO1).isNotEqualTo(developmentDTO2);
        developmentDTO2.setId(developmentDTO1.getId());
        assertThat(developmentDTO1).isEqualTo(developmentDTO2);
        developmentDTO2.setId(2L);
        assertThat(developmentDTO1).isNotEqualTo(developmentDTO2);
        developmentDTO1.setId(null);
        assertThat(developmentDTO1).isNotEqualTo(developmentDTO2);
    }
}
