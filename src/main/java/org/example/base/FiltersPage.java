package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для раздела «Фильтры и заблокированные адреса» в настройках Gmail.
 */
public class FiltersPage extends BasePage {
    private static final By CREATE_NEW_FILTER_BTN   =
            BaseComponent.byXpath("//span[text()='Создать новый фильтр']");
    private static final By FROM_FIELD              =
            BaseComponent.byCss("input.ZH.nr.aQa");
    private static final By CREATE_FILTER_LINK      =
            BaseComponent.byXpath("//div[@role='link' and text()='Создать фильтр']");
    private static final By NEVER_SPAM_LABEL        =
            BaseComponent.byXpath("//label[contains(text(),'Никогда не отправлять в спам')]");
    private static final By FINAL_CREATE_FILTER_BTN =
            BaseComponent.byXpath("//div[@role='button' and text()='Создать фильтр']");

    /**
     * Конструктор: ждёт загрузки страницы фильтров (title содержит «Настройки»).
     * @param driver ChromeDriver
     */
    public FiltersPage(ChromeDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.titleContains("Настройки"));
    }

    /** Нажимает «Создать новый фильтр». */
    public void clickCreateNewFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_FILTER_BTN)).click();
    }

    /**
     * Вводит адрес отправителя в поле «От».
     * @param fromAddress email отправителя
     */
    public void enterFromAddress(String fromAddress) {
        wait.until(ExpectedConditions.elementToBeClickable(FROM_FIELD)).sendKeys(fromAddress);
    }

    /** Нажимает ссылку «Создать фильтр». */
    public void clickCreateFilterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(CREATE_FILTER_LINK)).click();
    }

    /** Отмечает опцию «Никогда не отправлять в спам». */
    public void clickNeverSpamOption() {
        wait.until(ExpectedConditions.elementToBeClickable(NEVER_SPAM_LABEL)).click();
    }

    /** Нажимает финальную кнопку «Создать фильтр». */
    public void clickFinalCreateFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(FINAL_CREATE_FILTER_BTN)).click();
    }
}
