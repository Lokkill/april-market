package ru.geekbrains.april.market.data_mapping;

import ru.geekbrains.april.market.models.Product;

import java.util.HashMap;
import java.util.Map;

public class IdentityMap {
    private static Map<Long, Product> productMap = new HashMap<>();

    public static void addProduct(Product product){
        productMap.put(product.getId(), product);
    }

    public static Product getProduct(Long id){
        return productMap.get(id);
    }
}
