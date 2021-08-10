package domain.order;

import domain.ProductData;
import domain.product.Product;
import domain.product.Products;
import exception.SoldOutException;

import java.util.*;
import java.util.stream.Stream;

public class Orders {
    List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public Orders(Map<Integer, Integer> orderMap) {
        for (Integer productNumber : orderMap.keySet()) {
            orders.add(Order.of(productNumber, orderMap.get(productNumber)));
        }
    }

    public List<Order> get() {
        return orders;
    }

    public void validate() throws SoldOutException {
        Products products = ProductData.INSTANCE.getProducts();
        for (Order order : orders) {
            Product product = products.findByNumber(order.productNumber());
            product.checkHasEnoughRemainedCountTo(order);
        }
    }

}
