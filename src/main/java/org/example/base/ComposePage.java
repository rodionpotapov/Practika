package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для диалога «Написать письмо».
 */
public class ComposePage extends BasePage {
    private final By toField        = By.cssSelector("input[aria-label^='Получатели']");
    private final By subjectField   = By.name("subjectbox");
    private final By bodyField      = By.cssSelector("div[aria-label='Текст письма']");
    private final By sendButton     = By.cssSelector("div[role='button'][aria-label*='Отправить']");

    /**
     * Ждёт появления диалога.
     * @param driver ChromeDriver.
     */
    public ComposePage(ChromeDriver driver) {
        super(driver);
        wait.until(d ->
                d.findElement(toField).isDisplayed()
        );
    }

    /** Вводит адресата. */
    public void enterRecipient(String to) {
        wait.until(ExpectedConditions.elementToBeClickable(toField))
                .sendKeys(to);
    }

    /** Вводит тему письма. */
    public void enterSubject(String subject) {
        wait.until(ExpectedConditions.elementToBeClickable(subjectField))
                .sendKeys(subject);
    }

    /** Вводит тело письма. */
    public void enterBody(String body) {
        wait.until(ExpectedConditions.elementToBeClickable(bodyField))
                .sendKeys(body);
    }

    /** Кликает «Отправить». */
    public void clickSend() {
        wait.until(ExpectedConditions.elementToBeClickable(sendButton))
                .click();
    }
}
