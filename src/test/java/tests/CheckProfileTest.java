package tests;

import config.ConfigProvider;
import config.EnvConfig;
import core.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;
import tag.ProfileTag;

import java.util.function.Supplier;

@Epic(value = "Профиль")
@Link("https://t.me/moskkovsky")
@DisplayName("Тесты для Профиля пользователя")
public class CheckProfileTest extends BaseTest {
    private Supplier<LoginPage> loginPage = () -> new LoginPage().get();
    private Supplier<ProfilePage> profilePage = () -> new ProfilePage().get();

    @Test
    @ProfileTag
    @Feature(value = "Отображение элементов")
    @Story("Видимость имени на странице")
    @DisplayName("Имя пользователя на странице Профиль")
    @Description("Тест проверяет, что отображается имя пользователя при открытии страницы Профиль")
    @Owner("Anton Moskovsky")
    @Link("https://t.me/moskkovsky")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckVisibleNameOnProfile() {
        loginPage.get().auth(
                        EnvConfig.USER_LOGIN,
                        EnvConfig.USER_PASSWORD
                )
                .getNavigation()
                .clickElementInMenuNavigation(ConfigProvider.USER_NAME);
        profilePage.get();
    }

    @Test
    @ProfileTag
    @Feature(value = "Отображение элементов")
    @Story("Видимость подсказок в блоке О себе")
    @DisplayName("Подсказка Рассказать о себе в блоке О себе")
    @Description("Тест проверяет все подсказки в поле ввода текста в блоке О себе")
    @Owner("Anton Moskovsky")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testCheckVisibleHintInFieldTellAboutMeOnProfile() {
        loginPage.get().auth(
                        EnvConfig.USER_LOGIN,
                        EnvConfig.USER_PASSWORD
                )
                .getNavigation()
                .clickElementInMenuNavigation(ConfigProvider.USER_NAME);
        profilePage.get().checkHintInFieldTellAboutMeInProfile();
    }
}
