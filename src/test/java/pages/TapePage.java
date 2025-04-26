package pages;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static config.ConfigProvider.URL_FEED;
import static constants.attribute.AttributeValues.ARIA_SELECTED;
import static constants.tape.TapeValues.*;
import static constants.valueInMethods.ValueMethods.*;

/**
 * Страница Лента
 */
public class TapePage extends LoadableComponent<TapePage> {
    private static final Logger log = LogManager.getLogger(TapePage.class);
    private static final String BUTTON_ON_TAPE = "//button[text()='%s']";
    private static final By BUTTON_HOBBIES = By.xpath("//button[@data-l='t,to_hobbies']");
    private static final By BUTTON_MOMENTS = By.xpath("//button[@data-l='t,to_moments']");

    @Override
    protected void load() {
        log.info(CALL_METHOD_LOAD);
        Selenide.open(URL_FEED);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info(CALL_METHOD_IS_LOADED);
        try {
            checkVisibleOpenTapePage();
        } catch (Exception exception) {
            throw new Error("Страница Лента не загружена: " + exception.getMessage());
        }
    }

    @Step("Проверяем, что открылась страница Лента")
    @DisplayName("Проверка открытии страницы: Tape")
    public TapePage checkVisibleOpenTapePage() {
        log.info("Проверка, что открылась страница Лента");
        $(BUTTON_HOBBIES).shouldBe(visible.because(
                "Не удалось найти кнопку Увлечения на странице Лента"
        ));
        $(BUTTON_MOMENTS).shouldBe(visible.because(
                "Не удалось найти кнопку Моменты на странице Лента"
        ));
        return this;
    }

    @Step("Проверяем, что кнопка '{valueButtonOnTape}' выбрана")
    @DisplayName("Проверка активности кнопки valueButtonOnTape")
    public TapePage checkButtonIsSelectOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что с атрибутом aria-selected активна кнопка: %s", valueButtonOnTape));
        By elementButtonOnTape = By.xpath(String.format(BUTTON_ON_TAPE, valueButtonOnTape));
        $(elementButtonOnTape).shouldBe(visible.because(
                String.format("Не удалось найти кнопку на странице: %s", valueButtonOnTape)
        )).shouldHave(attribute(ARIA_SELECTED, "true").because(
                String.format("Ожидался атрибут %s='true' у кнопки '%s'",
                        ARIA_SELECTED,
                        valueButtonOnTape
                )
        ));
        return this;
    }


    @Step("Проверяем, что кнопка '{valueButtonOnTape}' не выбрана")
    @DisplayName("Проверяем неактивную кнопку")
    public TapePage checkButtonIsNotSelectOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что с атрибутом aria-selected не активна кнопка: %s", valueButtonOnTape));
        By elementButtonOnTape = By.xpath(String.format(BUTTON_ON_TAPE, valueButtonOnTape));
        $(elementButtonOnTape).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueButtonOnTape
        )).shouldHave(attribute(ARIA_SELECTED, "false").because(
                String.format("Ожидался атрибут %s='false' у кнопки '%s'",
                        ARIA_SELECTED,
                        valueButtonOnTape
                )
        ));
        return this;
    }

    @Step("Проверяем, что кнопка '{valueButtonOnTape}' отображается на странице")
    @DisplayName("Проверяем видимость кнопки на странице")
    public TapePage checkButtonVisibleOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что кнопка %s отображается", valueButtonOnTape));
        By actualButton = By.xpath(String.format(BUTTON_ON_TAPE, valueButtonOnTape));
        $(actualButton).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueButtonOnTape
        )).shouldHave(exactText(valueButtonOnTape).because(
                String.format("Текст кнопки %s не совпадает", valueButtonOnTape)
        ));

        return this;
    }

    @Step("Проверяем, что кнопка '{valueActiveButtonOnTape}' активна после нажатия и кнопка {valueNotActiveButtonOnTape} неактивна")
    @DisplayName("Проверяем атрибуты кнопок после действий на странице")
    public TapePage checkActiveButtonAfterClickOnTapePage(String valueActiveButtonOnTape, String valueNotActiveButtonOnTape) {
        log.info(String.format("Проверяем, что после нажатия на кнопку %s атрибут aria-selected true", valueActiveButtonOnTape));
        By elementActiveButtonOnTape = By.xpath(String.format(BUTTON_ON_TAPE, valueActiveButtonOnTape));
        By elementNotActiveButtonOnTape = By.xpath(String.format(BUTTON_ON_TAPE, valueNotActiveButtonOnTape));
        $(elementActiveButtonOnTape).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueActiveButtonOnTape
        )).click();
        $(elementActiveButtonOnTape).shouldHave(attribute(ARIA_SELECTED, "true").because(
                String.format("Ожидался атрибут %s='true' у кнопки '%s'",
                        ARIA_SELECTED,
                        valueActiveButtonOnTape
                )
        ));
        $(elementNotActiveButtonOnTape).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueNotActiveButtonOnTape
        )).shouldHave(attribute(ARIA_SELECTED, "false").because(
                String.format("Ожидался атрибут %s='false' у кнопки '%s'",
                        ARIA_SELECTED,
                        valueNotActiveButtonOnTape
                )
        ));
        return this;
    }
}