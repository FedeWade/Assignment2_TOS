package it.unipd.tos.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MenuItemTest {

    @Test
    public void MenuItem_GetterMethods_Test() {
        MenuItem item =new MenuItem("Coppa Bella", MenuItem.items.Gelato, 3.00);

        assertEquals("Coppa Bella", item.getName());
        assertEquals(MenuItem.items.Gelato, item.getType());
        assertEquals(3.00, item.getPrice(), 0.0);
    }
}
