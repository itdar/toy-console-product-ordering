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

    public List<Order> get() {
        return orders;
    }

    public Stream<Order> stream() {
        return this.orders.stream();
    }

    /**
     * 중복상품 주문시, 주문을 합친다.
     */
    public void assembleSameOrders() {
        Map<Integer, Integer> orderMap = new LinkedHashMap<>();

        Order order;
        for (int i = 0; i < orders.size(); ++i) {
            order = orders.get(i);
            if (orderMap.containsKey(order.productNumber())) {
                orderMap.put(order.productNumber(), orderMap.get(order.productNumber()) + order.quantity());
                continue;
            }
            orderMap.put(order.productNumber(), order.quantity());
        }

        orders = new ArrayList<>();

        for (int productNumber : orderMap.keySet()) {
            orders.add(Order.of(productNumber, orderMap.get(productNumber)));
        }

    }

    public void validate() throws SoldOutException {
        Products products = ProductData.INSTANCE.getProducts();
        for (Order order : orders) {
            Product product = products.findByNumber(order.productNumber());
            product.checkHasEnoughRemainedCountTo(order);
        }
    }

}
