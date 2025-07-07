package org.example.base;

import org.openqa.selenium.By;

/**
 * Утильный класс для получения локаторов элементов.
 */
public abstract class BaseComponent {
    /**
     * Локатор по имени класса.
     * @param className имя CSS-класса
     * @return By.className
     */
    public static By byClass(String className) {
        return By.className(className);
    }

    /**
     * Локатор по атрибуту name.
     * @param name значение атрибута name
     * @return By.name
     */
    public static By byName(String name) {
        return By.name(name);
    }

    /**
     * Локатор по CSS-селектору.
     * @param cssSelector строка селектора
     * @return By.cssSelector
     */
    public static By byCss(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    /**
     * Локатор по XPath-выражению.
     * @param xpath строка XPath
     * @return By.xpath
     */
    public static By byXpath(String xpath) {
        return By.xpath(xpath);
    }

    /**
     * Локатор по точному тексту элемента.
     * @param text текст, который должен точно совпадать
     * @return By.xpath
     */
    public static By byText(String text) {
        return By.xpath(String.format("//*[text()='%s']", text));
    }

    /**
     * Локатор по точному тексту ссылки.
     * @param linkText текст ссылки
     * @return By.linkText
     */
    public static By byLinkText(String linkText) {
        return By.linkText(linkText);
    }
}
