package tests.ui;


import constants.login.LoginErrorMessageValues;
import core.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.TapePage;

import java.util.concurrent.TimeUnit;

@Epic(value = "Авторизация")
@Link("https://t.me/moskkovsky")
@DisplayName("Тесты для Авторизации пользователя")
@Timeout(value = 120000, unit = TimeUnit.MILLISECONDS)
public class CheckLoginTest extends BaseTest {
    @Test
    @Feature(value = "Вход по логину и паролю")
    @Story(value = "Успешная авторизация с валидными данными")
    @DisplayName("Прохождение авторизации")
    @Description("Тест проверяет успешное прохождение авторизации и с проверкой, что после нажатия на кнопку Войти в Одноклассники переход на страницу Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckSuccessAuth() {
        LoginPage loginPage = new LoginPage().get();
        TapePage tapePage = new TapePage();
        loginPage.auth();
        tapePage.get();
    }

    @Disabled
    @Test
    @Feature(value = "Получение ошибки в случае пустого ввода")
    @DisplayName("Получение ошибки при пустом логине")
    @Description("Тест проверяет на получение ошибки, в случае если вводим пустой логин")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleErrorMessageWithEmptyLogin() {
        LoginPage loginPage = new LoginPage().get();
        loginPage.inputUserEmail()
                .inputUserPassword()
                .clickButtonSignOk()
                .checkErrorMessage(LoginErrorMessageValues.ERROR_MESSAGE_EMPTY_LOGIN);
    }

    @Disabled
    @Test
    @Feature(value = "Получение ошибки в случае пустого ввода")
    @DisplayName("Получение ошибки при пустом пароле")
    @Description("Тест проверяет на получение ошибки, в случае если вводим пустой пароль")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleErrorMessageWithEmptyPassword() {
        LoginPage loginPage = new LoginPage().get();
        loginPage.inputUserEmail()
                .inputUserPassword()
                .clickButtonSignOk()
                .checkErrorMessage(LoginErrorMessageValues.ERROR_MESSAGE_EMPTY_PASSWORD);
    }

    @Disabled
    @Test
    @Feature(value = "Получение ошибки в случае пустого ввода")
    @DisplayName("Получение ошибки при пустом логине и пароле")
    @Description("Тест проверяет на получение ошибки, в случае если вводим пустой логин и пароль")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleErrorMessageWithEmptyLoginAndPassword() {
        LoginPage loginPage = new LoginPage().get();
        loginPage.inputUserEmail()
                .inputUserPassword()
                .clickButtonSignOk()
                .checkErrorMessage(LoginErrorMessageValues.ERROR_MESSAGE_EMPTY_LOGIN);
    }

    @Disabled
    @Test
    @Feature(value = "Получение ошибки в случае ввода неправильных данных")
    @DisplayName("Получение ошибки при неправильном логине и пароле")
    @Description("Тест проверяет на получение ошибки, в случае если вводим неправильный логин и пароль")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleErrorMessageWrongLogin() {
        LoginPage loginPage = new LoginPage().get();
        loginPage.inputUserEmail()
                .inputUserPassword()
                .clickButtonSignOk()
                .checkErrorMessage(LoginErrorMessageValues.ERROR_MESSAGE_WRONG_LOGIN_OR_PASSWORD);
    }
}
