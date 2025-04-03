package tests;


import config.ConfigProvider;
import config.EnvConfig;
import constants.login.LoginErrorMessageValues;
import core.SelenideDriver;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import pages.LoginPage;
import pages.TapePage;
import tag.LoginTag;

import java.util.concurrent.TimeUnit;


@Epic(value = "Авторизация")
@DisplayName("Тесты для Авторизации пользователя")
@Link("https://t.me/moskkovsky")
public class CheckLoginTest extends SelenideDriver {
    private LoginPage loginPage = new LoginPage();
    private TapePage tapePage = new TapePage();

    @Test
    @Tag("login")
    @Timeout(value = 120000, unit = TimeUnit.MILLISECONDS)
    @Feature(value = "Вход по логину и паролю")
    @Story(value = "Успешная авторизация с валидными данными")
    @DisplayName("Прохождение авторизации")
    @Description("Тест проверяет успешное прохождение авторизации и с проверкой, что после нажатия на кнопку Войти в Одноклассники переход на страницу Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckSuccessAuth() {
        loginPage.auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        tapePage.checkButtonBurgerMenuOnTapePage();
    }

    @Test
    @Tag("login")
    @Timeout(value = 120000, unit = TimeUnit.MILLISECONDS)
    @Feature(value = "Получение ошибки в случае пустого ввода")
    @DisplayName("Получение ошибки при пустом логине")
    @Description("Тест проверяет на получение ошибки, в случае если вводим пустой логин")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleErrorMessageWithEmptyLogin() {
        loginPage.inputUserEmail("")
                .inputUserPassword(EnvConfig.USER_PASSWORD)
                .clickButtonSignOk()
                .checkErrorMessage(LoginErrorMessageValues.ERROR_MESSAGE_EMPTY_LOGIN);
    }

    @Test
    @Tag("login")
    @Timeout(value = 120000, unit = TimeUnit.MILLISECONDS)
    @Feature(value = "Получение ошибки в случае пустого ввода")
    @DisplayName("Получение ошибки при пустом пароле")
    @Description("Тест проверяет на получение ошибки, в случае если вводим пустой пароль")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleErrorMessageWithEmptyPassword() {
        loginPage.inputUserEmail(EnvConfig.USER_LOGIN)
                .inputUserPassword("")
                .clickButtonSignOk()
                .checkErrorMessage(LoginErrorMessageValues.ERROR_MESSAGE_EMPTY_PASSWORD);
    }

    @Test
    @LoginTag
    @Timeout(value = 120000, unit = TimeUnit.MILLISECONDS)
    @Feature(value = "Получение ошибки в случае пустого ввода")
    @DisplayName("Получение ошибки при пустом логине и пароле")
    @Description("Тест проверяет на получение ошибки, в случае если вводим пустой логин и пароль")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleErrorMessageWithEmptyLoginAndPassword() {
        loginPage.inputUserEmail("")
                .inputUserPassword("")
                .clickButtonSignOk()
                .checkErrorMessage(LoginErrorMessageValues.ERROR_MESSAGE_EMPTY_LOGIN);
    }

    @Test
    @Timeout(value = 120000, unit = TimeUnit.MILLISECONDS)
    @Feature(value = "Получение ошибки в случае ввода неправильных данных")
    @DisplayName("Получение ошибки при неправильном логине и пароле")
    @Description("Тест проверяет на получение ошибки, в случае если вводим неправильный логин и пароль")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleErrorMessageWrongLogin() {
        loginPage.inputUserEmail(ConfigProvider.WRONG_LOGIN)
                .inputUserPassword(ConfigProvider.WRONG_PASSWORD)
                .clickButtonSignOk()
                .checkErrorMessage(LoginErrorMessageValues.ERROR_MESSAGE_WRONG_LOGIN_OR_PASSWORD);
    }
}
