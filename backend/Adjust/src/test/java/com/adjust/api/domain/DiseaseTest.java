package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class DiseaseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Disease.class);
        Disease disease1 = new Disease();
        disease1.setId(1L);
        Disease disease2 = new Disease();
        disease2.setId(disease1.getId());
        assertThat(disease1).isEqualTo(disease2);
        disease2.setId(2L);
        assertThat(disease1).isNotEqualTo(disease2);
        disease1.setId(null);
        assertThat(disease1).isNotEqualTo(disease2);
    }
}
