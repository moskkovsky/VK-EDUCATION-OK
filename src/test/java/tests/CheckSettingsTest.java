package tests;

import config.ConfigProvider;
import core.SelenideDriver;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;

@Epic(value = "Настройки")
@DisplayName("Тесты для Настройки пользователя")
@Link("https://t.me/moskkovsky")
public class CheckSettingsTest extends SelenideDriver {

    private LoginPage loginPage = new LoginPage();
    private ProfilePage profilePage = new ProfilePage();

    @Test
    @Feature(value = "Отображение элементов")
    @Story("Открытие страницы настроек")
    @DisplayName("Открытие страницы Настройки через Профиль")
    @Description("Проверка корректности перехода на страницу настроек из профиля пользователя")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleNameOnProfile() {
        loginPage.auth()
                .getNavigation()
                .clickElementInMenuNavigation(ConfigProvider.USER_NAME);
        profilePage.clickToButtonSettingsOnProfile()
                .checkVisibleOpenSettings();
    }
}
