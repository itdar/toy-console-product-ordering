package domain.order;

import domain.product.ProductNumber;

import java.util.Objects;

public class Order {
    private ProductNumber productNumber;
    private int quantity;

    private Order(int productNumber, int quantity) {
        this.productNumber = ProductNumber.of(productNumber);
        this.quantity = quantity;
    }

    public static Order of(int productNumber, int quantity) {
        return new Order(productNumber, quantity);
    }

    public int quantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return quantity == order.quantity && Objects.equals(productNumber, order.productNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNumber, quantity);
    }
}
