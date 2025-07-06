// src/main/java/org/example/base/AdvancedSearchPage.java
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

    private final ButtonElement advBtn;
    private final InputElement firstInput;
    private final InputElement secondInput;
    private final ButtonElement searchBtn;
    private final ButtonElement clearBtn;

    public AdvancedSearchPage(WebDriver driver) {
        super(driver);
        // Дождаться, что мы на странице «Входящие»
        wait.until(d -> d.getTitle().contains("Входящие"));

        // Инициализация компонентов с новым сигнатурой Button
        this.advBtn      = new ButtonElement(driver, wait, BTN_ADV);
        this.firstInput  = new InputElement(wait, INPUT1);
        this.secondInput = new InputElement(wait, INPUT2);
        this.searchBtn   = new ButtonElement(driver, wait, BTN_SEARCH);
        this.clearBtn    = new ButtonElement(driver, wait, BTN_CLEAR);
    }

    /** Открывает расширённый поиск. */
    public void openSearch() {
        advBtn.click();
    }

    /** Вводит запрос в первое поле. */
    public void typeFirstQuery(String query) {
        firstInput.setValue(query);
    }

    /** Очищает первое поле. */
    public void clearFirst() {
        clearBtn.click();
    }

    /** Вводит запрос во второе поле. */
    public void typeSecondQuery(String query) {
        secondInput.setValue(query);
    }

    /** Отправляет запрос и ждёт результатов. */
    public void submitSearch() {
        searchBtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                BaseComponent.byCss("table.F.cf.zt")
        ));
    }

    /** Пауза 5 секунд (если нужна демонстрация задержки). */
    public void pauseFiveSeconds() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
