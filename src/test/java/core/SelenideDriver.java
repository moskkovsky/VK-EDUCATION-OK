package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.AllureListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static config.ConfigSelenideProvider.*;

@ExtendWith(AllureListener.class)
abstract public class SelenideDriver {
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*",
                "--user-data-dir=/tmp/chrome_profile_" + System.currentTimeMillis()
        );

        if (HEADLESS) {
            options.addArguments("--headless=new");
        }
        Configuration.browser = BROWSER;
        Configuration.headless = HEADLESS;
        Configuration.timeout = TIMEOUT;
        Configuration.pageLoadTimeout = PAGE_LOAD_TIMEOUT;
        Configuration.browserSize = BROWSER_SIZE;
        Selenide.open(URL);
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
