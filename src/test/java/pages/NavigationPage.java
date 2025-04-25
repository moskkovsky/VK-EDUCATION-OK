package pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.LoadableComponent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static constants.navigation.NavigationValue.arrayMenu;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationPage extends LoadableComponent<NavigationPage> {
    private static final Logger log = LogManager.getLogger(NavigationPage.class);
    private static final By MENU_NAVIGATION = By.id("hook_Block_Navigation");

    @Override
    protected void load() {
        refresh();
    }


    @Override
    protected void isLoaded() throws Error {

    }

    @Step("Нажимаем на '{valueInMenuNavigation}' в меню Навигации")
    public void clickElementInMenuNavigation(String valueInMenuNavigation) {
        log.info(String.format("Нажимаем на элемент в меню навигации: %s", valueInMenuNavigation));
        $(getSelenideElementInNavigationMenu(valueInMenuNavigation)).shouldBe(visible.because(
                "Не удалось нажать на кнопку " + valueInMenuNavigation + " из меню навигации"
        )).click();
    }

    @Step("Проверяем, что кнопки навигации отображаются на странице")
    public NavigationPage checkVisibleElementsInNavigationMenu() {
        log.info("Проверяем, что все кнопки на меню навигации присутствуют");
        assertAll("Все кнопки из навигации присутствуют",
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[0])).getText().trim(), arrayMenu[0]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[1])).getText().trim(), arrayMenu[1]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[2])).getText().trim(), arrayMenu[2]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[3])).getText().trim(), arrayMenu[3]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[4])).getText().trim(), arrayMenu[4]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[5])).getText().trim(), arrayMenu[5]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[6])).getText().trim(), arrayMenu[6]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[7])).getText().trim(), arrayMenu[7]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[8])).getText().trim(), arrayMenu[8]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[9])).getText().trim(), arrayMenu[9]),
                () -> assertEquals($(getSelenideElementInNavigationMenu(arrayMenu[10])).getText().trim(), arrayMenu[10])
        );
        return this;
    }

    private SelenideElement getSelenideElementInNavigationMenu(String elementInMenuNavigation) {
        return $(MENU_NAVIGATION)
                .find(By.xpath(String.format(".//*[text()='%s']", elementInMenuNavigation)))
                .shouldBe(visible.because("Не найден элемент навигации: " + elementInMenuNavigation));
    }
}

