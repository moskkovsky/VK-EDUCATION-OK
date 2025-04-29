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
import pages.SettingsPage;
import tag.regress.RegressTag;
import tag.pages.SettingsTag;

@Epic(value = "Настройки")
@DisplayName("Тесты для Настройки пользователя")
@Link("https://t.me/moskkovsky")
public class CheckSettingsTest extends BaseTest {
    @Test
    @RegressTag
    @SettingsTag
    @Feature(value = "Отображение элементов")
    @Story("Открытие страницы настроек")
    @DisplayName("Открытие страницы Настройки через Профиль")
    @Description("Проверка корректности перехода на страницу настроек из профиля пользователя")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleNameOnProfile() {
        LoginPage loginPage = new LoginPage().get();
        ProfilePage profilePage = new ProfilePage();
        SettingsPage settingsPage = new SettingsPage();
        NavigationPage navigationPage = new NavigationPage();
        loginPage.auth();
        navigationPage.get().clickElementInMenuNavigation(ConfigProvider.USER_NAME);
        profilePage.get().clickToButtonSettingsOnProfile();
        settingsPage.get();
    }
}
