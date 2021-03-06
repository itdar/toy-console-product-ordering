package kr.co._29cm.homework.domain.order;

import kr.co._29cm.homework.domain.ProductData;
import kr.co._29cm.homework.exception.NegativeOrderQuantityException;
import kr.co._29cm.homework.exception.SoldOutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        normalOrders = new Orders();
        normalOrders.addOrder(productNumber1, normalQuantity1);
        normalOrders.addOrder(productNumber2, normalQuantity2);

        abnormalOrders = new Orders();
        abnormalOrders.addOrder(productNumber1, abnormalQuantity1);
        abnormalOrders.addOrder(productNumber2, abnormalQuantity2);
    }

    @DisplayName("주문들이 전부 가능한 수량을 가지고 있다.")
    @Test
    void 주문들이_전부_가능한_수량을_가졌는지_검증1() throws SoldOutException, NegativeOrderQuantityException {
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
