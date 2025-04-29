package tests.ui;

import config.ConfigProvider;
import core.BaseTest;
import io.qameta.allure.*;
import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.NavigationPage;
import pages.ProfilePage;
import tag.pages.ProfileTag;
import tag.regress.RegressTag;


@Epic(value = "Профиль")
@Link("https://t.me/moskkovsky")
@DisplayName("Тесты для Профиля пользователя")
public class CheckProfileTest extends BaseTest {
    @Test
    @RegressTag
    @ProfileTag
    @Feature(value = "Отображение элементов")
    @Story("Видимость имени на странице")
    @DisplayName("Имя пользователя на странице Профиль")
    @Description("Тест проверяет, что отображается имя пользователя при открытии страницы Профиль")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleNameOnProfile() {
        LoginPage loginPage = new LoginPage().get();
        ProfilePage profilePage = new ProfilePage();
        NavigationPage navigationPage = new NavigationPage();
        loginPage.auth();
        navigationPage.get().clickElementInMenuNavigation(ConfigProvider.USER_NAME);
        profilePage.get().checkNameInProfile(ConfigProvider.USER_NAME);
    }

    @Test
    @RegressTag
    @ProfileTag
    @Feature(value = "Отображение элементов")
    @Story("Видимость подсказок в блоке О себе")
    @DisplayName("Подсказка Рассказать о себе в блоке О себе")
    @Description("Тест проверяет все подсказки в поле ввода текста в блоке О себе")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testCheckVisibleHintInFieldTellAboutMeOnProfile() {
        LoginPage loginPage = new LoginPage().get();
        ProfilePage profilePage = new ProfilePage();
        NavigationPage navigationPage = new NavigationPage();
        loginPage.auth();
        navigationPage.get().clickElementInMenuNavigation(ConfigProvider.USER_NAME);
        profilePage.get().checkHintInFieldTellAboutMeInProfile();
    }
}
