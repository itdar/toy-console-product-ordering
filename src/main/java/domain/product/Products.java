package domain.product;

import java.util.*;
import java.util.stream.Stream;

public class Products {
    private Map<Integer, Product> productMap;

    public Products(List<Product> products) {
        productMap = new HashMap<>();
        for (Product product : products) {
            productMap.put(product.number(), product);
        }
        productMap = Collections.unmodifiableMap(productMap);
    }

    public Stream<Product> stream() {
        return productMap.values().stream();
    }

    public Product findByNumber(int productNumber) {
        if (!productMap.containsKey(productNumber)) {
            throw new NoSuchElementException("등록되지 않은 상품 번호");
        }
        return productMap.get(productNumber);
    }
}
