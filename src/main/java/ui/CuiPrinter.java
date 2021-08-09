package ui;

import domain.Product;

import java.util.List;

public class CuiPrinter {

    public void listOf(List<Product> products) {
        products.forEach(System.out::println);
    }

}
