package domain;

import java.util.Objects;

public class Order {
    private int productNumber;
    private int quantity;

    private Order(int productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public static Order of(int productNumber, int quantity) {
        return new Order(productNumber, quantity);
    }

    public int productNumber() {
        return productNumber;
    }

    public int quantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return productNumber == order.productNumber && quantity == order.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNumber, quantity);
    }

}
