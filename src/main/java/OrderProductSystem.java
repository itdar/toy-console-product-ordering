import domain.ProductData;
import domain.common.Process;
import domain.order.Orders;
import domain.orderproduct.OrderProducts;
import domain.product.Products;
import exception.SoldOutException;
import ui.CUIHandler;

import java.io.IOException;
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

                Orders orders = readOrders(cuiHandler);
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

    private Orders readOrders(CUIHandler cuiHandler) {
        Orders orders = new Orders();

        boolean isContinue = true;
        while (isContinue) {
            try {
                orders.addOrder(cuiHandler.selectProductNumber(),
                        cuiHandler.selectQuantity());
            } catch (IOException e) {
                isContinue = false;
            } catch (Exception e) {
                cuiHandler.printInvalidInput();
            }
        }

        return orders;
    }

}
