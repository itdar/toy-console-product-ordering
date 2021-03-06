package kr.co._29cm.homework.acceptance;

import kr.co._29cm.homework.domain.ProductData;
import kr.co._29cm.homework.domain.common.Process;
import kr.co._29cm.homework.domain.order.Orders;
import kr.co._29cm.homework.domain.orderproduct.OrderProducts;
import kr.co._29cm.homework.domain.product.Products;
import kr.co._29cm.homework.exception.NegativeOrderQuantityException;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.ui.CUIHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    private int abnormalProductNumber = 12345;
    private int abnormalQuantity = 20000;

    @BeforeEach
    public void setUp() {
        productData = ProductData.INSTANCE;
        cuiPrinter = new CUIHandler();
    }

    @Test
    void 저장되어있는_상품을_상품번호와_수량을_입력해서_구매하는_해피케이스() throws IOException, SoldOutException, NegativeOrderQuantityException {
        // given
        // 상품데이터가 저장되어 있다.
        Products products = productData.loadAll();

        // when
        // 주문과 종료 중 주문을 한다.
        CUIHandler mockCUI = mock(CUIHandler.class);
        when(mockCUI.selectOrderOrQuit()).thenReturn(Process.ORDER);
        // then
        // 주문가능 목록을 출력한다.
        cuiPrinter.show(products);

        // when
        // 상품번호와 수량을 여러개 입력받아 검증한다.
        Orders orders = new Orders();
        orders.addOrder(productNumber1, quantity1);
        orders.addOrder(productNumber1, quantity1_1);
        orders.addOrder(productNumber2, quantity2);
        orders.addOrder(productNumber3, quantity3);
        orders.validate();
        // then 주문 유효성 검증 완료

        // then
        // 주문내역, 주문금액, 지불금액이 출력된다.
        OrderProducts orderProducts = new OrderProducts(products, orders);
        cuiPrinter.show(orderProducts);

        assertThat(orderProducts.totalOrderPrice())
                .isEqualTo(products.findByNumber(productNumber1).price() * quantity1 +
                        products.findByNumber(productNumber1).price() * quantity1_1 +
                        products.findByNumber(productNumber2).price() * quantity2 +
                        products.findByNumber(productNumber3).price() * quantity3);

        // 종료한다.
    }

    @DisplayName("주문번호 실수, 재고수량 초과 주문 후에 정상 입력 한다.")
    @Test
    void 저장되어있는_상품을_상품번호와_수량을_입력해서_구매하는_예외포함_케이스() throws IOException, SoldOutException, NegativeOrderQuantityException {
        // given
        // 상품데이터가 저장되어 있다.
        Products products = productData.loadAll();

        // when
        // 주문과 종료 중 주문을 한다.
        CUIHandler mockCUI = mock(CUIHandler.class);
        when(mockCUI.selectOrderOrQuit()).thenReturn(Process.ORDER);
        // then
        // 주문가능 목록을 출력한다.
        cuiPrinter.show(products);

        // when
        // 상품번호와 수량을 여러개 입력받는다. 주문번호 실수 포함한다.
        Orders orders = new Orders();
        orders.addOrder(abnormalProductNumber, quantity1);
        orders.addOrder(productNumber3, quantity3);

        // then exception
        Orders finalOrders1 = orders;
        assertThatThrownBy(() -> {
            finalOrders1.validate();
        }).isInstanceOf(NoSuchElementException.class);

        // when
        // 상품번호와 수량을 여러개 입력받는다. 주문 수량 실수 포함한다.
        orders = new Orders();
        orders.addOrder(productNumber1, quantity1);
        orders.addOrder(productNumber3, abnormalQuantity);
        // then exception
        Orders finalOrders = orders;
        assertThatThrownBy(() -> {
            finalOrders.validate();
        }).isInstanceOf(SoldOutException.class);

        // when 정상 주문들
        orders = new Orders();
        orders.addOrder(productNumber1, quantity1);
        orders.addOrder(productNumber3, quantity3);
        orders.validate();
        // then
        // 주문내역, 주문금액, 지불금액이 출력된다.
        OrderProducts orderProducts = new OrderProducts(products, orders);
        cuiPrinter.show(orderProducts);

        assertThat(orderProducts.totalOrderPrice())
                .isEqualTo(products.findByNumber(productNumber1).price() * quantity1 +
                        products.findByNumber(productNumber3).price() * quantity3);

        // 종료한다.
    }
}
