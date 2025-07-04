package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для расширённого поиска в Gmail.
 */
public class AdvancedSearchPage extends BasePage {
    // Локаторы
    private static final By BTN_ADV      = BaseComponent.byCss("button[aria-label='Расширенный поиск']");
    private static final By INPUT1       = BaseComponent.byCss("input.ZH.nr.aQd");
    private static final By INPUT2       = BaseComponent.byCss("input.ZH.nr.aQb");
    private static final By BTN_SEARCH   = BaseComponent.byCss("div[aria-label='Поиск почты']");
    private static final By BTN_CLEAR    = BaseComponent.byCss("button[aria-label='Очистить поле поиска']");

    /**
     * Конструктор: ждёт появления титула "Входящие".
     * @param driver ChromeDriver
     */
    public AdvancedSearchPage(ChromeDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.titleContains("Входящие"));
    }

    /**
     * Открывает диалог расширённого поиска.
     */
    public void openSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(BTN_ADV)).click();
    }

    /**
     * Вводит текст в первое поле.
     * @param query запрос для поиска
     */
    public void typeFirstQuery(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(INPUT1)).sendKeys(query);
    }

    /**
     * Очищает первое поле через кнопку "Очистить".
     */
    public void clearFirst() {
        wait.until(ExpectedConditions.elementToBeClickable(BTN_CLEAR)).click();
    }

    /**
     * Вводит текст во второе поле.
     * @param query запрос во второе поле
     */
    public void typeSecondQuery(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(INPUT2)).sendKeys(query);
    }

    /**
     * Выполняет поиск по введённым параметрам и ждёт появления результатов.
     */
    public void submitSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(BTN_SEARCH)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                BaseComponent.byCss("table.F.cf.zt")
        ));
    }
}
