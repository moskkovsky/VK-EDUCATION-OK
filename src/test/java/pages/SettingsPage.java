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
import static constants.settings.SettingsValues.SETTINGS_TITLE;
import static constants.valueInMethods.ValueMethods.CALL_METHOD_IS_LOADED;
import static constants.valueInMethods.ValueMethods.CALL_METHOD_LOAD;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Страница Настройки
 */
public class SettingsPage extends LoadableComponent<SettingsPage> {
    private static final Logger log = LogManager.getLogger(SettingsPage.class);
    private static final By TITLE_SETTINGS = By.xpath("//h3[text()='Настройки']");

    @Override
    protected void load() {
        log.info(CALL_METHOD_LOAD);
        Selenide.open(URL_SETTINGS);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info(CALL_METHOD_IS_LOADED);
        checkVisibleOpenSettingsPage();

    }

    @Step("Проверяем, что открылась страница Настроек")
    @DisplayName("Проверка открытии страницы: Settings")
    public SettingsPage checkVisibleOpenSettingsPage() {
        log.info("Проверка, что открылась страница Настройки");
        $(TITLE_SETTINGS).shouldBe(visible.because(
                "Не удалось найти заголовок на странице Настроек"
        ));
        String actualTitleSettings = $(TITLE_SETTINGS).getText().trim();
        assertEquals(
                SETTINGS_TITLE,
                actualTitleSettings,
                String.format(
                        "Заголовок страницы Настроек не совпадает.\nОжидалось: '%s'\nФактически: '%s'",
                        SETTINGS_TITLE,
                        actualTitleSettings
                )
        );
        return this;
    }
}
