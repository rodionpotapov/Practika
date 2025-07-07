package org.example.element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Универсальный компонент для кнопок.
 * Ожидает кликабельности и при необходимости делает JS-клик.
 */
public class ButtonElement {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By locator;

    /**
     * Конструктор кнопки.
     * @param driver WebDriver
     * @param wait WebDriverWait
     * @param locator локатор кнопки
     */
    public ButtonElement(WebDriver driver, WebDriverWait wait, By locator) {
        this.driver  = driver;
        this.wait    = wait;
        this.locator = locator;
    }

    /**
     * Фабричный метод для создания и ожидания готовности кнопки.
     * @param driver WebDriver
     * @param locator локатор кнопки
     * @return готовый Button
     */
    public static ButtonElement of(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return new ButtonElement(driver, wait, locator);
    }

    /**
     * Кликает по кнопке: сначала нативно, при ошибке — через JS.
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
     * @return видимый текст элемента
     */
    public String getText() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
    }
}
