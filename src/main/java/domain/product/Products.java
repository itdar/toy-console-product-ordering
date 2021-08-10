package domain.product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

public class Products {
    List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public Stream<Product> stream() {
        return this.products.stream();
    }

    public Product findByNumber(int productNumber) {
        return products.stream()
                .filter(product -> Objects.equals(product.number(), productNumber))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
