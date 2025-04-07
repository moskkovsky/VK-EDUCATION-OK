package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static constants.pushOnProfilePage.PushOnProfileValues.*;

/**
 * Опубликовать на странице Профиля
 */
public class PushOnProfilePage extends LoadableComponent<PushOnProfilePage> {
    private static final Logger log = LogManager.getLogger(PushOnProfilePage.class);
    private final By buttonDropDownChoiceListMenu = By.xpath("//button[@data-testid='ddm-button']");
    private final By blockListMenu = By.xpath("//div[@role='menu']");
    private final By buttonSaveForPushOnProfile = By.xpath("//button[@title='Поделиться']");
    private final By blockInputTextInField = By.xpath("//div[@class='posting_itx-w']");
    private final By fieldForInputPushProfile = By.xpath("//div[@data-module='postingForm/mediaText']");

    @Override
    protected void load() {
        clickButtonPush();
    }

    @Override
    protected void isLoaded() throws Error {
        log.info("Вызов метода isLoaded()");
        try {
            $(buttonDropDownChoiceListMenu).shouldBe(visible.because("Не удалось найти кнопку Опубликовать"));
        } catch (Exception e) {
            throw new Error("Страница Опубликовать не загружена: " + e.getMessage());
        }
    }

    /**
     * Метод неактульный из-за LoadableComponent
     */
    @Step("Проверяем отображение кнопки Опубликовать")
    @DisplayName("Проверка, что кнопка Опубликовать на странице")
    public PushOnProfilePage checkVisibleButtonPush() {
        log.info("Проверка кнопки Опубликовать на странице");
        $(buttonDropDownChoiceListMenu).shouldBe(visible.because(
                "Не удалось найти кнопку Опубликовать"
        ));
        return this;
    }

    @Step("Нажимаем на кнопку Опубликовать")
    @DisplayName("Нажимаем на кнопку Опубликовать")
    public PushOnProfilePage clickButtonPush() {
        log.info("Тык по кнопке Опубликовать");
        $(buttonDropDownChoiceListMenu).shouldBe(visible).click();
        $(blockListMenu).find(By.xpath(".//*[text()='Опубликовать']")).shouldBe(visible.because(
                "Не удалось нажать на кнопку Опубликовать"
        )).shouldHave(exactText(TITLE_CHOICE_LIST_MENU));
        return this;
    }

    @Step("Нажимаем на кнопку '{choiceButtonInListMenu}' в выпадающем списке Опубликовать")
    @DisplayName("Нажимаем на кнопку в выпадающем списке Опубликовать")
    public PushOnProfilePage clickChoiceButtonInListMenu(String choiceButtonInListMenu) {
        log.info(String.format("Тык по кнопке %s в списке Опубликовать", choiceButtonInListMenu));
        $(blockListMenu).find(By.xpath(String.format(".//*[text()='%s']/ancestor::a", choiceButtonInListMenu)))
                .shouldBe(visible.because(
                        "Не удалось нажать на кнопку " + choiceButtonInListMenu
                )).shouldHave(text(choiceButtonInListMenu)).click();
        return this;
    }

    @Step("Проверяем подсказки в поле ввода")
    @DisplayName("Проверяем, что подсказки видны в поле ввода")
    public PushOnProfilePage checkVisibleHintInInputField() {
        log.info("Проверка подсказок в поле ввода");
        $(blockInputTextInField).find(By.xpath(".//*[@class='input_placeholder']"))
                .shouldBe(visible.because(
                        "Не удалось найти подсказку 'Введите текст'"
                )).shouldHave(text(HINT_TITLE_IN_TEXT_FIELD));
        $(blockInputTextInField).find(By.xpath(".//*[@class='input_placeholder_desc']"))
                .shouldBe(visible.because(
                        "Не удалось найти подсказку 'Используйте @ для упоминания...'"
                )).shouldHave(text(HINT_DESCRIPTION_IN_TEXT_FIELD));
        return this;
    }

    @Step("Вводим текст '{textInputInFieldForPushProfile}' в поле ввода")
    @DisplayName("Вводим текст в поле ввода")
    public PushOnProfilePage inputTextInField(String textInputInFieldForPushProfile) {
        log.info("Вводим текст для публикации");
        $(fieldForInputPushProfile).shouldBe(visible.because(
                "Не удалось найти поле для ввода"
        )).setValue(textInputInFieldForPushProfile);
        return this;
    }

    @Step("Проверяем атрибут у неактивной кнопки Поделиться")
    @DisplayName("Проверяем, что кнопка Поделиться неактивна")
    public PushOnProfilePage checkButtonPushOnProfileDisabled() {
        log.info("Проверка неактивной кнопки Поделиться");
        $(buttonSaveForPushOnProfile).shouldBe(visible.because(
                "Не удалось найти кнопку Поделиться"
        )).shouldHave(attribute("disabled", "true"));
        return this;
    }

    @Step("Нажимаем на кнопку Поделиться")
    @DisplayName("Нажимаем на кнопку Поделиться")
    public PushOnProfilePage clickButtonPushOnProfile() {
        log.info("Тык по кнопке Поделиться");
        $(buttonSaveForPushOnProfile).shouldBe(visible.because(
                "Не удалось найти кнопку Поделиться"
        )).click();
        return this;
    }
}