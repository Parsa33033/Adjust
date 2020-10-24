package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class DevelopmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Development.class);
        Development development1 = new Development();
        development1.setId(1L);
        Development development2 = new Development();
        development2.setId(development1.getId());
        assertThat(development1).isEqualTo(development2);
        development2.setId(2L);
        assertThat(development1).isNotEqualTo(development2);
        development1.setId(null);
        assertThat(development1).isNotEqualTo(development2);
    }
}
