package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustTokenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustToken.class);
        AdjustToken adjustToken1 = new AdjustToken();
        adjustToken1.setId(1L);
        AdjustToken adjustToken2 = new AdjustToken();
        adjustToken2.setId(adjustToken1.getId());
        assertThat(adjustToken1).isEqualTo(adjustToken2);
        adjustToken2.setId(2L);
        assertThat(adjustToken1).isNotEqualTo(adjustToken2);
        adjustToken1.setId(null);
        assertThat(adjustToken1).isNotEqualTo(adjustToken2);
    }
}
