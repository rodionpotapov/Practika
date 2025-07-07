package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ButtonElement;
import org.example.element.InputElement;
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

    /**
     * Конструктор: переключается в iframe «Контакты».
     * @param driver WebDriver
     */
    public ContactsPage(WebDriver driver) {
        super(driver);
        wait.until(d -> {
            WebElement frame = d.findElement(IFRAME_CONTACTS);
            d.switchTo().frame(frame);
            return true;
        });
    }

    /** Нажимает кнопку «Новый контакт». */
    public void clickNewContact() {
        ButtonElement.of(driver, NEW_CONTACT_BTN).click();
    }

    /** Вводит имя. */
    public void enterName(String fullName) {
        InputElement.of(driver, NAME_INPUT).setValue(fullName);
    }

    /** Вводит email. */
    public void enterEmail(String email) {
        InputElement.of(driver, EMAIL_INPUT).setValue(email);
    }

    /** Сохраняет контакт. */
    public void saveContact() {
        ButtonElement.of(driver, SAVE_BTN).click();
    }

    /** Ждёт сохранения и выходит из iframe. */
    public void waitForSaveAndReturn() {
        wait.until(d -> !d.findElement(SAVE_BTN).isDisplayed());
        driver.switchTo().defaultContent();
    }
}
