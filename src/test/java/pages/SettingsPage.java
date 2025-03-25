package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import static constants.settings.SettingsValues.TITLE;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница Настройки
 */
public class SettingsPage {
    private static final Logger logger = LogManager.getLogger(SettingsPage.class);
    private final SelenideElement titleOnSettingsPage = $x("//h3[text()='Настройки']");

    @Step("Проверяем, что открылась страница Настроек")
    @DisplayName("Проверка открытии страницы")
    public SettingsPage checkVisibleOpenSettings() {
        logger.info("Проверка, что открылась страница Настройки");
        titleOnSettingsPage.shouldBe(Condition.visible)
                .shouldHave(exactText(TITLE));
        return this;
    }
}
