// src/main/java/org/example/base/SettingsPage.java
package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object для страницы «Все настройки» Gmail.
 */
public class SettingsPage extends BasePage {
    /** Кнопка «Все настройки» в быстром меню */
    private static final By ALL_SETTINGS_BTN  = By.xpath("//button[normalize-space(text())='Все настройки']");
    /** Секция «Фильтры и заблокированные адреса» */
    private static final By FILTERS_TAB       = By.xpath("//a[normalize-space(text())='Фильтры и заблокированные адреса']");
    /** Выпадающий список выбора языка */
    private static final By LANGUAGE_SELECT   = By.cssSelector("select.a5p");
    /** Кнопка «Сохранить изменения» внизу формы */
    private static final By SAVE_CHANGES_BTN  = By.xpath("//button[normalize-space(text())='Сохранить изменения']");

    public SettingsPage(WebDriver driver) {
        super(driver);
        // Ждём доступности кнопки «Все настройки»
        wait.until(d -> d.findElement(ALL_SETTINGS_BTN).isDisplayed());
    }

    /** Нажимает кнопку «Все настройки». */
    public SettingsPage clickAllSettings() {
        WebElement btn = wait.until(d -> d.findElement(ALL_SETTINGS_BTN));
        new ClickElement(wait, btn).click();
        return this;
    }

    /** Открывает раздел «Фильтры и заблокированные адреса». */
    public FiltersPage openFiltersPage() {
        WebElement tab = wait.until(d -> d.findElement(FILTERS_TAB));
        new ClickElement(wait, tab).click();
        return new FiltersPage(driver);
    }

    /**
     * Выбирает язык интерфейса по видимому тексту опции.
     *
     * @param visibleText точный текст, например "English (UK)"
     */
    public SettingsPage selectLanguage(String visibleText) {
        WebElement selectElem = wait.until(d -> d.findElement(LANGUAGE_SELECT));
        Select select = new Select(selectElem);
        select.selectByVisibleText(visibleText);
        return this;
    }

    /** Нажимает кнопку «Сохранить изменения». */
    public SettingsPage clickSaveChanges() {
        WebElement btn = wait.until(d -> d.findElement(SAVE_CHANGES_BTN));
        new ClickElement(wait, btn).click();
        return this;
    }
}
