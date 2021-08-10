package kr.co._29cm.homework.domain.order;

import kr.co._29cm.homework.domain.ProductData;
import kr.co._29cm.homework.domain.product.Product;
import kr.co._29cm.homework.domain.product.Products;
import kr.co._29cm.homework.exception.NegativeOrderQuantityException;
import kr.co._29cm.homework.exception.SoldOutException;

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
