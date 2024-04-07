package com.ocado.basket;
import java.util.*;

public class Graph {
    private Map<String, List<String>> productsToSuppliers;
    private Map<String, List<String>> suppliersToProducts;
    private PriorityQueue<String> suppliersQueue;

    public Graph(Map<String, List<String>> deliveryConfig, List<String> products) {
        productsToSuppliers = new HashMap<>();
        suppliersToProducts = new HashMap<>();
        suppliersQueue = new PriorityQueue<>((s1, s2) -> suppliersToProducts.get(s2).size() - suppliersToProducts.get(s1).size());

        for (String product : products) {
            productsToSuppliers.put(product, deliveryConfig.get(product));
            for (String neighbour : deliveryConfig.get(product)) {
                if (!suppliersToProducts.containsKey(neighbour)) {
                    suppliersToProducts.put(neighbour, new ArrayList<>());
                }
                suppliersToProducts.get(neighbour).add(product);
            }
        }

        suppliersQueue.addAll(suppliersToProducts.keySet());
    }

    public Optional<Map<String, List<String>>> getMax(){
        Map<String, List<String>> result = new HashMap<>();

        while (!suppliersQueue.isEmpty()) {
            String supplier = suppliersQueue.poll();
            if (suppliersToProducts.get(supplier).size() > 0) {
                result.put(supplier, suppliersToProducts.get(supplier));
                removeEdges(result.get(supplier));
                return Optional.of(result);
            }
        }

        return Optional.empty();
    }

    private void removeEdges(List<String> products) {
        for (String product : products) {
            List<String> neighbours = new ArrayList<>(productsToSuppliers.get(product));
            for (String neighbour : neighbours) {
                List<String> supplierProducts = new ArrayList<>(suppliersToProducts.get(neighbour));
                supplierProducts.remove(product);
                suppliersToProducts.put(neighbour, supplierProducts);
                suppliersQueue.remove(neighbour);
                suppliersQueue.add(neighbour);
            }
        }
    }
}
