package org.example.element;

import org.openqa.selenium.By;

/**
 * Утильный класс для получения локаторов элементов.
 */
public abstract class BaseComponent {
    /** Локатор по имени класса. */
    public static By byClass(String className) {
        return By.className(className);
    }

    /** Локатор по атрибуту name. */
    public static By byName(String name) {
        return By.name(name);
    }

    /** Локатор по CSS-селектору. */
    public static By byCss(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    /** Локатор по XPath-выражению. */
    public static By byXpath(String xpath) {
        return By.xpath(xpath);
    }

    /** Локатор по точному тексту элемента. */
    public static By byText(String text) {
        return By.xpath(String.format("//*[text()='%s']", text));
    }

    /** Локатор по тексту ссылки. */
    public static By byLinkText(String linkText) {
        return By.linkText(linkText);
    }
}
