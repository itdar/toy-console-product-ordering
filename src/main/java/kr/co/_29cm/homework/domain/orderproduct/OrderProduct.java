package kr.co._29cm.homework.domain.orderproduct;

import kr.co._29cm.homework.domain.order.Order;
import kr.co._29cm.homework.domain.product.Product;

public class OrderProduct {
    private String name;
    private int quantity;
    private int price;

    private OrderProduct(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderProduct of(String name, int quantity, int price) {
        return new OrderProduct(name, quantity, price);
    }

    public static OrderProduct of(Product product, Order order) {
        return new OrderProduct(
                product.name(),
                order.quantity(),
                product.price() * order.quantity()
        );
    }

    public int price() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - " +
                quantity + "개";
    }
}
