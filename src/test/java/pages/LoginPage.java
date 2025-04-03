package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница для входа в OK
 */
public class LoginPage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private final SelenideElement fieldInputEmail = $(byId("field_email"));
    private final SelenideElement fieldInputPassword = $(byId("field_password"));
    private final SelenideElement buttonSignInOK = $x("//input[@value='Войти в Одноклассники']");
    private static final String ERROR_MESSAGE_TEMPLATE = "//div[text()='%s']"; // Шаблон для ошибок на страницы Авторизации

    @Step("Проходим авторизацию с почтой пользователя")
    public LoginPage auth(String userEmail, String userPassword) {
        log.info("Авторизация с логином и паролем");
        inputUserEmail(userEmail);
        inputUserPassword(userPassword);
        clickButtonSignOk();
        return this;
    }

    @Step("Проверяем ошибку '{errorMessageValue}'")
    public LoginPage checkErrorMessage(String errorMessageValue) {
        log.info(String.format("Проверяем ошибку на странице Авторизации: %s", errorMessageValue));
        SelenideElement errorMessageOnLoginPage = $x(String.format(ERROR_MESSAGE_TEMPLATE, errorMessageValue));
        errorMessageOnLoginPage
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(errorMessageValue));
        return this;
    }

    @Step("Вводим логин пользователя")
    public LoginPage inputUserEmail(String userEmail) {
        log.info(String.format("Вводим логин пользователя: %s", userEmail));
        fieldInputEmail.shouldBe(Condition.visible).clear();
        fieldInputEmail.sendKeys(userEmail);
        return this;
    }

    @Step("Вводим пароль пользователя")
    public LoginPage inputUserPassword(String userPassword) {
        log.info(String.format("Вводим пароль пользователя: %s", userPassword));
        fieldInputPassword.shouldBe(Condition.visible).clear();
        fieldInputPassword.sendKeys(userPassword);
        return this;
    }

    @Step("Нажимаем на кнопку Войти в Одноклассники")
    public LoginPage clickButtonSignOk() {
        log.info("Нажимаем на кнопку Войти на странице Авторизации");
        buttonSignInOK.shouldBe(Condition.visible).click();
        return this;
    }

    public NavigationPage getNavigation() {
        return new NavigationPage();
    }
}
