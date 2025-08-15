import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled("Тест на время выполнения — отключён, запускайте вручную при необходимости")
    void mainCompletesWithin22Seconds() throws Exception {
        Main.main(new String[0]);
    }
}
