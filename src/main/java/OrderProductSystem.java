import domain.ProductData;
import domain.common.Process;
import domain.order.Orders;
import domain.orderproduct.OrderProducts;
import domain.product.Products;
import exception.SoldOutException;
import ui.CUIHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class OrderProductSystem {

    public void start() {
        CUIHandler cuiHandler = new CUIHandler();

        while (true) {
            try {
                if (Process.QUIT.equals(cuiHandler.selectOrderOrQuit())) {
                    break;
                }

                Products products = ProductData.INSTANCE.loadAll();
                cuiHandler.show(products);

                Orders orders = inputOrders(cuiHandler);
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
        Map<Integer, Integer> orderMap = new HashMap<>();

        boolean isContinue = true;
        int productNumber;
        int quantity;
        while (isContinue) {
            try {
                productNumber = cuiHandler.selectProductNumber();
                quantity = cuiHandler.selectQuantity();

                addOrder(orderMap, productNumber, quantity);
            } catch (IOException e) {
                isContinue = false;
            } catch (Exception e) {
                cuiHandler.printInvalidInput();
            }
        }

        return new Orders(orderMap);
    }

    private void addOrder(Map<Integer, Integer> orders, int productNumber, int quantity) {
        if (orders.containsKey(productNumber)) {
            orders.put(productNumber, orders.get(productNumber) + quantity);
            return;
        }
        orders.put(productNumber, quantity);
    }

}
