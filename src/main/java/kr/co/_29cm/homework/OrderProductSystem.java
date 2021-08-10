package kr.co._29cm.homework;

import kr.co._29cm.homework.domain.ProductData;
import kr.co._29cm.homework.domain.common.Process;
import kr.co._29cm.homework.domain.order.Orders;
import kr.co._29cm.homework.domain.orderproduct.OrderProducts;
import kr.co._29cm.homework.domain.product.Products;
import kr.co._29cm.homework.exception.NegativeOrderQuantityException;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.ui.CUIHandler;

import java.io.IOException;
import java.util.NoSuchElementException;

public class OrderProductSystem {

    public void start() {
        CUIHandler cuiHandler = new CUIHandler();

        while (true) {
            try {
                if (cuiHandler.selectOrderOrQuit() == Process.QUIT) {
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
            } catch (NegativeOrderQuantityException e) {
                cuiHandler.printNoNegativeOrderQuantity();
            }
        }

        cuiHandler.printBye();
    }

    private Orders readOrders(CUIHandler cuiHandler) {
        Orders orders = new Orders();

        boolean isContinue = true;
        while (isContinue) {
            try {
                orders.addOrder(
                        cuiHandler.selectProductNumber(),
                        cuiHandler.selectQuantity()
                );
            } catch (IOException e) {
                isContinue = false;
            } catch (Exception e) {
                cuiHandler.printInvalidInput();
            }
        }

        return orders;
    }

}
