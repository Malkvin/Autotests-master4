package Home4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractTest {
    public RemoteWebDriver driver;
    public WebDriverWait wait;
    static String login = "GB202212c8b3e14";
    static String password = "123d5e4487";

    @BeforeEach
    void init() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVideo", true);
        }});
       driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
//        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Навигация на https://test-stand.gb.ru/login
        driver.get(("https://test-stand.gb.ru/login"));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void close() {
        driver.quit();
    }
}
