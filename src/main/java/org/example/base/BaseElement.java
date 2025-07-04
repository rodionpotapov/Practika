package org.example.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

/**
 * Базовый класс-обёртка для WebElement.
 */
public abstract class BaseElement {
    protected WebElement element;
    protected WebDriverWait wait;
    private static final Logger log = Logger.getLogger(BaseElement.class.getName());

    /**
     * @param element WebElement для обёртки
     * @param wait    WebDriverWait для ожиданий
     */
    protected BaseElement(WebElement element, WebDriverWait wait) {
        this.element = element;
        this.wait = wait;
    }

    /**
     * Клик по элементу.
     */
    public void click() {
        log.info("Clicking element: " + element);
        wait.until(d -> element.isDisplayed() && element.isEnabled());
        element.click();
    }

    /**
     * Ввод текста.
     * @param text что вводим
     */
    public void enterText(String text) {
        log.info("Entering text '" + text + "' into element: " + element);
        wait.until(d -> element.isDisplayed());
        element.clear();
        element.sendKeys(text);
    }
}
