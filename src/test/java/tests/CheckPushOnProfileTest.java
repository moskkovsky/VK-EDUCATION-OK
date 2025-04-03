package tests;

import config.EnvConfig;
import core.SelenideDriver;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.PushOnProfilePage;
import tag.PushOnProfileTag;

import static constants.pushOnProfilePage.PushOnProfileValues.NOTE_IN_LIST_MENU;
import static constants.pushOnProfilePage.PushOnProfileValues.TEXT_FOR_INPUT_IN_FIELD;

@Epic(value = "Опубликовать")
@DisplayName("Тесты для Публикации пользователя")
@Link("https://t.me/moskkovsky")
public class CheckPushOnProfileTest extends SelenideDriver {
    private LoginPage loginPage = new LoginPage();
    private PushOnProfilePage pushOnProfilePage = new PushOnProfilePage();

    @Test
    @PushOnProfileTag
    @Feature("Отображение элементов")
    @Story("Видимость кнопки Опубликовать")
    @DisplayName("Проверка отображения кнопки Опубликовать")
    @Description("Отображение кнопки Опубликовать на странице")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleButtonPushOnProfile() {
        loginPage.auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        pushOnProfilePage.checkVisibleButtonPush();
    }

    @Test
    @PushOnProfileTag
    @Feature("Кликабельность элементов")
    @Story("Кликабельность кнопки Опубликовать")
    @DisplayName("Кликабельность кнопки Опубликовать")
    @Description("Проверка кнопки Опубликовать кликабельна")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckClickButtonPushOnProfile() {
        loginPage.auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        pushOnProfilePage.clickButtonPush();
    }

    @Test
    @PushOnProfileTag
    @Feature("Отображение элементов")
    @Story("Видимость подсказок в поле ввода Записи")
    @DisplayName("Проверка подсказок в поле ввода Записи")
    @Description("Тест проверяет, что подсказки в поле ввода для публикации Записи на Профиле")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testCheckVisibleHintInInputTextInFieldForPushOnProfile() {
        loginPage.auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        pushOnProfilePage.clickButtonPush()
                .clickChoiceButtonInListMenu(NOTE_IN_LIST_MENU)
                .checkVisibleHintInInputField();
    }

    @Test
    @PushOnProfileTag
    @Feature("Не кликабельна кнопка")
    @Story("Не кликабельность кнопки без вставки текста в поле ввода")
    @DisplayName("Неактивная кнопка Публиковать без текста")
    @Description("Проверка кнопки Публиковать неактивна, пока не введем текст")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckNotActivePushEmptyText() {
        loginPage.auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        pushOnProfilePage.clickButtonPush()
                .clickChoiceButtonInListMenu(NOTE_IN_LIST_MENU)
                .checkButtonPushOnProfileDisabled();
    }

    @Test
    @PushOnProfileTag
    @Feature("Публикация записи")
    @Story("Публикация записи на странице пользователя")
    @DisplayName("Публикация записи на странице")
    @Description("Тест проверяет, что запись опубликована на странице")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCreateNoteOnProfile() {
        loginPage.auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        pushOnProfilePage.clickButtonPush()
                .clickChoiceButtonInListMenu(NOTE_IN_LIST_MENU)
                .inputTextInField(TEXT_FOR_INPUT_IN_FIELD)
                .clickButtonPushOnProfile();
    }
}