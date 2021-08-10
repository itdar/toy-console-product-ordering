package domain.order;

import domain.ProductData;
import domain.product.Product;
import domain.product.Products;
import exception.SoldOutException;

import java.util.*;

public class Orders {
    Map<Integer, Integer> orderMap = new HashMap<>();

    public List<Order> get() {
        List<Order> orders = new ArrayList<>();

        for (Integer productNumber : orderMap.keySet()) {
            orders.add(Order.of(productNumber, orderMap.get(productNumber)));
        }

        return orders;
    }

    public void validate() throws SoldOutException {
        Products products = ProductData.INSTANCE.getProducts();

        for (Integer productNumber : orderMap.keySet()) {
            Product product = products.findByNumber(productNumber);
            product.validateRemainedCount(orderMap.get(productNumber));
        }
    }

    public void addOrder(int productNumber, int quantity) {
        if (orderMap.containsKey(productNumber)) {
            orderMap.put(productNumber, orderMap.get(productNumber) + quantity);
            return;
        }
        orderMap.put(productNumber, quantity);
    }

}
