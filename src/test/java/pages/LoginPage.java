package pages;

import com.codeborne.selenide.Selenide;
import config.EnvConfig;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static config.ConfigProvider.URL_LOGIN;
import static config.ConfigProvider.URL_SETTINGS;
import static constants.valueInMethods.ValueMethods.CALL_METHOD_IS_LOADED;
import static constants.valueInMethods.ValueMethods.CALL_METHOD_LOAD;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Страница для входа в OK
 */
public class LoginPage extends LoadableComponent<LoginPage> {
    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private static final String ERROR_MESSAGE_TEMPLATE = "//div[text()='%s']"; // Шаблон для ошибок на страницы Авторизации
    private static final By TITLE_LOGIN = By.xpath("//a[text()='Вход']");
    private static final By FIELD_INPUT_EMAIL = byId("field_email");
    private static final By FIELD_INPUT_PASSWORD = byId("field_password");
    private static final By BUTTON_SIGN = By.xpath("//input[@value='Войти в Одноклассники']");

    @Override
    protected void load() {
        log.info(CALL_METHOD_LOAD);
        Selenide.open(URL_LOGIN);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info(CALL_METHOD_IS_LOADED);
        try {
            checkVisibleOpenLogin();
        } catch (Exception e) {
            throw new Error("Страница Авторизации не загружена: " + e.getMessage());
        }
    }

    @Step("Проверяем, что открылась страница Авторизации")
    @DisplayName("Проверка открытии страницы Login")
    public LoginPage checkVisibleOpenLogin() {
        log.info("Проверка, что открылась страница Авторизации");
        $(TITLE_LOGIN).shouldBe(visible.because(
                "Не удалось найти заголовок на странице Авторизации"
        ));
        return this;
    }

    @Step("Проходим авторизацию с почтой пользователя")
    public LoginPage auth() {
        log.info("Прохождение авторизации");
        inputUserEmail();
        inputUserPassword();
        clickButtonSignOk();
        log.info("Авторизация пройдена");
        return this;
    }

    @Step("Проверяем ошибку '{errorMessageValue}'")
    public LoginPage checkErrorMessage(String errorMessageValue) {
        log.info("Проверяем ошибку на странице Авторизации: {}", errorMessageValue);
        By errorMessageLocator = By.xpath(String.format(ERROR_MESSAGE_TEMPLATE, errorMessageValue));
        String actualErrorMessage = $(errorMessageLocator).getText().trim();
        $(errorMessageLocator).shouldBe(visible.because("Должна отображаться ошибка: " + errorMessageValue));
        assertEquals(
                actualErrorMessage,
                errorMessageValue,
                String.format(
                        "Текст ошибки не совпадает.\nОжидалось: '%s'\nФактически: '%s'",
                        errorMessageValue,
                        actualErrorMessage
                )
        );
        return this;
    }

    @Step("Вводим логин пользователя")
    public LoginPage inputUserEmail() {
        log.info("Вводим логин пользователя");
        $(FIELD_INPUT_EMAIL).shouldBe(visible.because(
                "Не удалось найти поля ввода логина"
        )).setValue(EnvConfig.USER_LOGIN);
        return this;
    }

    @Step("Вводим пароль пользователя")
    public LoginPage inputUserPassword() {
        log.info("Вводим пароль пользователя");
        $(FIELD_INPUT_PASSWORD).shouldBe(visible.because(
                "Не удалось найти поля ввода пароля"
        )).setValue(EnvConfig.USER_PASSWORD);
        return this;
    }

    @Step("Нажимаем на кнопку Войти в Одноклассники")
    public LoginPage clickButtonSignOk() {
        log.info("Нажимаем на кнопку Войти на странице Авторизации");
        $(BUTTON_SIGN).shouldBe(visible.because(
                "Не удалось нажать на кнопку"
        )).click();
        return this;
    }
}
