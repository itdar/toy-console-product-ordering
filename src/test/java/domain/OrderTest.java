package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("주문 클래스 테스트")
public class OrderTest {

    int 상품번호 = 782858;
    int 주문수량 = 2;

    @Test
    void 정상_주문을_만든다() {
        Order order = new Order(상품번호, 주문수량);

        assertThat(order).isEqualTo(new Order(상품번호, 주문수량));
    }
}