package com.adjust.api.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ShoppingItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoppingItemDTO.class);
        ShoppingItemDTO shoppingItemDTO1 = new ShoppingItemDTO();
        shoppingItemDTO1.setId(1L);
        ShoppingItemDTO shoppingItemDTO2 = new ShoppingItemDTO();
        assertThat(shoppingItemDTO1).isNotEqualTo(shoppingItemDTO2);
        shoppingItemDTO2.setId(shoppingItemDTO1.getId());
        assertThat(shoppingItemDTO1).isEqualTo(shoppingItemDTO2);
        shoppingItemDTO2.setId(2L);
        assertThat(shoppingItemDTO1).isNotEqualTo(shoppingItemDTO2);
        shoppingItemDTO1.setId(null);
        assertThat(shoppingItemDTO1).isNotEqualTo(shoppingItemDTO2);
    }
}
