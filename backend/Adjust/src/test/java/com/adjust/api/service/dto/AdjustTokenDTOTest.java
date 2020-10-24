package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class AdjustTokenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjustTokenDTO.class);
        AdjustTokenDTO adjustTokenDTO1 = new AdjustTokenDTO();
        adjustTokenDTO1.setId(1L);
        AdjustTokenDTO adjustTokenDTO2 = new AdjustTokenDTO();
        assertThat(adjustTokenDTO1).isNotEqualTo(adjustTokenDTO2);
        adjustTokenDTO2.setId(adjustTokenDTO1.getId());
        assertThat(adjustTokenDTO1).isEqualTo(adjustTokenDTO2);
        adjustTokenDTO2.setId(2L);
        assertThat(adjustTokenDTO1).isNotEqualTo(adjustTokenDTO2);
        adjustTokenDTO1.setId(null);
        assertThat(adjustTokenDTO1).isNotEqualTo(adjustTokenDTO2);
    }
}
