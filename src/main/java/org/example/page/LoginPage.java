package org.example.page;

import org.example.element.ButtonElement;
import org.example.element.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для экрана логина в Gmail.
 */
public class LoginPage extends BasePage {
    private static final By EMAIL_FIELD    = By.id("identifierId");
    private static final By EMAIL_NEXT_BTN = By.id("identifierNext");
    private static final By PASSWORD_FIELD = By.name("Passwd");
    private static final By PASSWORD_NEXT  = By.id("passwordNext");

    /**
     * Открывает страницу логина.
     * @param driver WebDriver
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get("https://accounts.google.com/signin/v2/identifier?service=mail");
    }

    /** Вводит email. */
    public void enterEmail(String email) {
        InputElement.of(driver, EMAIL_FIELD).setValue(email);
    }

    /** Кликает «Далее» после email и отключает флаг автоматизации. */
    public void clickEmailNext() {
        ButtonElement.of(driver, EMAIL_NEXT_BTN).click();
        ((JavascriptExecutor) driver)
                .executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
    }

    /** Вводит пароль. */
    public void enterPassword(String password) {
        InputElement.of(driver, PASSWORD_FIELD).setValue(password);
    }

    /** Кликает «Далее» после пароля и ждёт «Входящие». */
    public void clickPasswordNext() {
        ButtonElement.of(driver, PASSWORD_NEXT).click();
        wait.until(ExpectedConditions.titleContains("Входящие"));
    }
}
