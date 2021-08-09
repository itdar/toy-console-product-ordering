package domain;

import java.util.Objects;

public class Order {
    private int productNumber;
    private int quantity;

    public Order(int productNumber, int quantity) {
        this.productNumber = productNumber;
        this.quantity = quantity;
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
