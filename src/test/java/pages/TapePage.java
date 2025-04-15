package pages;


import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static config.ConfigProvider.URL_FEED;

/**
 * Страница Лента
 */
public class TapePage extends LoadableComponent<TapePage> {
    private static final Logger log = LogManager.getLogger(TapePage.class);
    private static final String BUTTON_ON_TAPE = "//button[text()='%s']";
    private final By buttonMenuBurgerMenuMyTape = By.xpath("//a[span='Моя лента']");

    @Override
    protected void load() {
        Selenide.open(URL_FEED);
    }

    @Override
    protected void isLoaded() throws Error {
        log.info("Вызов метода isLoaded()");
        try {
            $(buttonMenuBurgerMenuMyTape).shouldBe(visible.because("Не удалось найти заголовок страницы Лента"));
        } catch (Exception e) {
            throw new Error("Страница Лента не загружена: " + e.getMessage());
        }
    }

    @Step("Проверяем, что кнопка выпадающего списка 'Моя лента' отображается на странице")
    @DisplayName("Проверка бургера на Моя лента на странице Лента")
    public TapePage checkButtonBurgerMenuOnTapePage() {
        log.info("Проверяем, кнопка бургер отображается на странице");
        $(buttonMenuBurgerMenuMyTape).shouldBe(visible.because("Не удалось найти выпадающий список Моя лента"));
        return this;
    }

    /**
     * На пользаке с прода
     */
    @Step("Проверяем, что кнопка '{valueButtonOnTape}' выбрана")
    @DisplayName("Проверка активности кнопки valueButtonOnTape")
    public TapePage checkButtonIsSelectOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что с атрибутом aria-selected активна кнопка: %s", valueButtonOnTape));
        By elementButtonOnTape = By.xpath(String.format(BUTTON_ON_TAPE, valueButtonOnTape));
        $(elementButtonOnTape).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueButtonOnTape
        )).shouldHave(attribute("aria-selected", "true"));
        return this;
    }

    /**
     * На пользаке с прода
     */
    @Step("Проверяем, что кнопка '{valueButtonOnTape}' не выбрана")
    @DisplayName("Проверяем неактивную кнопку")
    public TapePage checkButtonIsNotSelectOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что с атрибутом aria-selected не активна кнопка: %s", valueButtonOnTape));
        By elementButtonOnTape = By.xpath(String.format(BUTTON_ON_TAPE, valueButtonOnTape));
        $(elementButtonOnTape).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueButtonOnTape
        )).shouldHave(attribute("aria-selected", "false"));
        return this;
    }

    /**
     * На пользаке с прода
     */
    @Step("Проверяем, что кнопка '{valueButtonOnTape}' отображается на странице")
    @DisplayName("Проверяем видимость кнопки на странице")
    public TapePage checkButtonVisibleOnTapePage(String valueButtonOnTape) {
        log.info(String.format("Проверяем, что кнопка %s отображается", valueButtonOnTape));
        $x(String.format(BUTTON_ON_TAPE, valueButtonOnTape)).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueButtonOnTape
        ));
        return this;
    }

    /**
     * На пользаке с прода
     */
    @Step("Проверяем, что кнопка '{valueActiveButtonOnTape}' активна после нажатия и кнопка {valueNotActiveButtonOnTape} неактивна")
    @DisplayName("Проверяем атрибуты кнопок после действий на странице")
    public TapePage checkActiveButtonAfterClickOnTapePage(String valueActiveButtonOnTape, String valueNotActiveButtonOnTape) {
        log.info(String.format("Проверяем, что после нажатия на кнопку %s атрибут aria-selected true", valueActiveButtonOnTape));
        By elementActiveButtonOnTape = By.xpath(String.format(BUTTON_ON_TAPE, valueActiveButtonOnTape));
        By elementNotActiveButtonOnTape = By.xpath(String.format(BUTTON_ON_TAPE, valueNotActiveButtonOnTape));
        $(elementActiveButtonOnTape).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueActiveButtonOnTape
        )).click();
        $(elementActiveButtonOnTape).shouldHave(attribute("aria-selected", "true"));
        $(elementNotActiveButtonOnTape).shouldBe(visible.because(
                "Не удалось найти кнопку " + valueNotActiveButtonOnTape
        )).shouldHave(attribute("aria-selected", "false"));
        return this;
    }
}