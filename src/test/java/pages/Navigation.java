package pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class Navigation {
    private static final Logger log = LogManager.getLogger(Navigation.class);
    private static String NAVIGATION_MENU_TEMPLATE = "//div[@class='navigation']//div[text()='%s']"; // Шаблон для перехода по вкладкам Меню Навигации

    @Step("Нажимаем на '{elementInMenuNavigation}' в меню Навигации")
    public void clickElementInMenuNavigation(String elementInMenuNavigation) {
        log.info(String.format("Нажимаем на элемент в меню навигации: %s", elementInMenuNavigation));
        SelenideElement elementInNavigationMenu = $x(String.format(NAVIGATION_MENU_TEMPLATE, elementInMenuNavigation));
        elementInNavigationMenu
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(elementInMenuNavigation)).click();
    }
}
