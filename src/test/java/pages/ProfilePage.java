package pages;


import com.codeborne.selenide.Selenide;
import config.ConfigProvider;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static config.ConfigProvider.URL_PROFILE;
import static constants.profile.ProfileValues.HINT_MESSAGE_ABOUT_ME;
import static constants.valueInMethods.ValueMethods.CALL_METHOD_IS_LOADED;
import static constants.valueInMethods.ValueMethods.CALL_METHOD_LOAD;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Страница Профиля пользователя
 */
public class ProfilePage extends LoadableComponent<ProfilePage> {
    private static final Logger log = LogManager.getLogger(ProfilePage.class);
    private static final By USER_NAME = By.xpath("//a[@class='profile-user-info_name']/h1");
    private static final By HINT_ABOUT_ME = By.xpath("//div[@class='text-field_empty']");
    private static final By BUTTON_SETTINGS_IN_PROFILE = By.xpath("//a[@href='/settings' and @hrefattrs='st.cmd=userConfig']");

    @Override
    protected void load() {
        log.info(CALL_METHOD_LOAD);
        Selenide.open(URL_PROFILE);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info(CALL_METHOD_IS_LOADED);
        try {
            checkNameInProfile(ConfigProvider.USER_NAME);
        } catch (Exception e) {
            throw new Error("Страница Профиля не загружена: " + e.getMessage());
        }
    }


    @Step("Проверяем имя '{nameUser}' в Профиле")
    @DisplayName("Проверка имени в Профиле")
    public ProfilePage checkNameInProfile(String userName) {
        log.info(String.format("Проверяем на странице Профиля имя: %s", userName));
        $(USER_NAME).shouldBe(visible.because(
                "Не удалось найти имя " + userName
        ));
        String actualUserName = $(USER_NAME).getText().trim();
        assertEquals(
                userName,
                actualUserName,
                String.format(
                        "Имя пользователя не совпадает.\nОжидалось: '%s'\nФактически: '%s'",
                        userName,
                        actualUserName
                )
        );
        return this;
    }

    @Step("Проверяем подсказки Рассказать о себе в блоке О себе")
    @DisplayName("Проверяем подсказки Рассказать о себе")
    public ProfilePage checkHintInFieldTellAboutMeInProfile() {
        log.info("Проверяем подсказку в блоке О себе");
        $(HINT_ABOUT_ME).shouldBe(visible.because(
                "Не удалось найти подсказки в поле ввода О себе"
        ));
        String actualHintAboutMe = $(HINT_ABOUT_ME).getText().trim();
        assertEquals(
                HINT_MESSAGE_ABOUT_ME,
                actualHintAboutMe,
                String.format(
                        "Подсказка в поле ввода О себе не совпадает.\nОжидалось: '%s'\nФактически: '%s'",
                        HINT_MESSAGE_ABOUT_ME,
                        actualHintAboutMe
                )
        );
        return this;
    }

    @Step("Нажимаем на кнопку Настройки в Профиле")
    @DisplayName("Открываем страницу Настройки через шапку профиля")
    public SettingsPage clickToButtonSettingsOnProfile() {
        log.info("Тык по кнопке Настройки в Профиле");
        $(BUTTON_SETTINGS_IN_PROFILE).shouldBe(visible.because(
                "Не удалось нажать на кнопку Настройки"
        )).click();
        return new SettingsPage().get();
    }
}
