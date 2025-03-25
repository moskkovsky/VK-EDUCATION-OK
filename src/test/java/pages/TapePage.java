package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$x;
public class TapePage {
    private static final Logger log = LogManager.getLogger(TapePage.class);
    private static String BUTTON_ON_TAPE = "//button[text()='%s']";

    @Step("Проверяем, что кнопка '{valueButtonOnTape}' выбрана")
    @DisplayName("Проверка активности кнопки valueButtonOnTape")
    public TapePage checkButtonIsSelectOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что с атрибутом aria-selected активна кнопка: %s", valueButtonOnTape));
        SelenideElement elementButtonOnTape = $x(String.format(BUTTON_ON_TAPE, valueButtonOnTape));
        elementButtonOnTape.shouldBe(Condition.visible).shouldHave(attribute("aria-selected", "true"));
        return this;
    }

    @Step("Проверяем, что кнопка '{valueButtonOnTape}' не выбрана")
    @DisplayName("Проверяем неактивную кнопку")
    public TapePage checkButtonIsNotSelectOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что с атрибутом aria-selected не активна кнопка: %s", valueButtonOnTape));
        SelenideElement elementButtonOnTape = $x(String.format(BUTTON_ON_TAPE, valueButtonOnTape));
        elementButtonOnTape.shouldBe(Condition.visible).shouldHave(attribute("aria-selected", "false"));
        return this;
    }

    @Step("Проверяем, что кнопка '{valueButtonOnTape}' отображается на странице")
    @DisplayName("Проверяем видимость кнопки на странице")
    public TapePage checkButtonVisibleOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что кнопка %s отображается", valueButtonOnTape));
        $x(String.format(BUTTON_ON_TAPE, valueButtonOnTape)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверяем, что кнопка '{valueActiveButtonOnTape}' активна после нажатия и кнопка {valueNotActiveButtonOnTape} неактивна")
    @DisplayName("Проверяем атрибуты кнопок после действий на странице")
    public TapePage checkActiveButtonAfterClickOnTapePage(String valueActiveButtonOnTape, String valueNotActiveButtonOnTape) {
        log.info(String.format("Проверяем, что после нажатия на кнопку %s атрибут aria-selected true", valueActiveButtonOnTape));
        SelenideElement elementActiveButtonOnTape = $x(String.format(BUTTON_ON_TAPE, valueActiveButtonOnTape));
        SelenideElement elementNotActiveButtonOnTape = $x(String.format(BUTTON_ON_TAPE, valueNotActiveButtonOnTape));
        elementActiveButtonOnTape.shouldBe(Condition.visible).click();
        elementActiveButtonOnTape.shouldBe(Condition.visible)
                .shouldHave(attribute("aria-selected", "true"));
        elementNotActiveButtonOnTape.shouldBe(Condition.visible)
                .shouldHave(attribute("aria-selected", "false"));
        return this;
    }
}
