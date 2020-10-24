package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class MedicalProfileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalProfile.class);
        MedicalProfile medicalProfile1 = new MedicalProfile();
        medicalProfile1.setId(1L);
        MedicalProfile medicalProfile2 = new MedicalProfile();
        medicalProfile2.setId(medicalProfile1.getId());
        assertThat(medicalProfile1).isEqualTo(medicalProfile2);
        medicalProfile2.setId(2L);
        assertThat(medicalProfile1).isNotEqualTo(medicalProfile2);
        medicalProfile1.setId(null);
        assertThat(medicalProfile1).isNotEqualTo(medicalProfile2);
    }
}
