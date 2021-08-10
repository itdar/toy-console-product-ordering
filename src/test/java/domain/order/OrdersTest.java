package domain.order;

import domain.ProductData;
import domain.order.Order;
import domain.order.Orders;
import exception.SoldOutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("주문 일급콜렉션 클래스 테스트")
public class OrdersTest {
    private int productNumber1 = 779989;
    private int productNumber2 = 517643;
    private int normalQuantity1 = 1;
    private int normalQuantity2 = 1;
    private int abnormalQuantity1 = 1000;
    private int abnormalQuantity2 = 10000;

    private Orders normalOrders;
    private Orders abnormalOrders;

    @BeforeEach
    public void setUp() {
        ProductData.INSTANCE.loadAll();

        normalOrders = new Orders(Arrays.asList(
                Order.of(productNumber1, normalQuantity1),
                Order.of(productNumber2, normalQuantity2)
        ));

        abnormalOrders = new Orders(Arrays.asList(
                Order.of(productNumber1, abnormalQuantity1),
                Order.of(productNumber2, abnormalQuantity2)
        ));
    }

    @DisplayName("주문들이 전부 가능한 수량을 가지고 있다.")
    @Test
    void 주문들이_전부_가능한_수량을_가졌는지_검증1() throws SoldOutException {
        normalOrders.validate();
    }

    @DisplayName("주문들 중에서 잔여수량이 부족한데 주문이 들어온 케이스가 있다")
    @Test
    void 주문들이_전부_가능한_수량을_가졌는지_검증2() {
        assertThatThrownBy(() -> {
            abnormalOrders.validate();
        }).isInstanceOf(SoldOutException.class);
    }

}
