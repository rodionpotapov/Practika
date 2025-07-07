package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ButtonElement;
import org.example.element.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для расширённого поиска в Gmail.
 */
public class AdvancedSearchPage extends BasePage {
    private static final By BTN_ADV    = BaseComponent.byCss("button[aria-label='Расширенный поиск']");
    private static final By INPUT1     = BaseComponent.byCss("input.ZH.nr.aQd");
    private static final By INPUT2     = BaseComponent.byCss("input.ZH.nr.aQb");
    private static final By BTN_SEARCH = BaseComponent.byCss("div[aria-label='Поиск почты']");
    private static final By BTN_CLEAR  = BaseComponent.byCss("button[aria-label='Очистить поле поиска']");

    /**
     * Конструктор.
     * @param driver WebDriver
     */
    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
        wait.until(d -> d.getTitle().contains("Входящие"));
    }

    /** Открывает расширённый поиск. */
    public void openSearch() {
        ButtonElement.of(driver, BTN_ADV).click();
    }

    /** Вводит запрос в первое поле. */
    public void typeFirstQuery(String query) {
        InputElement.of(driver, INPUT1).setValue(query);
    }

    /** Очищает первое поле. */
    public void clearFirst() {
        ButtonElement.of(driver, BTN_CLEAR).click();
    }

    /** Вводит запрос во второе поле. */
    public void typeSecondQuery(String query) {
        InputElement.of(driver, INPUT2).setValue(query);
    }

    /** Отправляет запрос и ждёт результатов. */
    public void submitSearch() {
        ButtonElement.of(driver, BTN_SEARCH).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                BaseComponent.byCss("table.F.cf.zt")
        ));
    }

    /** Пауза 5 секунд (для демонстрации). */
    public void pauseFiveSeconds() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
