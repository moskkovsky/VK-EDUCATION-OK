package tests;

import core.SelenideDriver;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.TapePage;

import static constants.tape.TapeValues.BUTTON_HOBBIES;
import static constants.tape.TapeValues.BUTTON_MOMENTS;


@Epic(value = "Лента")
@DisplayName("Тесты для Ленты пользователя")
@Link("https://t.me/moskkovsky")
public class CheckTapeTest extends SelenideDriver {
    private LoginPage loginPage = new LoginPage();
    private TapePage tapePage = new TapePage();

    @Test
    @Feature("Отображение элементов")
    @Story("Видимость кнопок навигации")
    @DisplayName("Отображение кнопки Увлечения при открытии страницы Лента")
    @Description("Тест проверяет видимость кнопки Увлечения при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckButtonHobbiesVisibleOnPage() {
        loginPage.auth();
        tapePage.checkButtonVisibleOnTapePage(BUTTON_HOBBIES);
    }

    @Test
    @Feature("Отображение элементов")
    @Story("Видимость кнопок навигации")
    @DisplayName("Отображение кнопки Моменты при открытии страницы Лента")
    @Description("Тест проверяет отображение кнопки Моменты при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckButtonMomentsVisibleOnPage() {
        loginPage.auth();
        tapePage.checkButtonVisibleOnTapePage(BUTTON_MOMENTS);
    }

    @Test
    @Feature("Состояние элементов")
    @Story("Проверка активного состояния")
    @DisplayName("Выбор кнопки Увлечения при открытии страницы Лента")
    @Description("Тест проверяет атрибут aria-selected:true у кнопки Увлечения при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckActiveButtonOnTape() {
        loginPage.auth();
        tapePage.checkButtonIsSelectOnTapePage(BUTTON_HOBBIES);
    }

    @Test
    @Feature("Состояние элементов")
    @Story("Проверка неактивного состояния")
    @DisplayName("Неактивна кнопка Моменты при открытии страницы Лента")
    @Description("Тест проверяет атрибут aria-selected:false у кнопки Моменты при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckNotActiveButtonOnTape() {
        loginPage.auth();
        tapePage.checkButtonIsNotSelectOnTapePage(BUTTON_MOMENTS);
    }

    @Test
    @Feature("Взаимодействие с элементами")
    @Story("Переключение между кнопками")
    @DisplayName("Кнопка Моменты после нажатия активна и кнопка Увлечения неактивна")
    @Description("Тест проверяет, что после нажатия на кнопку Моменты атрибут aria-selected:true, у кнопки Увлечения атрибут атрибут aria-selected:false")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckActiveButtonMomentsAndButtonHobbiesNotActiveOnTape() {
        loginPage.auth();
        tapePage.checkActiveButtonAfterClickOnTapePage(
                BUTTON_MOMENTS, BUTTON_HOBBIES
        );
    }
}
