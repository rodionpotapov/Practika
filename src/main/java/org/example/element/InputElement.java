// src/main/java/org/example/base/Input.java
package org.example.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Универсальный компонент для полей ввода текста.
 * Берёт на себя очистку и ввод значения.
 */
public class InputElement {
    private final WebDriverWait wait;
    private final By locator;

    /**
     * Конструктор компонента поля ввода.
     *
     * @param wait    экземпляр WebDriverWait для ожиданий
     * @param locator локатор поля ввода
     */
    public InputElement(WebDriverWait wait, By locator) {
        this.wait    = wait;
        this.locator = locator;
    }

    /**
     * Очищает поле и вводит заданный текст.
     *
     * @param value текст для ввода
     */
    public void setValue(String value) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(value);
    }

    /**
     * Считывает текущее значение атрибута value.
     *
     * @return текущее значение поля ввода
     */
    public String getValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getAttribute("value");
    }
}
