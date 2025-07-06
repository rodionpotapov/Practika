// src/main/java/org/example/base/Link.java
package org.example.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Универсальный компонент для ссылок.
 * Обеспечивает ожидание кликабельности и клик по элементу.
 */
public class LinkElement {
    private final WebDriverWait wait;
    private final By locator;

    /**
     * Конструктор компонента ссылки.
     *
     * @param wait    экземпляр WebDriverWait для ожиданий
     * @param locator локатор ссылки
     */
    public LinkElement(WebDriverWait wait, By locator) {
        this.wait    = wait;
        this.locator = locator;
    }

    /**
     * Кликает по ссылке.
     */
    public void click() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((org.openqa.selenium.JavascriptExecutor) wait.until(d -> el))
                .executeScript("arguments[0].scrollIntoView(true);", el);
        el.click();
    }

    /**
     * Возвращает значение href ссылки.
     *
     * @return значение атрибута href
     */
    public String getHref() {
        return wait.until(ExpectedConditions.elementToBeClickable(locator))
                .getAttribute("href");
    }

    /**
     * Возвращает текст ссылки.
     *
     * @return текст внутри элемента
     */
    public String getText() {
        return wait.until(ExpectedConditions.elementToBeClickable(locator))
                .getText();
    }
}
