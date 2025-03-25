package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static constants.profile.ProfileValues.HINT_MESSAGE_ABOUT_ME;
/**
 * Страница Профиля пользователя
 */
public class ProfilePage {
    private static final Logger log = LogManager.getLogger(ProfilePage.class);
    private final SelenideElement userNameOnProfile = $x("//a[@class='profile-user-info_name']/h1");
    private final SelenideElement buttonSettingsOnProfile = $x("//div[@id='hook_Block_MainMenu']//a[@href='/settings']");
    private final SelenideElement hintInFieldTellAboutMe = $x("//div[@class='text-field_empty']");


    @Step("Проверяем имя '{nameUser}' в Профиле")
    @DisplayName("Проверка имени в Профиле")
    public ProfilePage checkNameInProfile(String nameUser) {
        log.info(String.format("Проверяем на странице Профиля имя: %s", nameUser));
        userNameOnProfile.shouldBe(visible)
                .shouldHave(exactText(nameUser));
        return this;
    }

    @Step("Проверяем подсказки Рассказать о себе в блоке О себе")
    @DisplayName("Проверяем подсказки Рассказать о себе")
    public ProfilePage checkHintInFieldTellAboutMeInProfile() {
        log.info("Проверяем подсказку в блоке О себе");
        hintInFieldTellAboutMe.shouldBe(visible)
                .shouldHave(exactText(HINT_MESSAGE_ABOUT_ME));
        return this;
    }

    @Step("Нажимаем на кнопку Настройки в Профиле")
    @DisplayName("Открываем страницу Настройки через шапку профиля")
    public SettingsPage clickToButtonSettingsOnProfile() {
        log.info("Тык по кнопке Настройки в Профиле");
        buttonSettingsOnProfile.shouldBe(visible).click();
        return new SettingsPage();
    }
}
