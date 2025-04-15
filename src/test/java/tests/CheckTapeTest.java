package tests;

import config.EnvConfig;
import core.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.TapePage;
import tag.TapeTag;

import java.util.function.Supplier;

import static constants.tape.TapeValues.BUTTON_MY_TAPE;


@Epic(value = "Лента")
@DisplayName("Тесты для Ленты пользователя")
@Link("https://t.me/moskkovsky")
public class CheckTapeTest extends BaseTest {
    private Supplier<LoginPage> loginPage = () -> new LoginPage().get();
    private Supplier<TapePage> tapePage = () -> new TapePage().get();

    @Disabled
    @Test
    @TapeTag
    @Feature("Отображение элементов")
    @Story("Видимость кнопок навигации")
    @DisplayName("Отображение кнопки Увлечения при открытии страницы Лента")
    @Description("Тест проверяет видимость кнопки Увлечения при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckButtonHobbiesVisibleOnPage() {
        loginPage.get().auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        tapePage.get().checkButtonVisibleOnTapePage(BUTTON_MY_TAPE);
    }

    @Disabled
    @Test
    @TapeTag
    @Feature("Отображение элементов")
    @Story("Видимость кнопок навигации")
    @DisplayName("Отображение кнопки Моменты при открытии страницы Лента")
    @Description("Тест проверяет отображение кнопки Моменты при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckButtonMomentsVisibleOnPage() {
        loginPage.get().auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        tapePage.get().checkButtonVisibleOnTapePage(BUTTON_MY_TAPE);
    }

    @Disabled
    @Test
    @TapeTag
    @Feature("Состояние элементов")
    @Story("Проверка активного состояния")
    @DisplayName("Выбор кнопки Увлечения при открытии страницы Лента")
    @Description("Тест проверяет атрибут aria-selected:true у кнопки Увлечения при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckActiveButtonOnTape() {
        loginPage.get().auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        tapePage.get().checkButtonIsSelectOnTapePage(BUTTON_MY_TAPE);
    }

    @Disabled
    @Test
    @TapeTag
    @Feature("Состояние элементов")
    @Story("Проверка неактивного состояния")
    @DisplayName("Неактивна кнопка Моменты при открытии страницы Лента")
    @Description("Тест проверяет атрибут aria-selected:false у кнопки Моменты при открытии страницы Лента")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckNotActiveButtonOnTape() {
        loginPage.get().auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        tapePage.get().checkButtonIsNotSelectOnTapePage(BUTTON_MY_TAPE);
    }

    @Disabled
    @Test
    @TapeTag
    @Feature("Взаимодействие с элементами")
    @Story("Переключение между кнопками")
    @DisplayName("Кнопка Моменты после нажатия активна и кнопка Увлечения неактивна")
    @Description("Тест проверяет, что после нажатия на кнопку Моменты атрибут aria-selected:true, у кнопки Увлечения атрибут атрибут aria-selected:false")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckActiveButtonMomentsAndButtonHobbiesNotActiveOnTape() {
        loginPage.get().auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        tapePage.get().checkActiveButtonAfterClickOnTapePage(
                BUTTON_MY_TAPE, BUTTON_MY_TAPE
        );
    }
}
