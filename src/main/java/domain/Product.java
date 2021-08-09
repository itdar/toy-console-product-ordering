package domain;

import java.util.Objects;

public class Product {
    private int number;
    private String name;
    private int price;
    private int remainedCount;

    public Product(int number, String name, int price, int remainedCount) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.remainedCount = remainedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return number == product.number && price == product.price && remainedCount == product.remainedCount && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, price, remainedCount);
    }
}
