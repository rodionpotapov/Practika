// src/main/java/org/example/base/FiltersPage.java
package org.example.page;

import org.example.element.ClickElement;
import org.example.element.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object для раздела «Фильтры и заблокированные адреса».
 */
public class FiltersPage extends BasePage {
    private static final By CREATE_FILTER_LINK  =
            By.xpath("//span[normalize-space(text())='Создать новый фильтр']");
    private static final By FROM_INPUT          =
            By.cssSelector("input.ZH.nr.aQa");
    private static final By INTERIM_CREATE_LINK =
            By.xpath("//div[@role='link' and normalize-space(text())='Создать фильтр']");
    private static final By ALWAYS_IMPORTANT_LABEL =
            By.xpath("//*[@id=\":yn\"]/div/div[8]/label");
    private static final By FINAL_CREATE_BTN    =
            By.xpath("//div[contains(@class,'T-I') and @role='button' and normalize-space(text())='Создать фильтр']");

    /** Конструктор. */
    public FiltersPage(WebDriver driver) {
        super(driver);
        wait.until(d -> d.findElement(CREATE_FILTER_LINK).isDisplayed());
    }

    /** Открывает диалог создания фильтра. */
    public FiltersPage clickCreateFilter() {
        ClickElement.of(driver, CREATE_FILTER_LINK).click();
        return this;
    }

    /** Устанавливает поле From. */
    public FiltersPage setFrom(String from) {
        InputElement.of(driver, FROM_INPUT).setValue(from);
        return this;
    }

    /** Нажимает промежуточную кнопку. */
    public FiltersPage clickInterimCreate() {
        ClickElement.of(driver, INTERIM_CREATE_LINK).click();
        return this;
    }

    /** Отмечает «Всегда важное». */
    public FiltersPage checkAlwaysImportant() {
        ClickElement.of(driver, ALWAYS_IMPORTANT_LABEL).click();
        return this;
    }

    /** Нажимает финальную кнопку создания. */
    public FiltersPage clickFinalCreate() {
        ClickElement.of(driver, FINAL_CREATE_BTN).click();
        return this;
    }
}
