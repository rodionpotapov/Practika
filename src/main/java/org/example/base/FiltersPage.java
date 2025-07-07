// src/main/java/org/example/base/FiltersPage.java
package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object для раздела «Фильтры и заблокированные адреса» в настройках Gmail.
 * Предоставляет методы для создания нового фильтра в несколько шагов.
 */
public class FiltersPage extends BasePage {
    /** Ссылка «Создать новый фильтр» */
    private static final By CREATE_FILTER_LINK  =
            By.xpath("//span[normalize-space(text())='Создать новый фильтр']");
    /** Поле отправителя (From) — по классам элемента */
    private static final By FROM_INPUT          =
            By.cssSelector("input.ZH.nr.aQa");
    /** Промежуточная кнопка «Создать фильтр» (первый шаг) */
    private static final By INTERIM_CREATE_LINK =
            By.xpath("//div[@role='link' and normalize-space(text())='Создать фильтр']");
    /**
     * Чекбокс «Всегда помечать как важное» — находим сам <label>,
     * клик по которому переключает соответствующий <input>
     */
    private static final By ALWAYS_IMPORTANT_LABEL =
            By.xpath("//*[@id=\":yn\"]/div/div[8]/label");
    /** Финальная кнопка «Создать фильтр» (второй шаг) */
    private static final By FINAL_CREATE_BTN    =
            By.xpath("//div[contains(@class,'T-I') and @role='button' and normalize-space(text())='Создать фильтр']");

    public FiltersPage(WebDriver driver) {
        super(driver);
        wait.until(d -> d.findElement(CREATE_FILTER_LINK).isDisplayed());
    }

    /** Открывает диалог создания нового фильтра. */
    public FiltersPage clickCreateFilter() {
        WebElement link = wait.until(d -> d.findElement(CREATE_FILTER_LINK));
        new ClickElement(wait, link).click();
        return this;
    }

    /** Вводит адрес отправителя в поле From. */
    public FiltersPage setFrom(String from) {
        WebElement input = wait.until(d -> d.findElement(FROM_INPUT));
        new ClickElement(wait, input).click();
        input.clear();
        input.sendKeys(from);
        return this;
    }

    /** Нажимает промежуточную кнопку «Создать фильтр». */
    public FiltersPage clickInterimCreate() {
        WebElement link = wait.until(d -> d.findElement(INTERIM_CREATE_LINK));
        new ClickElement(wait, link).click();
        return this;
    }

    /** Отмечает «Всегда помечать как важное» кликом по <label>. */
    public FiltersPage checkAlwaysImportant() {
        WebElement label = wait.until(d -> d.findElement(ALWAYS_IMPORTANT_LABEL));
        new ClickElement(wait, label).click();
        return this;
    }

    /** Нажимает финальную кнопку «Создать фильтр». */
    public FiltersPage clickFinalCreate() {
        WebElement btn = wait.until(d -> d.findElement(FINAL_CREATE_BTN));
        new ClickElement(wait, btn).click();
        return this;
    }
}
