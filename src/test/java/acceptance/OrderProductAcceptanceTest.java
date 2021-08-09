package acceptance;

import controller.DataController;
import domain.Product;
import domain.ProductData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("상품 주문 시스템 인수테스트 클래스")
public class OrderProductAcceptanceTest {

    private ProductData productData;

    @BeforeEach
    public void setUp() {
        productData = new ProductData();

    }

    @Test
    void 저장되어있는_상품을_상품번호와_수량을_입력해서_구매하는_해피케이스() {
        // given
        // 상품데이터가 저장되어 있다.
        List<Product> products = productData.loadAll();

        // when
        // 주문과 종료 중 주문을 한다.
        // then
        // 주문가능 목록을 출력한다.

        // when
        // 상품번호와 수량을 입력받는다.
        // 상품번호와 수량을 입력받는다.
        // 스페이스바+엔터키로 종료를 선언한다.

        // then
        // 주문내역이 출력된다.
        // 주문금액이 출력된다.
        // (배송비가 필요하면 출력된다.)
        // 지불금액이 출력된다.

        // when
        // 주문과 종료 중 종료를 한다.

        // then
        // 종료한다.
    }

}
