package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 클래스 테스트")
public class ProductTest {
    int 상품번호 = 1;
    String 상품명 = "상품명";
    int 판매가격 = 1000;
    int 재고수량 = 3;

    @Test
    void 정상적인_상품을_만든다() {

        Product product = new Product(상품번호, 상품명, 판매가격, 재고수량);

        assertThat(product).isEqualTo(new Product(상품번호, 상품명, 판매가격, 재고수량));
    }
}
