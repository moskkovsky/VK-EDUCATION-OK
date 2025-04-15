package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static config.ConfigProvider.URL_PROFILE;
import static config.ConfigProvider.USER_NAME;
import static constants.profile.ProfileValues.HINT_MESSAGE_ABOUT_ME;

/**
 * Страница Профиля пользователя
 */
public class ProfilePage extends LoadableComponent<ProfilePage> {
    private static final Logger log = LogManager.getLogger(ProfilePage.class);
    private final By userNameOnProfile = By.xpath("//a[@class='profile-user-info_name']/h1");
    private final By blockAboutMeOnProfile = By.id("hook_Block_AboutUserRB");
    private final By blockUnderNameOnProfile = By.id("hook_Block_MainMenu");

    @Override
    protected void load() {
        Selenide.open(URL_PROFILE);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info("Вызов метода isLoaded()");
        try {
            $(userNameOnProfile).shouldBe(visible.because(
                    "Не удалось найти имя на странице Профиля"
            )).shouldHave(exactText(USER_NAME));
        } catch (Exception e) {
            throw new Error("Страница Профиля не загружена: " + e.getMessage());
        }
    }

    /**
     * Не нужен, из-за LoadableComponent
     */
    @Step("Проверяем имя '{nameUser}' в Профиле")
    @DisplayName("Проверка имени в Профиле")
    public ProfilePage checkNameInProfile(String nameUser) {
        log.info(String.format("Проверяем на странице Профиля имя: %s", nameUser));
        $(userNameOnProfile).shouldBe(visible.because(
                "Не удалось найти имя " + nameUser
        )).shouldHave(exactText(nameUser));
        return this;
    }

    @Step("Проверяем подсказки Рассказать о себе в блоке О себе")
    @DisplayName("Проверяем подсказки Рассказать о себе")
    public ProfilePage checkHintInFieldTellAboutMeInProfile() {
        log.info("Проверяем подсказку в блоке О себе");
        $(blockAboutMeOnProfile).find(By.xpath(".//*[@class='text-field_empty']"))
                .shouldBe(visible.because(
                        "Не удалось найти подсказки в поле ввода О себе"
                )).shouldHave(exactText(HINT_MESSAGE_ABOUT_ME));
        return this;
    }

    @Step("Нажимаем на кнопку Настройки в Профиле")
    @DisplayName("Открываем страницу Настройки через шапку профиля")
    public SettingsPage clickToButtonSettingsOnProfile() {
        log.info("Тык по кнопке Настройки в Профиле");
        $(blockUnderNameOnProfile).find(By.xpath(".//*[@href='/settings']"))
                .shouldBe(visible.because(
                        "Не удалось нажать на кнопку Настройки"
                )).click();
        return new SettingsPage().get();
    }
}
