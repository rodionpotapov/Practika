// src/main/java/org/example/base/ContactsPage.java
package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object для вкладки «Контакты» в Gmail.
 */
public class ContactsPage extends BasePage {
    private static final By IFRAME_CONTACTS = BaseComponent.byCss("iframe[src*='contacts.google.com/widget/companion']");
    private static final By NEW_CONTACT_BTN = BaseComponent.byXpath("//button[.//span[text()='Новый контакт']]");
    private static final By NAME_INPUT      = BaseComponent.byCss("input[aria-label='Имя']");
    private static final By EMAIL_INPUT     = BaseComponent.byCss("input[aria-label='Эл. почта']");
    private static final By SAVE_BTN        = BaseComponent.byCss("button[jsname='JbVpqb'][aria-label='Сохранить']");

    private final Button newContactBtn;
    private final Input nameInput;
    private final Input emailInput;
    private final Button saveBtn;

    public ContactsPage(WebDriver driver) {
        super(driver);
        // Переключаемся в iframe контактов
        wait.until(d -> {
            WebElement frame = d.findElement(IFRAME_CONTACTS);
            d.switchTo().frame(frame);
            return true;
        });

        this.newContactBtn = new Button(driver, wait, NEW_CONTACT_BTN);
        this.nameInput     = new Input(wait, NAME_INPUT);
        this.emailInput    = new Input(wait, EMAIL_INPUT);
        this.saveBtn       = new Button(driver, wait, SAVE_BTN);
    }

    /** Нажать кнопку «Новый контакт». */
    public void clickNewContact() {
        newContactBtn.click();
    }

    /** Ввести имя в поле «Имя». */
    public void enterName(String fullName) {
        nameInput.setValue(fullName);
    }

    /** Ввести email в поле «Эл. почта». */
    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    /** Нажать кнопку «Сохранить». */
    public void saveContact() {
        saveBtn.click();
    }

    /** Подождать сохранения и вернуться из iframe. */
    public void waitForSaveAndReturn() {
        wait.until(d -> !d.findElement(SAVE_BTN).isDisplayed());
        driver.switchTo().defaultContent();
    }
}
