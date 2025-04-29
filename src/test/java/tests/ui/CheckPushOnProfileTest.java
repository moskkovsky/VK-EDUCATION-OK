package tests.ui;

import core.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.LoginPage;
import pages.PushOnProfilePage;
import tag.pages.PushOnProfileTag;
import tag.regress.RegressTag;

import static constants.pushOnProfilePage.PushOnProfileValues.NOTE_IN_LIST_MENU;
import static constants.pushOnProfilePage.PushOnProfileValues.TEXT_FOR_INPUT_IN_FIELD;

@Epic(value = "Опубликовать")
@DisplayName("Тесты для Публикации пользователя")
@Link("https://t.me/moskkovsky")
public class CheckPushOnProfileTest extends BaseTest {
    @Test
    @RegressTag
    @PushOnProfileTag
    @Feature("Отображение элементов")
    @Story("Видимость кнопки Опубликовать")
    @DisplayName("Проверка отображения кнопки Опубликовать")
    @Description("Отображение кнопки Опубликовать на странице")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleButtonPushOnProfile() {
        LoginPage loginPage = new LoginPage().get();
        PushOnProfilePage pushOnProfilePage = new PushOnProfilePage();
        loginPage.auth();
        pushOnProfilePage.get();
    }

    @Test
    @RegressTag
    @PushOnProfileTag
    @Feature("Кликабельность элементов")
    @Story("Кликабельность кнопки Опубликовать")
    @DisplayName("Кликабельность кнопки Опубликовать")
    @Description("Проверка кнопки Опубликовать кликабельна")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckClickButtonPushOnProfile() {
        LoginPage loginPage = new LoginPage().get();
        PushOnProfilePage pushOnProfilePage = new PushOnProfilePage();
        loginPage.auth();
        pushOnProfilePage.get()
                .clickButtonPush()
                .checkVisibleOpenPushOnProfilePage();
    }

    @Test
    @RegressTag
    @PushOnProfileTag
    @Feature("Отображение элементов")
    @Story("Видимость подсказок в поле ввода Записи")
    @DisplayName("Проверка подсказок в поле ввода Записи")
    @Description("Тест проверяет, что подсказки в поле ввода для публикации Записи на Профиле")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testCheckVisibleHintInInputTextInFieldForPushOnProfile() {
        LoginPage loginPage = new LoginPage().get();
        PushOnProfilePage pushOnProfilePage = new PushOnProfilePage();
        loginPage.auth();
        pushOnProfilePage.get()
                .clickButtonPush()
                .clickChoiceButtonInListMenu(NOTE_IN_LIST_MENU)
                .checkVisibleHintInInputField();
    }

    @Test
    @RegressTag
    @PushOnProfileTag
    @Feature("Не кликабельна кнопка")
    @Story("Не кликабельность кнопки без вставки текста в поле ввода")
    @DisplayName("Неактивная кнопка Публиковать без текста")
    @Description("Проверка кнопки Публиковать неактивна, пока не введем текст")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckNotActivePushEmptyText() {
        LoginPage loginPage = new LoginPage().get();
        PushOnProfilePage pushOnProfilePage = new PushOnProfilePage();
        loginPage.auth();
        pushOnProfilePage.get()
                .clickButtonPush()
                .clickChoiceButtonInListMenu(NOTE_IN_LIST_MENU)
                .checkButtonShareDisabled();
    }

    @ParameterizedTest(name = "Публикация записи с текстом: {0}")
    @ValueSource(strings = {
            "VK EDUCATION 2025",
            "AQA VK",
            "VK",
    })
    public void testShareNoteOnProfile() {
        LoginPage loginPage = new LoginPage().get();
        PushOnProfilePage pushOnProfilePage = new PushOnProfilePage();
        loginPage.auth();
        pushOnProfilePage.get()
                .clickButtonPush()
                .clickChoiceButtonInListMenu(NOTE_IN_LIST_MENU)
                .inputTextInField(TEXT_FOR_INPUT_IN_FIELD)
                .clickButtonShare();
    }
}