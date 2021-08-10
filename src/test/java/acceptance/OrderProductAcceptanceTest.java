package acceptance;

import domain.*;
import domain.order.Order;
import domain.order.Orders;
import domain.orderproduct.OrderProducts;
import domain.product.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.CUIHandler;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 주문 시스템 인수테스트 클래스")
public class OrderProductAcceptanceTest {

    private ProductData productData;
    private CUIHandler cuiPrinter;

    private int productNumber1 = 779989;
    private int productNumber2 = 779943;
    private int productNumber3 = 517643;
    private int quantity1 = 1;
    private int quantity1_1 = 0;
    private int quantity2 = 0;
    private int quantity3 = 0;

    @BeforeEach
    public void setUp() {
        productData = ProductData.INSTANCE;
        cuiPrinter = new CUIHandler();
    }

    @Test
    void 저장되어있는_상품을_상품번호와_수량을_입력해서_구매하는_해피케이스() {
        // given
        // 상품데이터가 저장되어 있다.
        Products products = productData.loadAll();

        // when
        // 주문과 종료 중 주문을 한다.
        // then
        // 주문가능 목록을 출력한다.
        cuiPrinter.show(products);

        // when
        // 상품번호와 수량을 여러개 입력받는다.
        Orders orders = new Orders(Arrays.asList(
                Order.of(productNumber1, quantity1),
                Order.of(productNumber1, quantity1_1),
                Order.of(productNumber2, quantity2),
                Order.of(productNumber3, quantity3)
        ));
        orders.assembleSameOrders();
        // 스페이스바+엔터키로 종료를 선언한다.

        // then
        // 주문내역이 출력된다.
        // 주문금액이 출력된다.
        // (배송비가 필요하면 출력된다.)
        // 지불금액이 출력된다.
        OrderProducts orderProducts = new OrderProducts(products, orders);
        cuiPrinter.show(orderProducts);

        assertThat(orderProducts.totalOrderPrice())
                .isEqualTo(products.findByNumber(productNumber1).price() * quantity1 +
                        products.findByNumber(productNumber1).price() * quantity1_1 +
                        products.findByNumber(productNumber2).price() * quantity2 +
                        products.findByNumber(productNumber3).price() * quantity3);

        // 종료한다.
    }

}
