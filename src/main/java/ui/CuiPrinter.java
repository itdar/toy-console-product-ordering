package ui;

import domain.OrderProducts;
import domain.Products;

import java.text.NumberFormat;
import java.util.Locale;

public class CuiPrinter {

    public void show(Products products) {
        products.stream().forEach(System.out::println);
    }

    public void show(OrderProducts orderProducts) {
        printSeparator();
        printListOf(orderProducts);
        printSeparator();
        printTotalPriceOf(orderProducts);
        printSeparator();
        printFinalPayPrice(orderProducts);
    }

    private void printSeparator() {
        System.out.println("------------------------------------------------------------");
    }

    private void printListOf(OrderProducts orderProducts) {
        orderProducts.stream()
                .forEach(System.out::println);
    }

    private void printTotalPriceOf(OrderProducts orderProducts) {
        int totalOrderPrice = orderProducts.totalOrderPrice();
        System.out.println("주문금액: " + 
                NumberFormat.getNumberInstance(Locale.US).format(totalOrderPrice) +
                "원");
        if (totalOrderPrice < 50000) {
            System.out.println("배송비: " + "2,500원");
        }
    }

    private void printFinalPayPrice(OrderProducts orderProducts) {
        System.out.println("지불금액: " +
                NumberFormat.getNumberInstance(Locale.US).format(orderProducts.finalPayPrice()) +
                "원");
    }

}
