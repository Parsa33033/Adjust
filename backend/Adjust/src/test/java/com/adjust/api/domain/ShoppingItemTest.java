package com.adjust.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.adjust.api.web.rest.TestUtil;

public class ShoppingItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoppingItem.class);
        ShoppingItem shoppingItem1 = new ShoppingItem();
        shoppingItem1.setId(1L);
        ShoppingItem shoppingItem2 = new ShoppingItem();
        shoppingItem2.setId(shoppingItem1.getId());
        assertThat(shoppingItem1).isEqualTo(shoppingItem2);
        shoppingItem2.setId(2L);
        assertThat(shoppingItem1).isNotEqualTo(shoppingItem2);
        shoppingItem1.setId(null);
        assertThat(shoppingItem1).isNotEqualTo(shoppingItem2);
    }
}
