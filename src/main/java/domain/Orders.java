package domain;

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

        Iterator<Integer> iterator = orderMap.keySet().iterator();
        while (iterator.hasNext()) {
            int productNumber = iterator.next().intValue();
            orders.add(Order.of(productNumber, orderMap.get(productNumber)));
        }

    }
}
