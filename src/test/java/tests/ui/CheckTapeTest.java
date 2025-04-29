package tests.ui;


import core.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.TapePage;
import tag.pages.TapeTag;
import tag.regress.RegressTag;

import static constants.tape.TapeValues.TITLE_BUTTON_HOBBIES;
import static constants.tape.TapeValues.TITLE_BUTTON_MOMENTS;

@Epic(value = "Лента")
@DisplayName("Тесты для Ленты пользователя")
@Link("https://t.me/moskkovsky")
public class CheckTapeTest extends BaseTest {
    @Test
    @RegressTag
    @TapeTag
    @Feature("Отображение элементов")
    @Story("Видимость кнопок навигации")
    @DisplayName("Отображение кнопки Увлечения при открытии страницы Лента")
    @Description("Тест проверяет видимость кнопки Увлечения при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckButtonHobbiesVisibleOnPage() {
        LoginPage loginPage = new LoginPage().get();
        TapePage tapePage = new TapePage();
        loginPage.auth();
        tapePage.get().checkButtonVisibleOnTapePage(TITLE_BUTTON_HOBBIES);
    }

    @Test
    @RegressTag
    @TapeTag
    @Feature("Отображение элементов")
    @Story("Видимость кнопок навигации")
    @DisplayName("Отображение кнопки Моменты при открытии страницы Лента")
    @Description("Тест проверяет отображение кнопки Моменты при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckButtonMomentsVisibleOnPage() {
        LoginPage loginPage = new LoginPage().get();
        TapePage tapePage = new TapePage();
        loginPage.auth();
        tapePage.get().checkButtonVisibleOnTapePage(TITLE_BUTTON_MOMENTS);
    }

    @Test
    @RegressTag
    @TapeTag
    @Feature("Состояние элементов")
    @Story("Проверка активного состояния")
    @DisplayName("Выбор кнопки Увлечения при открытии страницы Лента")
    @Description("Тест проверяет атрибут aria-selected:true у кнопки Увлечения при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckActiveButtonOnTape() {
        LoginPage loginPage = new LoginPage().get();
        TapePage tapePage = new TapePage();
        loginPage.auth();
        tapePage.get().checkButtonIsSelectOnTapePage(TITLE_BUTTON_HOBBIES);
    }

    @Test
    @RegressTag
    @TapeTag
    @Feature("Состояние элементов")
    @Story("Проверка неактивного состояния")
    @DisplayName("Неактивна кнопка Моменты при открытии страницы Лента")
    @Description("Тест проверяет атрибут aria-selected:false у кнопки Моменты при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckNotActiveButtonOnTape() {
        LoginPage loginPage = new LoginPage().get();
        TapePage tapePage = new TapePage();
        loginPage.auth();
        tapePage.get().checkButtonIsNotSelectOnTapePage(TITLE_BUTTON_MOMENTS);
    }

    @Test
    @RegressTag
    @TapeTag
    @Feature("Взаимодействие с элементами")
    @Story("Переключение между кнопками")
    @DisplayName("Кнопка Моменты после нажатия активна и кнопка Увлечения неактивна")
    @Description("Тест проверяет, что после нажатия на кнопку Моменты атрибут aria-selected:true, у кнопки Увлечения атрибут атрибут aria-selected:false")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckActiveButtonMomentsAndButtonHobbiesNotActiveOnTape() {
        LoginPage loginPage = new LoginPage().get();
        TapePage tapePage = new TapePage();
        loginPage.auth();
        tapePage.get().checkActiveButtonAfterClickOnTapePage(
                TITLE_BUTTON_HOBBIES, TITLE_BUTTON_MOMENTS
        );
    }
}
