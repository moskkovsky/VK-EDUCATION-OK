package tests;

import config.ConfigProvider;
import config.EnvConfig;
import core.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;
import tag.SettingsTag;

import java.util.function.Supplier;

@Epic(value = "Настройки")
@DisplayName("Тесты для Настройки пользователя")
@Link("https://t.me/moskkovsky")
public class CheckSettingsTest extends BaseTest {

    private Supplier<LoginPage> loginPage = () -> new LoginPage().get();
    private Supplier<ProfilePage> profilePage = () -> new ProfilePage().get();

    @Test
    @SettingsTag
    @Feature(value = "Отображение элементов")
    @Story("Открытие страницы настроек")
    @DisplayName("Открытие страницы Настройки через Профиль")
    @Description("Проверка корректности перехода на страницу настроек из профиля пользователя")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleNameOnProfile() {
        loginPage.get().auth(
                        EnvConfig.USER_LOGIN,
                        EnvConfig.USER_PASSWORD
                )
                .getNavigation()
                .clickElementInMenuNavigation(ConfigProvider.USER_NAME);
        profilePage.get().clickToButtonSettingsOnProfile()
                .checkVisibleOpenSettings();
    }
}
