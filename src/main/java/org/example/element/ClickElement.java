package org.example.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

/**
 * Базовый класс для кликабельных элементов.
 */
public class ClickElement {
    protected final WebElement element;
    protected final WebDriverWait wait;
    private static final Logger log = Logger.getLogger(ClickElement.class.getName());

    public ClickElement(WebDriverWait wait, WebElement element) {
        this.wait = wait;
        this.element = element;
    }

    /**
     * Ждём кликабельность, скроллим и кликаем.
     */
    public void click() {
        log.info("Clicking element: " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((org.openqa.selenium.JavascriptExecutor) wait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)))
                .executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
}
