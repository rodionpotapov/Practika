package org.example.element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Универсальный компонент для ссылок.
 * Ожидает кликабельности и выполняет скролл+клик.
 */
public class LinkElement {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By locator;

    /**
     * Конструктор ссылки.
     * @param driver WebDriver
     * @param wait WebDriverWait
     * @param locator локатор ссылки
     */
    public LinkElement(WebDriver driver, WebDriverWait wait, By locator) {
        this.driver  = driver;
        this.wait    = wait;
        this.locator = locator;
    }

    /**
     * Фабричный метод для создания и ожидания готовности ссылки.
     * @param driver WebDriver
     * @param locator локатор ссылки
     * @return готовый Link
     */
    public static LinkElement of(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return new LinkElement(driver, wait, locator);
    }

    /**
     * Кликает по ссылке.
     */
    public void click() {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
        el.click();
    }

    /**
     * Возвращает значение href ссылки.
     * @return атрибут href
     */
    public String getHref() {
        return wait.until(ExpectedConditions.elementToBeClickable(locator)).getAttribute("href");
    }

    /**
     * Возвращает текст ссылки.
     * @return текст внутри элемента
     */
    public String getText() {
        return wait.until(ExpectedConditions.elementToBeClickable(locator)).getText();
    }
}
