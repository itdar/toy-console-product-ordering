package ui;

import domain.common.Process;
import domain.orderproduct.OrderProducts;
import domain.product.Products;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;

import static domain.common.MoneyStandard.MINIMUM_FREE_ORDER_PRICE;
import static domain.common.MoneyStandard.PRICE_OF_DELIVERY;

public class CUIHandler {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Process selectOrderOrQuit() throws IOException {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");

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

    public int selectProductNumber() throws IOException {
        System.out.print("상품번호: ");

        String line = reader.readLine();
        if (line.contains(" ")) {
            throw new IOException("상품 선택 종료");
        }
        return Integer.parseInt(line);
    }

    public int selectQuantity() throws IOException {
        System.out.print("수량: ");

        return Integer.parseInt(reader.readLine());
    }

    public void show(OrderProducts orderProducts) {
        printSeparator();
        printListOf(orderProducts);
        printSeparator();
        printTotalPriceOf(orderProducts);
        printSeparator();
        printFinalPayPrice(orderProducts);
        printSeparator();
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
        System.out.println("유효하지 않은 입력입니다. 다시 입력하세요.");
    }

    public void printBye() {
        System.out.println("고객님의 주문 감사합니다.");
    }

    public void printSoldOut() {
        System.out.println("주문수량보다 재고가 적은 상품이 있습니다. 다시 주문하세요.");
    }

    public void printNoProductNumber() {
        System.out.println("주문번호가 없는 상품이 있습니다. 다시 주문하세요.");
    }

    public void printNoNegativeOrderQuantity() {
        System.out.println("최종 주문 수량이 음수인 주문이 있습니다. 다시 주문하세요.");
    }
}
