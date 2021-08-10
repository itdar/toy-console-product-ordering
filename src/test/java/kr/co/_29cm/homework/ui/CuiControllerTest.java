package kr.co._29cm.homework.ui;

import kr.co._29cm.homework.domain.common.Process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("커맨드라인 입출력 컨트롤러 테스트")
public class CuiControllerTest {

    private CUIHandler cuiController;

    @BeforeEach
    void setUp() {
        cuiController = new CUIHandler();
    }

    @Test
    void 주문_종료_여부에서_종료_선택한다() throws IOException {
        CUIHandler cuiController = mock(CUIHandler.class);
        when(cuiController.selectOrderOrQuit()).thenReturn(Process.QUIT);

        boolean isContinue = true;
        while (isContinue) {
            try {
                isContinue = cuiController.selectOrderOrQuit().isContinue();
            } catch (IOException e) {
                System.out.println("유효하지 않은 입력");
            }
        }
    }

    @Test
    void 주문_종료_여부에서_주문_10회_선택한다() throws IOException {
        CUIHandler cuiController = mock(CUIHandler.class);
        when(cuiController.selectOrderOrQuit()).thenReturn(Process.ORDER);

        int count = 0;
        boolean isContinue = true;
        while (isContinue && count < 10) {
            try {
                isContinue = cuiController.selectOrderOrQuit().isContinue();
            } catch (IOException e) {
                System.out.println("유효하지 않은 입력");
            }
            ++count;
        }
    }

}
