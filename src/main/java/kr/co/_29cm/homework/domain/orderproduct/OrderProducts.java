package kr.co._29cm.homework.domain.orderproduct;

import kr.co._29cm.homework.domain.order.Orders;
import kr.co._29cm.homework.domain.product.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static kr.co._29cm.homework.domain.common.MoneyStandard.MINIMUM_FREE_ORDER_PRICE;
import static kr.co._29cm.homework.domain.common.MoneyStandard.PRICE_OF_DELIVERY;

public class OrderProducts {
    private List<OrderProduct> orderProducts;

    public OrderProducts(Products products, Orders orders) {
        orderProducts = new ArrayList<>();

        for (Integer productNumber : orders.getProductNumbers()) {
            orderProducts.add(OrderProduct.of(
                    products.findByNumber(productNumber),
                    orders.find(productNumber)
                    )
            );
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
        if (totalOrderPrice < MINIMUM_FREE_ORDER_PRICE) {
            return totalOrderPrice + PRICE_OF_DELIVERY;
        }
        return totalOrderPrice;
    }
}
