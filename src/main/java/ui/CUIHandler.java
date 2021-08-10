package ui;

import common.Process;
import domain.OrderProducts;
import domain.Products;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;

public class CUIHandler {

    private final int MINIMUM_FREE_ORDER_PRICE = 50000;
    private final int PRICE_OF_DELIVERY = 2500;

    public Process selectOrderOrQuit() throws IOException {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readString = reader.readLine();

        if (readString.equals("o") || readString.equals("order")) {
            return Process.ORDER;
        }

        if (readString.equals("q") || readString.equals("quit") || readString.equals("exit")) {
            return Process.QUIT;
        }
        throw new IOException("유효하지 않은 입력");
    }

    public void show(Products products) {
        products.stream()
                .forEach(System.out::println);
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

        if (totalOrderPrice < MINIMUM_FREE_ORDER_PRICE) {
            System.out.println("배송비: " +
                    NumberFormat.getNumberInstance(Locale.US).format(PRICE_OF_DELIVERY) +
                    "원");
        }
    }

    private void printFinalPayPrice(OrderProducts orderProducts) {
        System.out.println("지불금액: " +
                NumberFormat.getNumberInstance(Locale.US).format(orderProducts.finalPayPrice()) +
                "원");
    }

    public void printInvalidInput() {
        System.out.println("유효하지 않은 입력");
    }

    public void printBye() {
        System.out.println("고객님의 주문 감사합니다.");
    }

}
