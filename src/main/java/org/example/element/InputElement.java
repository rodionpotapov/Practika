package org.example.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Универсальный компонент для полей ввода текста.
 * Берёт на себя ожидание, очистку и ввод значения.
 */
public class InputElement {
    private final WebDriverWait wait;
    private final By locator;

    /**
     * Конструктор компонента поля ввода.
     * @param wait экземпляр WebDriverWait
     * @param locator локатор поля ввода
     */
    public InputElement(WebDriverWait wait, By locator) {
        this.wait    = wait;
        this.locator = locator;
    }

    /**
     * Фабричный метод для создания и ожидания готовности поля.
     * @param driver WebDriver
     * @param locator локатор поля ввода
     * @return готовый Input
     */
    public static InputElement of(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return new InputElement(wait, locator);
    }

    /**
     * Очищает поле и вводит заданный текст.
     * @param value текст для ввода
     */
    public void setValue(String value) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(value);
    }

    /**
     * Считывает текущее значение атрибута value.
     * @return текущее значение поля ввода
     */
    public String getValue() {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return el.getAttribute("value");
    }
}
