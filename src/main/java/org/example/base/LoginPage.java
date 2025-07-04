package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для экрана логина в Gmail.
 */
public class LoginPage extends BasePage {
    private final By emailField       = By.id("identifierId");
    private final By emailNextButton  = By.id("identifierNext");
    private final By passwordField    = By.name("Passwd");
    private final By passwordNext     = By.id("passwordNext");

    /**
     * Открывает страницу логина.
     * @param driver ChromeDriver.
     */
    public LoginPage(ChromeDriver driver) {
        super(driver);
        driver.get("https://accounts.google.com/signin/v2/identifier?service=mail");
    }

    /** Вводит email. */
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField))
                .sendKeys(email);
    }

    /** Кликает кнопку «Далее» после email и отключает флаг автоматизации. */
    public void clickEmailNext() {
        wait.until(ExpectedConditions.elementToBeClickable(emailNextButton)).click();
        ((JavascriptExecutor) driver)
                .executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
    }

    /** Вводит пароль. */
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField))
                .sendKeys(password);
    }

    /** Кликает кнопку «Далее» после пароля и ждёт загрузки страницы. */
    public void clickPasswordNext() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordNext)).click();
        waitForPageLoad();
    }
}
