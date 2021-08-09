package ui;

import domain.OrderProducts;
import domain.Products;

public class CuiPrinter {

    public void show(Products products) {
        products.stream().forEach(System.out::println);
    }

    public void show(OrderProducts orderProducts) {
        System.out.println("------------------------------------------------------------");
        orderProducts.stream()
                .forEach(System.out::println);
        System.out.println("------------------------------------------------------------");
        // 주문금액
        int totalOrderPrice = orderProducts.totalOrderPrice();
        System.out.println("주문금액: " + totalOrderPrice);
        if (totalOrderPrice < 50000) {
            System.out.println("배송비: " + "2,500원");
        }
        System.out.println("------------------------------------------------------------");
        // 지불금액
        System.out.println("지불금액: " + orderProducts.finalPayPrice());
    }
}
