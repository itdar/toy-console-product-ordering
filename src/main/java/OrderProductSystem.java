import common.Process;
import ui.CUIHandler;

import java.io.IOException;

public class OrderProductSystem {

    public void start() {
        CUIHandler cuiHandler = new CUIHandler();

        Process process = Process.START;
        while (process.isContinue()) {
            try {
                process = cuiHandler.selectOrderOrQuit();

            } catch (IOException e) {
                cuiHandler.printInvalidInput();
            }
        }

        cuiHandler.printBye();
    }
}
