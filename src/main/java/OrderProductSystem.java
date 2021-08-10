import domain.common.Process;
import domain.*;
import domain.order.Order;
import domain.order.Orders;
import domain.orderproduct.OrderProducts;
import domain.product.Products;
import exception.SoldOutException;
import ui.CUIHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderProductSystem {

    public void start() {
        CUIHandler cuiHandler = new CUIHandler();

        Process process = Process.START;
        while (process.isContinue()) {
            try {
                process = cuiHandler.selectOrderOrQuit();

                Products products = ProductData.INSTANCE.loadAll();
                cuiHandler.show(products);

                Orders orders = inputOrders(cuiHandler);
                orders.assembleSameOrders();
                orders.validate();

                OrderProducts orderProducts = new OrderProducts(products, orders);
                cuiHandler.show(orderProducts);

            } catch (IOException e) {
                cuiHandler.printInvalidInput();
            } catch (SoldOutException e) {
                cuiHandler.printSoldOut();
            } catch (NoSuchElementException e) {
                cuiHandler.printNoProductNumber();
            }
        }

        cuiHandler.printBye();
    }

    private Orders inputOrders(CUIHandler cuiHandler) {
        List<Order> orders = new ArrayList<>();

        boolean isContinue = true;
        int productNumber;
        int quantity;
        while (isContinue) {
            try {
                productNumber = cuiHandler.selectProductNumber();
                quantity = cuiHandler.selectQuantity();

                orders.add(Order.of(productNumber, quantity));
            } catch (IOException e) {
                isContinue = false;
            }
        }

        return new Orders(orders);
    }

//    private
}
