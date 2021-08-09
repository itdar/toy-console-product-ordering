package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OrderProducts {
    private List<OrderProduct> orderProducts;

    public OrderProducts(Products products, Orders orders) {
        orderProducts = new ArrayList<>();

        Order order;
        Product product;
        List<Order> orderList = orders.get();
        for (int i = 0; i < orderList.size(); ++i) {
            order = orderList.get(i);
            product = products.findByNumber(order.productNumber());

            orderProducts.add(OrderProduct.of(product, order));
        }
    }

    public Stream<OrderProduct> stream() {
        return orderProducts.stream();
    }

    public int totalOrderPrice() {
        return orderProducts.stream()
                .mapToInt(OrderProduct::price)
                .sum();
    }

    public int finalPayPrice() {
        int totalOrderPrice = totalOrderPrice();
        if (totalOrderPrice < 50000) {
            return totalOrderPrice + 2500;
        }
        return totalOrderPrice;
    }
}
