package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static constants.attribute.AttributeValues.DISABLED;
import static constants.pushOnProfilePage.PushOnProfileValues.*;
import static constants.valueInMethods.ValueMethods.CALL_METHOD_IS_LOADED;
import static constants.valueInMethods.ValueMethods.CALL_METHOD_LOAD;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Опубликовать на странице Профиля
 */
public class PushOnProfilePage extends LoadableComponent<PushOnProfilePage> {
    private static final Logger log = LogManager.getLogger(PushOnProfilePage.class);
    private static final By BUTTON_PUBLISH = By.xpath("//button[@data-tsid='ddm-button']"); // Кнопка Опубликовать на
    private static final By MENU_IN_PUBLISH = By.xpath("//div[@role='menu']");
    private static final By TITLE_PUBLISH = By.xpath("//span[@role='menuitem']//h3[text()='Опубликовать']");
    private static final By BUTTON_SHARE = By.xpath("//button[@data-l='t,button.submit']");
    private static final By FIELD_INPUT = By.xpath("//div[@data-module='postingForm/mediaText']");
    private static final By HINT_TEXT_IN_FIELD_INPUT = By.xpath("//div[@class='input_placeholder']");
    private static final By HINT_DESCRIPTION_IN_FIELD_INPUT = By.xpath("//div[@class='input_placeholder_desc']");


    @Override
    protected void load() {
        log.info(CALL_METHOD_LOAD);
        clickButtonPush();
    }

    @Override
    protected void isLoaded() throws Error {
        log.info(CALL_METHOD_IS_LOADED);
        checkVisibleButtonPush();
    }

    @Step("Проверяем отображение кнопки Опубликовать")
    @DisplayName("Проверка, что кнопка Опубликовать на странице")
    public PushOnProfilePage checkVisibleButtonPush() {
        log.info("Проверка кнопки Опубликовать на странице");
        $(BUTTON_PUBLISH).shouldBe(visible.because(
                "Не удалось найти кнопку Опубликовать"
        )).shouldHave(exactText(TITLE_CHOICE_LIST_MENU).because(
                String.format("Текст кнопки не совпадает с %s", TITLE_CHOICE_LIST_MENU)
        ));
        return this;
    }

    @Step("Проверяем, что нажали на кнопку Опубликовать")
    @DisplayName("Проверка нажатия на кнопку Опубликовать")
    public PushOnProfilePage checkVisibleOpenPushOnProfilePage() {
        log.info("Проверка, что открылась страница Опубликовать");
        $(TITLE_PUBLISH).shouldBe(visible.because(
                "Не удалось найти заголовок на странице Опубликовать"
        ));
        return this;
    }


    @Step("Нажимаем на кнопку Опубликовать")
    @DisplayName("Нажимаем на кнопку Опубликовать")
    public PushOnProfilePage clickButtonPush() {
        log.info("Тык по кнопке Опубликовать");
        $(BUTTON_PUBLISH).shouldBe(visible.because(
                "Не удалось нажать на кнопку Опубликовать"
        )).click();
        return this;
    }

    @Step("Нажимаем на кнопку '{choiceButtonInListMenu}' в выпадающем списке Опубликовать")
    @DisplayName("Нажимаем на кнопку в выпадающем списке Опубликовать")
    public PushOnProfilePage clickChoiceButtonInListMenu(String choiceButtonInListMenu) {
        log.info(String.format("Тык по кнопке %s в списке Опубликовать", choiceButtonInListMenu));
        $(MENU_IN_PUBLISH).find(By.xpath(String.format(".//*[text()='%s']/ancestor::a", choiceButtonInListMenu)))
                .shouldBe(visible.because(
                        "Не удалось нажать на кнопку " + choiceButtonInListMenu
                )).click();
        return this;
    }

    @Step("Проверяем подсказки в поле ввода")
    @DisplayName("Проверяем, что подсказки видны в поле ввода")
    public PushOnProfilePage checkVisibleHintInInputField() {
        log.info("Проверка подсказок в поле ввода");
        $(HINT_TEXT_IN_FIELD_INPUT).shouldBe(visible.because(
                "Не удалось найти подсказку 'Введите текст'"
        ));
        $(HINT_DESCRIPTION_IN_FIELD_INPUT).shouldBe(visible.because(
                "Не удалось найти подсказку 'Используйте @ для упоминания...'"
        ));
        String actualHintDescriptionInFieldInput = $(HINT_DESCRIPTION_IN_FIELD_INPUT).getText().trim();
        assertEquals(
                HINT_DESCRIPTION_IN_TEXT_FIELD,
                actualHintDescriptionInFieldInput,
                String.format(
                        "Текст подсказок не совпадает.\nОжидалось: %s\nФактически: %s",
                        HINT_DESCRIPTION_IN_TEXT_FIELD,
                        actualHintDescriptionInFieldInput
                )
        );
        return this;
    }

    @Step("Вводим текст '{textInputInFieldForPushProfile}' в поле ввода")
    @DisplayName("Вводим текст в поле ввода")
    public PushOnProfilePage inputTextInField(String textInputInFieldForPushProfile) {
        log.info("Вводим текст для публикации");
        $(FIELD_INPUT).shouldBe(visible.because(
                "Не удалось найти поле для ввода"
        )).setValue(textInputInFieldForPushProfile);
        return this;
    }

    @Step("Проверяем атрибут у неактивной кнопки Поделиться")
    @DisplayName("Проверяем, что кнопка Поделиться неактивна")
    public PushOnProfilePage checkButtonShareDisabled() {
        log.info("Проверка неактивной кнопки Поделиться");
        $(BUTTON_SHARE).shouldBe(visible.because(
                "Не удалось найти кнопку Поделиться"
        )).shouldHave(attribute(DISABLED, "true").because(
                String.format("Ожидался атрибут %s='true' у кнопки '%s'",
                        DISABLED,
                        SHARE
                )
        ));
        return this;
    }

    @Step("Нажимаем на кнопку Поделиться")
    @DisplayName("Нажимаем на кнопку Поделиться")
    public PushOnProfilePage clickButtonShare() {
        log.info("Тык по кнопке Поделиться");
        $(BUTTON_SHARE).shouldBe(visible.because(
                "Не удалось найти кнопку Поделиться"
        )).click();
        return this;
    }
}