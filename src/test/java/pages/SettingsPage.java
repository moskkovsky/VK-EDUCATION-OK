package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static config.ConfigProvider.URL_SETTINGS;
import static constants.settings.SettingsValues.TITLE;
import static com.codeborne.selenide.Condition.exactText;

/**
 * Страница Настройки
 */
public class SettingsPage extends LoadableComponent<SettingsPage> {
    private static final Logger log = LogManager.getLogger(SettingsPage.class);
    private final By titleOnSettingsPage = By.xpath("//h3[text()='Настройки']");

    @Override
    protected void load() {
        Selenide.open(URL_SETTINGS);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info("Вызов метода isLoaded()");
        try {
            $(titleOnSettingsPage).shouldBe(visible.because("Не удалось найти заголовок страницы Настройки"));
        } catch (Exception e) {
            throw new Error("Страница Настройки не загружена: " + e.getMessage());
        }
    }

    /**
     * Больше не нужно, после паттерна LoadedComponent
     */
    @Step("Проверяем, что открылась страница Настроек")
    @DisplayName("Проверка открытии страницы")
    public SettingsPage checkVisibleOpenSettings() {
        log.info("Проверка, что открылась страница Настройки");
        $(titleOnSettingsPage).shouldBe(visible.because(
                "Не удалось найти заголовок на странице Настроек"
        )).shouldHave(exactText(TITLE));
        return this;
    }
}
