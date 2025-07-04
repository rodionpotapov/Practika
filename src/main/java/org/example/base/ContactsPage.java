package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для вкладки «Контакты» в Gmail.
 */
public class ContactsPage extends BasePage {
    private static final By CONTACTS_IFRAME  = BaseComponent.byCss("iframe[src*='contacts.google.com/widget/companion']");
    private static final By NEW_CONTACT_BTN  = BaseComponent.byXpath("//button[.//span[text()='Новый контакт']]");
    private static final By NAME_INPUT       = BaseComponent.byCss("input[aria-label='Имя']");
    private static final By EMAIL_INPUT      = BaseComponent.byCss("input[aria-label='Эл. почта']");
    private static final By SAVE_BUTTON      = BaseComponent.byCss("button[jsname='JbVpqb'][aria-label='Сохранить']");

    /**
     * Конструктор: ждёт iframe с контактами и переключается в него.
     *
     * @param driver ChromeDriver
     */
    public ContactsPage(ChromeDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(CONTACTS_IFRAME));
    }

    /** Кликает кнопку «Новый контакт». */
    public void clickNewContact() {
        wait.until(ExpectedConditions.elementToBeClickable(NEW_CONTACT_BTN)).click();
    }

    /**
     * Вводит полное имя нового контакта.
     *
     * @param fullName имя и фамилия
     */
    public void enterName(String fullName) {
        wait.until(ExpectedConditions.elementToBeClickable(NAME_INPUT)).sendKeys(fullName);
    }

    /**
     * Вводит email нового контакта.
     *
     * @param email адрес электронной почты
     */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(EMAIL_INPUT)).sendKeys(email);
    }

    /** Сохраняет контакт (кликает «Сохранить»). */
    public void saveContact() {
        wait.until(ExpectedConditions.elementToBeClickable(SAVE_BUTTON)).click();
    }

    /**
     * Ждёт, пока форма сохранения закроется, и возвращается в основной контекст страницы.
     */
    public void waitForSaveAndReturn() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SAVE_BUTTON));
        driver.switchTo().defaultContent();
    }
}
