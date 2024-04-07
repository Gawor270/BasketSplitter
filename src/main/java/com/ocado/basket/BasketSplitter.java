package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketSplitter {
    private final Map<String, List<String>> deliveryConfig;

    public BasketSplitter(String absolutePathToConfigFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        this.deliveryConfig = mapper.readValue(new File(absolutePathToConfigFile), new TypeReference<Map<String, List<String>>>(){});
    }

    public Map<String, List<String>> split(List<String> items) {
        Graph graph = new Graph(deliveryConfig, items);
        Map<String, List<String>> result = new HashMap<>();
        while (true) {
            var max = graph.getMax();
            if (max.isEmpty()) {
                break;
            }
            result.putAll(max.get());
        }
        return result;
    }
}
