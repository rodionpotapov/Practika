// src/main/java/org/example/base/SettingsPage.java
package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ButtonElement;
import org.example.element.LinkElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object для панели быстрых настроек Gmail и перехода к полным настройкам.
 */
public class SettingsPage extends BasePage {
    private static final By ALL_SETTINGS_BTN = BaseComponent.byCss("button.Tj[aria-label='Все настройки']");
    private static final By FILTERS_TAB      = BaseComponent.byLinkText("Фильтры и заблокированные адреса");
    private static final By LANGUAGE_SELECT  = BaseComponent.byCss("select.a5p");
    private static final By SAVE_CHANGES_BTN = BaseComponent.byCss("button[guidedhelpid='save_changes_button']");

    private final ButtonElement allSettingsBtn;
    private final LinkElement filtersTab;
    private final Select languageSelect;
    private final ButtonElement saveChangesBtn;

    public SettingsPage(WebDriver driver) {
        super(driver);
        // Инициализируем кнопки и ссылки с новым конструктором Button(driver, wait, locator)
        this.allSettingsBtn = new ButtonElement(driver, wait, ALL_SETTINGS_BTN);
        this.filtersTab     = new LinkElement(wait, FILTERS_TAB);
        // Селектор языка
        WebElement langElem = wait.until(d -> d.findElement(LANGUAGE_SELECT));
        this.languageSelect = new Select(langElem);
        this.saveChangesBtn = new ButtonElement(driver, wait, SAVE_CHANGES_BTN);
    }

    /** Открывает полный раздел «Все настройки». */
    public void clickAllSettings() {
        allSettingsBtn.click();
    }

    /** Переходит на вкладку «Фильтры и заблокированные адреса». */
    public FiltersPage openFiltersPage() {
        filtersTab.click();
        return new FiltersPage(driver);
    }

    /** Выбирает язык по значению атрибута value селектора. */
    public void selectLanguage(String langValue) {
        languageSelect.selectByValue(langValue);
    }

    /** Сохраняет изменения настроек. */
    public void clickSaveChanges() {
        saveChangesBtn.click();
    }
}
