package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object для панели быстрых настроек Gmail и работы с полными настройками.
 */
public class SettingsPage extends BasePage {
    // Локатор кнопки перехода в полный раздел настроек
    private static final By ALL_SETTINGS_BTN  =
            BaseComponent.byCss("button.Tj[aria-label='Все настройки']");
    // Локатор вкладки "Фильтры и заблокированные адреса"
    private static final By FILTERS_TAB       =
            BaseComponent.byLinkText("Фильтры и заблокированные адреса");
    // Локатор селекта языка
    private static final By LANGUAGE_SELECT   =
            BaseComponent.byCss("select.a5p");
    // Локатор кнопки "Сохранить изменения"
    private static final By SAVE_CHANGES_BTN  =
            BaseComponent.byCss("button[guidedhelpid='save_changes_button']");

    /**
     * Конструктор: ждёт появления кнопки "Все настройки" после открытия quick settings.
     *
     * @param driver экземпляр ChromeDriver
     */
    public SettingsPage(ChromeDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.elementToBeClickable(ALL_SETTINGS_BTN));
    }

    /**
     * Нажимает кнопку "Все настройки" для перехода в полный интерфейс настроек.
     */
    public void clickAllSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(ALL_SETTINGS_BTN)).click();
    }

    /**
     * Открывает вкладку "Фильтры и заблокированные адреса" в полном интерфейсе настроек.
     *
     * @return новый объект FiltersPage
     */
    public FiltersPage openFiltersPage() {
        wait.until(ExpectedConditions.elementToBeClickable(FILTERS_TAB)).click();
        return new FiltersPage(driver);
    }

    /**
     * Выбирает язык интерфейса.
     *
     * @param langValue значение опции (например, "en-GB")
     */
    public void selectLanguage(String langValue) {
        WebElement selectElem = wait.until(
                ExpectedConditions.visibilityOfElementLocated(LANGUAGE_SELECT)
        );
        new Select(selectElem).selectByValue(langValue);
    }

    /**
     * Нажимает кнопку "Сохранить изменения" на странице настроек.
     */
    public void clickSaveChanges() {
        wait.until(ExpectedConditions.elementToBeClickable(SAVE_CHANGES_BTN)).click();
    }
}
