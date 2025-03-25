package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static constants.pushOnProfilePage.PushOnProfileValues.*;

/**
 * Опубликовать на странице Профиля
 */
public class PushOnProfilePage {
    private static final Logger log = LogManager.getLogger(PushOnProfilePage.class);
    private final SelenideElement buttonDropDownChoiceListMenu = $x("//span[text()='Опубликовать']/parent::button");
    private final SelenideElement titleChoiceListMenu = $x("//h3[text()='Опубликовать']");
    private final SelenideElement buttonSaveForPushOnProfile = $x("//button[@title='Поделиться']");
    private final SelenideElement hintTitleInputTextInFieldForPushProfile = $x("//div[@class='input_placeholder' and text() = 'Введите текст']");
    private final SelenideElement hintDescriptionInputTextInFieldForPushProfile = $x("//div[@class='input_placeholder_desc' and contains(text(), 'Используйте @ для упоминания')]");
    private final SelenideElement fieldForInputPushProfile = $x("//div[@contenteditable='true' and @data-module='postingForm/mediaText']");
    private String BUTTON_CHOICE_LIST_MENU = "//span[text()='%s']/ancestor::a";

    @Step("Проверяем отображение кнопки Опубликовать")
    @DisplayName("Проверка, что кнопка Опубликовать на странице")
    public PushOnProfilePage checkVisibleButtonPush() {
        log.info("Проверка кнопки Опубликовать на странице");
        buttonDropDownChoiceListMenu.shouldBe(Condition.visible);
        return this;
    }

    @Step("Нажимаем на кнопку Опубликовать")
    @DisplayName("Нажимаем на кнопку Опубликовать")
    public PushOnProfilePage clickButtonPush() {
        log.info("Тык по кнопке Опубликовать");
        buttonDropDownChoiceListMenu.shouldBe(Condition.visible).click();
        titleChoiceListMenu.shouldBe(visible)
                .shouldHave(exactText(TITLE_CHOICE_LIST_MENU));
        return this;
    }

    @Step("Нажимаем на кнопку '{choiceButtonInListMenu}' в выпадающем списке Опубликовать")
    @DisplayName("Нажимаем на кнопку в выпадающем списке Опубликовать")
    public PushOnProfilePage clickChoiceButtonInListMenu(String choiceButtonInListMenu) {
        log.info(String.format("Тык по кнопке %s в списке Опубликовать", choiceButtonInListMenu));
        SelenideElement elementChoiceButtonInListMenu = $x(String.format(BUTTON_CHOICE_LIST_MENU, choiceButtonInListMenu));
        elementChoiceButtonInListMenu
                .shouldBe(Condition.visible)
                .shouldHave(text(choiceButtonInListMenu))
                .click();
        return this;
    }

    @Step("Проверяем подсказки в поле ввода")
    @DisplayName("Проверяем, что подсказки видны в поле ввода")
    public PushOnProfilePage checkVisibleHintInInputField() {
        log.info("Проверка подсказок в поле ввода");
        hintTitleInputTextInFieldForPushProfile.shouldBe(visible).shouldHave(text(HINT_TITLE_IN_TEXT_FIELD));
        hintDescriptionInputTextInFieldForPushProfile.shouldBe(visible).shouldHave(text(HINT_DESCRIPTION_IN_TEXT_FIELD));
        return this;
    }

    @Step("Вводим текст '{textInputInFieldForPushProfile}' в поле ввода")
    @DisplayName("Вводим текст в поле ввода ")
    public PushOnProfilePage inputTextInField(String textInputInFieldForPushProfile) {
        log.info("Вводим текст для публикации");
        fieldForInputPushProfile.shouldBe(visible)
                .setValue(textInputInFieldForPushProfile);
        return this;
    }

    @Step("Проверяем атрибут у неактивной кнопки Поделиться")
    @DisplayName("Проверяем, что кнопка Поделиться неактивна")
    public PushOnProfilePage checkButtonPushOnProfileDisabled() {
        log.info("Проверка неактивной кнопки Поделиться");
        buttonSaveForPushOnProfile.shouldBe(visible)
                .shouldHave(attribute("disabled", "true"));
        return this;
    }


    @Step("Нажимаем на кнопку Поделиться")
    @DisplayName("Нажимаем на кнопку Поделиться")
    public PushOnProfilePage clickButtonPushOnProfile() {
        log.info("Тык по кнопке Поделиться");
        buttonSaveForPushOnProfile.shouldBe(visible)
                .click();
        return this;
    }
}
