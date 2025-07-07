// src/main/java/org/example/base/SettingsPage.java
package org.example.page;

import org.example.element.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object для страницы «Все настройки» Gmail.
 */
public class SettingsPage extends BasePage {
    private static final By ALL_SETTINGS_BTN  = By.xpath("//button[normalize-space(text())='Все настройки']");
    private static final By FILTERS_TAB       = By.xpath("//a[normalize-space(text())='Фильтры и заблокированные адреса']");
    private static final By LANGUAGE_SELECT   = By.cssSelector("select.a5p");
    private static final By SAVE_CHANGES_BTN  = By.xpath("//button[normalize-space(text())='Сохранить изменения']");

    /**
     * Конструктор: ждём кнопку «Все настройки».
     * @param driver WebDriver
     */
    public SettingsPage(WebDriver driver) {
        super(driver);
        wait.until(d -> d.findElement(ALL_SETTINGS_BTN).isDisplayed());
    }

    /** Нажимает «Все настройки». */
    public SettingsPage clickAllSettings() {
        ButtonElement.of(driver, ALL_SETTINGS_BTN).click();
        return this;
    }

    /** Открывает вкладку фильтров. */
    public FiltersPage openFiltersPage() {
        ButtonElement.of(driver, FILTERS_TAB).click();
        return new FiltersPage(driver);
    }

    /**
     * Меняет язык по видимому тексту.
     * @param visibleText точный текст опции
     */
    public SettingsPage selectLanguage(String visibleText) {
        Select select = new Select(wait.until(d -> d.findElement(LANGUAGE_SELECT)));
        select.selectByVisibleText(visibleText);
        return this;
    }

    /** Сохраняет изменения. */
    public SettingsPage clickSaveChanges() {
        ButtonElement.of(driver, SAVE_CHANGES_BTN).click();
        return this;
    }
}
