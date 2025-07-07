package org.example.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * Базовый класс для кликабельных элементов.
 */
public class ClickElement {
    private static final Logger log = Logger.getLogger(ClickElement.class.getName());
    private final WebDriverWait wait;
    private final WebElement element;

    /**
     * Конструктор.
     * @param wait WebDriverWait
     * @param element готовый WebElement
     */
    public ClickElement(WebDriverWait wait, WebElement element) {
        this.wait    = wait;
        this.element = element;
    }

    /**
     * Фабричный метод: ждём кликабельности по локатору,
     * скроллим и возвращаем ClickElement.
     * @param driver WebDriver
     * @param locator локатор элемента
     * @return готовый ClickElement
     */
    public static ClickElement of(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return new ClickElement(wait, el);
    }

    /**
     * Скроллит в элемент и кликает.
     */
    public void click() {
        log.info("Clicking element: " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((org.openqa.selenium.JavascriptExecutor) wait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)))
                .executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
}
