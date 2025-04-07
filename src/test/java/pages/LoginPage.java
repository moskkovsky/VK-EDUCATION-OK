package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static config.ConfigProvider.URL_LOGIN;

/**
 * Страница для входа в OK
 */
public class LoginPage extends LoadableComponent<LoginPage> {
    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private final By titleLoginPage = By.xpath("//a[text()='Вход']");
    private final By fieldInputEmail = byId("field_email");
    private final By fieldInputPassword = byId("field_password");
    private final By buttonSignInOK = By.xpath("//input[@value='Войти в Одноклассники']");
    private static final String ERROR_MESSAGE_TEMPLATE = "//div[text()='%s']"; // Шаблон для ошибок на страницы Авторизации

    @Override
    protected void load() {
        log.info("Вызов метода load()");
        Selenide.open(URL_LOGIN);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info("Вызов метода isLoaded()");
        try {
            $(titleLoginPage).shouldBe(visible.because("Не удалось найти заголовок страницы Авторизации"));
        } catch (Exception e) {
            throw new Error("Страница Авторизации не загружена: " + e.getMessage());
        }
    }

    @Step("Проходим авторизацию с почтой пользователя")
    public LoginPage auth(String userEmail, String userPassword) {
        log.info(String.format("Авторизация: \nLogin: %s\nPassword: %s", userEmail, userPassword));
        inputUserEmail(userEmail);
        inputUserPassword(userPassword);
        clickButtonSignOk();
        return this;
    }

    @Step("Проверяем ошибку '{errorMessageValue}'")
    public LoginPage checkErrorMessage(String errorMessageValue) {
        log.info("Проверяем ошибку на странице Авторизации: {}", errorMessageValue);
        By errorMessageLocator = By.xpath(String.format(ERROR_MESSAGE_TEMPLATE, errorMessageValue));
        $(errorMessageLocator)
                .shouldBe(visible.because("Должна отображаться ошибка: " + errorMessageValue))
                .shouldHave(exactText(errorMessageValue));
        return this;
    }

    @Step("Вводим логин пользователя")
    public LoginPage inputUserEmail(String userEmail) {
        log.info(String.format("Вводим логин пользователя: %s", userEmail));
        $(fieldInputEmail).shouldBe(visible.because(
                "Не удалось найти поля ввода логина"
        )).clear();
        $(fieldInputEmail).setValue(userEmail);
        return this;
    }

    @Step("Вводим пароль пользователя")
    public LoginPage inputUserPassword(String userPassword) {
        log.info(String.format("Вводим пароль пользователя: %s", userPassword));
        $(fieldInputPassword).shouldBe(visible.because(
                "Не удалось найти поля ввода пароля"
        )).clear();
        $(fieldInputPassword).setValue(userPassword);
        return this;
    }

    @Step("Нажимаем на кнопку Войти в Одноклассники")
    public LoginPage clickButtonSignOk() {
        log.info("Нажимаем на кнопку Войти на странице Авторизации");
        $(buttonSignInOK).shouldBe(visible.because(
                "Не удалось нажать на кнопку"
        )).click();
        return this;
    }

    public NavigationPage getNavigation() {
        return new NavigationPage();
    }


}
