package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ProgramDescriptionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramDescription.class);
        ProgramDescription programDescription1 = new ProgramDescription();
        programDescription1.setId(1L);
        ProgramDescription programDescription2 = new ProgramDescription();
        programDescription2.setId(programDescription1.getId());
        assertThat(programDescription1).isEqualTo(programDescription2);
        programDescription2.setId(2L);
        assertThat(programDescription1).isNotEqualTo(programDescription2);
        programDescription1.setId(null);
        assertThat(programDescription1).isNotEqualTo(programDescription2);
    }
}
