package com.ocado.basket;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketSplitterTest {

    @Test
    public void testBasket1() throws IOException {
        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");
        List<String> items = Arrays.asList("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht");

        Map<String, List<String>> result = splitter.split(items);

        assertEquals(2, result.size());
        assertEquals(5, result.get("Courier").size());
    }

    @Test
    public void testBasket2() throws IOException {
        BasketSplitter splitter = new BasketSplitter("src/main/resources/config.json");
        List<String> items = Arrays.asList("Fond - Chocolate", "Chocolate - Unsweetened",
                                        "Nut - Almond, Blanched, Whole", "Haggis",
                                        "Mushroom - Porcini Frozen", "Cake - Miini Cheesecake Cherry",
                                        "Sauce - Mint", "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear",
                                        "Puree - Strawberry", "Numi - Assorted Teas", "Apples - Spartan", "Garlic - Peeled",
                                        "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea");

        Map<String, List<String>> result = splitter.split(items);

        assertEquals(3, result.size());
        assertEquals(13, result.get("Express Collection").size());
    }
}
