package domain.order;

import domain.ProductData;
import domain.product.Product;
import domain.product.Products;
import exception.SoldOutException;

import java.util.*;

public class Orders {
    Map<Integer, Integer> orderMap = new HashMap<>();

    public Set<Integer> getProductNumbers() {
        return orderMap.keySet();
    }

    public Order find(Integer productNumber) {
        return Order.of(productNumber, orderMap.get(productNumber));
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
