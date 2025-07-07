// src/main/java/org/example/base/Button.java
package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Универсальный компонент для кнопок.
 * Обеспечивает ожидание кликабельности и выполнение JS-клика при необходимости.
 */
public class Button {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By locator;

    /**
     * Конструктор компонента кнопки.
     *
     * @param driver  экземпляр WebDriver
     * @param wait    экземпляр WebDriverWait для ожиданий
     * @param locator локатор кнопки
     */
    public Button(WebDriver driver, WebDriverWait wait, By locator) {
        this.driver  = driver;
        this.wait    = wait;
        this.locator = locator;
    }

    /**
     * Кликает по кнопке: сначала нативно, при ошибке — через JavaScript.
     */
    public void click() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            el.click();
        } catch (Exception ex) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    /**
     * Возвращает текст кнопки.
     *
     * @return видимый текст элемента
     */
    public String getText() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }
}
