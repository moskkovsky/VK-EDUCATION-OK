package tests.ui;


import core.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.NavigationPage;
import tag.pages.NavigationTag;
import tag.regress.RegressTag;

@Epic("Навигация")
@Link("https://t.me/moskkovsky")
@DisplayName("Тесты для элементов Навигации")
public class CheckNavigationTest extends BaseTest {
    @Test
    @RegressTag
    @NavigationTag
    @Feature(value = "Отображение элементов")
    @Story("Видимость кнопок в меню Навигации")
    @DisplayName("Элементы в меню навигации отображаются на странице")
    @Description("Тест проверяет все кнопки в меню навигации отображаются")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckVisibleElementsInNavigationMenu() {
        LoginPage loginPage = new LoginPage().get();
        NavigationPage navigationPage = new NavigationPage();
        loginPage.auth();
        navigationPage.get().checkVisibleElementsInNavigationMenu();
    }
}
