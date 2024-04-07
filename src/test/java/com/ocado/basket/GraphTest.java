package com.ocado.basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    private Graph graph;
    private Map<String, List<String>> deliveryConfig;
    private List<String> products;

    @BeforeEach
    public void setup() {
        deliveryConfig = new HashMap<>();
        deliveryConfig.put("product1", Arrays.asList("supplier1", "supplier2"));
        deliveryConfig.put("product2", Arrays.asList("supplier1", "supplier3"));
        deliveryConfig.put("product3", Arrays.asList("supplier2", "supplier3"));
        deliveryConfig.put("product4", Arrays.asList("supplier1"));

        products = Arrays.asList("product1", "product2", "product3", "product4");

        graph = new Graph(deliveryConfig, products);
    }

    @Test
    public void testConstructor() {
        assertNotNull(graph);
    }

    @Test
    public void testGetMax() {
        Optional<Map<String, List<String>>> max = graph.getMax();
        assertTrue(max.isPresent());
        assertEquals(1, max.get().size());
        assertEquals(3, max.get().get("supplier1").size());
    }

    @Test
    public void testRemoveEdges() {
        graph.getMax(); // This will call removeEdges internally
        Optional<Map<String, List<String>>> maxAfterRemove = graph.getMax();
        assertTrue(maxAfterRemove.isPresent());
        assertEquals(1, maxAfterRemove.get().size());
    }
}
