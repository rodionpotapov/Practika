// src/main/java/org/example/base/FiltersPage.java
package org.example.page;

import org.example.element.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object для раздела «Фильтры и заблокированные адреса» в настройках Gmail.
 */
public class FiltersPage extends BasePage {
    private static final By NEW_FILTER_BTN    = BaseComponent.byXpath("//span[text()='Создать новый фильтр']");
    private static final By FROM_FIELD        = BaseComponent.byCss("input.ZH.nr.aQa");
    private static final By CREATE_LINK       = BaseComponent.byXpath("//div[@role='link' and text()='Создать фильтр']");
    private static final By NEVER_SPAM_OPTION = BaseComponent.byXpath("//label[contains(text(),'Никогда не отправлять в спам')]");
    private static final By FINAL_CREATE_BTN  = BaseComponent.byXpath("//div[@role='button' and text()='Создать фильтр']");

    private final ButtonElement newFilterBtn;
    private final InputElement fromInput;
    private final LinkElement createLink;
    private final ClickElement neverSpamOpt;
    private final ButtonElement finalCreateBtn;

    public FiltersPage(WebDriver driver) {
        super(driver);
        // Ждём появления кнопки «Создать новый фильтр»
        wait.until(d -> d.findElement(NEW_FILTER_BTN).isDisplayed());

        this.newFilterBtn   = new ButtonElement(driver, wait, NEW_FILTER_BTN);
        this.fromInput      = new InputElement(wait, FROM_FIELD);
        this.createLink     = new LinkElement(wait, CREATE_LINK);

        WebElement neverSpamElement = wait.until(d -> d.findElement(NEVER_SPAM_OPTION));
        this.neverSpamOpt   = new ClickElement(wait, neverSpamElement);

        this.finalCreateBtn = new ButtonElement(driver, wait, FINAL_CREATE_BTN);
    }

    /** Нажать кнопку «Создать новый фильтр». */
    public void clickCreateNewFilter() {
        newFilterBtn.click();
    }

    /** Ввести адрес отправителя для фильтра. */
    public void enterFromAddress(String fromAddress) {
        fromInput.setValue(fromAddress);
    }

    /** Нажать ссылку «Создать фильтр». */
    public void clickCreateFilterLink() {
        createLink.click();
    }

    /** Выбрать опцию «Никогда не отправлять в спам». */
    public void clickNeverSpamOption() {
        neverSpamOpt.click();
    }

    /** Нажать финальную кнопку «Создать фильтр». */
    public void clickFinalCreateFilter() {
        finalCreateBtn.click();
    }
}
