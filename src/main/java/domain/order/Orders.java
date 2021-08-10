package domain.order;

import domain.ProductData;
import domain.product.Product;
import domain.product.Products;
import exception.NegativeOrderQuantityException;
import exception.SoldOutException;

import javax.activation.UnsupportedDataTypeException;
import java.util.*;

public class Orders {
    Map<Integer, Integer> orderMap = new HashMap<>();

    public Set<Integer> getProductNumbers() {
        return orderMap.keySet();
    }

    public Order find(Integer productNumber) {
        return Order.of(productNumber, orderMap.get(productNumber));
    }

    public void validate() throws SoldOutException, NegativeOrderQuantityException {
        Products products = ProductData.INSTANCE.getProducts();

        for (Integer productNumber : orderMap.keySet()) {
            Product product = products.findByNumber(productNumber);
            product.validateRemainedCount(orderMap.get(productNumber));
            validateNegativeOrderQuantity(productNumber);
        }
    }

    private void validateNegativeOrderQuantity(int productNumber) throws NegativeOrderQuantityException {
        if (orderMap.get(productNumber) < 0) {
            throw new NegativeOrderQuantityException("최종 주문수량 음수는 불가");
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
