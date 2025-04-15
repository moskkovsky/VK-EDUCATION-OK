package tests;


import config.EnvConfig;
import core.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.NavigationPage;
import tag.NavigationTag;

import java.util.function.Supplier;

@Epic("Навигация")
@Link("https://t.me/moskkovsky")
@DisplayName("Тесты для элементов Навигации")
public class CheckNavigationTest extends BaseTest {
    private Supplier<LoginPage> loginPage = () -> new LoginPage().get();
    private Supplier<NavigationPage> navigationPage = () -> new NavigationPage().get();

    @Test
    @NavigationTag
    @Feature(value = "Отображение элементов")
    @Story("Видимость кнопок в меню Навигации")
    @DisplayName("Элементы в меню навигации отображаются на странице")
    @Description("Тест проверяет все кнопки в меню навигации отображаются")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckVisibleElementsInNavigationMenu() {
        loginPage.get().auth(
                EnvConfig.USER_LOGIN,
                EnvConfig.USER_PASSWORD
        );
        navigationPage.get().checkVisibleElementsInNavigationMenu();
    }

}
