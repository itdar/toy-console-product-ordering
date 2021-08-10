package kr.co._29cm.homework.domain.product;

import kr.co._29cm.homework.exception.SoldOutException;

import java.util.Objects;

public class Product {
    private ProductNumber number;
    private String name;
    private int price;
    private int remainedCount;

    private Product(int number, String name, int price, int remainedCount) {
        this.number = ProductNumber.of(number);
        this.name = name;
        this.price = price;
        this.remainedCount = remainedCount;
    }

    public static Product of(int number, String name, int price, int remainedCount) {
        return new Product(number, name, price, remainedCount);
    }

    public boolean validateRemainedCount(int orderQuantity) throws SoldOutException {
        if (remainedCount >= orderQuantity) {
            return true;
        }
        throw new SoldOutException();
    }

    public String name() {
        return name;
    }

    public int number() {
        return number.get();
    }

    public int price() {
        return price;
    }

    @Override
    public String toString() {
        return number + "\t" +
                name + "\t" +
                price + "\t" +
                remainedCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && remainedCount == product.remainedCount && Objects.equals(number, product.number) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, price, remainedCount);
    }

}
