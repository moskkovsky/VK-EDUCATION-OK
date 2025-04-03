package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;
import static constants.navigation.NavigationValue.arrayMenu;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigationPage {
    private static final Logger log = LogManager.getLogger(NavigationPage.class);
    private static String NAVIGATION_MENU_TEMPLATE = "//div[@class='navigation']//div[text()='%s']"; // Шаблон для перехода по вкладкам Меню Навигации


    @Step("Нажимаем на '{valueInMenuNavigation}' в меню Навигации")
    public void clickElementInMenuNavigation(String valueInMenuNavigation) {
        log.info(String.format("Нажимаем на элемент в меню навигации: %s", valueInMenuNavigation));
        getSelenideElementInNavigationMenu(valueInMenuNavigation).click();
    }

    @Step("Проверяем, что кнопки навигации отображаются на странице")
    public void checkVisibleElementsInNavigationMenu() {
        log.info("Проверяем, что все кнопки на меню навигации присутствуют");
        assertAll("Все кнопки из навигации присутствуют",
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[0]).getText().trim(), arrayMenu[0]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[1]).getText().trim(), arrayMenu[1]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[2]).getText().trim(), arrayMenu[2]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[3]).getText().trim(), arrayMenu[3]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[4]).getText().trim(), arrayMenu[4]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[5]).getText().trim(), arrayMenu[5]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[6]).getText().trim(), arrayMenu[6]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[7]).getText().trim(), arrayMenu[7]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[8]).getText().trim(), arrayMenu[8]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[9]).getText().trim(), arrayMenu[9]),
                () -> assertEquals(getSelenideElementInNavigationMenu(arrayMenu[10]).getText().trim(), arrayMenu[10])
        );
    }

    private SelenideElement getSelenideElementInNavigationMenu(String elementInMenuNavigation) {
        SelenideElement elementInNavigationMenu = $x(String.format(NAVIGATION_MENU_TEMPLATE, elementInMenuNavigation));
        return elementInNavigationMenu
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(elementInMenuNavigation));
    }
}

