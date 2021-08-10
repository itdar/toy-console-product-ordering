package kr.co._29cm.homework.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 클래스 테스트")
public class ProductTest {
    private int 상품번호 = 782858;
    private String 상품명 = "상품명";
    private int 판매가격 = 1000;
    private int 재고수량 = 3;

    @Test
    void 정상적인_상품을_만든다() {
        Product product = Product.of(상품번호, 상품명, 판매가격, 재고수량);

        assertThat(product).isEqualTo(Product.of(상품번호, 상품명, 판매가격, 재고수량));
    }
}
