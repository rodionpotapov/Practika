package org.example.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;

/**
 * Базовый класс-обёртка для любых WebElement.
 */
public abstract class BaseElement {
    protected final WebElement element;
    protected final WebDriverWait wait;
    private static final Logger log = Logger.getLogger(BaseElement.class.getName());

    protected BaseElement(WebDriverWait wait, WebElement element) {
        this.wait = wait;
        this.element = element;
    }

    /**
     * Ввод текста (без клика).
     */
    public void enterText(String text) {
        log.info("Entering text '" + text + "' into element: " + element);
        wait.until(d -> element.isDisplayed());
        element.clear();
        element.sendKeys(text);
    }
}
