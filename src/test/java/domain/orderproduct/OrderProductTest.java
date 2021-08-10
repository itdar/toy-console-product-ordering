package domain.orderproduct;

import domain.order.Order;
import domain.orderproduct.OrderProduct;
import domain.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("주문상품 클래스 테스트")
public class OrderProductTest {
    private int 상품번호 = 782858;
    private int 주문수량 = 2;
    private String 상품명 = "상품명";
    private int 판매가격 = 1000;
    private int 재고수량 = 3;

    @Test
    void 주문상품을_만든다() {
        Product product = Product.of(상품번호, 상품명, 판매가격, 재고수량);
        Order order = Order.of(상품번호, 주문수량);

        OrderProduct orderProduct = OrderProduct.of(product, order);

        assertThat(orderProduct.price()).isEqualTo(주문수량 * 판매가격);
    }

}
