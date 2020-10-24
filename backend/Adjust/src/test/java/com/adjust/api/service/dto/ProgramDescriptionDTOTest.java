package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ProgramDescriptionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramDescriptionDTO.class);
        ProgramDescriptionDTO programDescriptionDTO1 = new ProgramDescriptionDTO();
        programDescriptionDTO1.setId(1L);
        ProgramDescriptionDTO programDescriptionDTO2 = new ProgramDescriptionDTO();
        assertThat(programDescriptionDTO1).isNotEqualTo(programDescriptionDTO2);
        programDescriptionDTO2.setId(programDescriptionDTO1.getId());
        assertThat(programDescriptionDTO1).isEqualTo(programDescriptionDTO2);
        programDescriptionDTO2.setId(2L);
        assertThat(programDescriptionDTO1).isNotEqualTo(programDescriptionDTO2);
        programDescriptionDTO1.setId(null);
        assertThat(programDescriptionDTO1).isNotEqualTo(programDescriptionDTO2);
    }
}
