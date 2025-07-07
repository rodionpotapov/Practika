package org.example.page;

import org.example.element.ButtonElement;
import org.example.element.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object для диалога «Написать письмо».
 */
public class ComposePage extends BasePage {
    private static final By TO_FIELD      = By.cssSelector("input[aria-label^='Получатели']");
    private static final By SUBJECT_FIELD = By.name("subjectbox");
    private static final By BODY_FIELD    = By.cssSelector("div[aria-label='Текст письма']");
    private static final By SEND_BUTTON   = By.cssSelector("div[role='button'][aria-label*='Отправить']");

    /**
     * Конструктор.
     * @param driver WebDriver
     */
    public ComposePage(WebDriver driver) {
        super(driver);
    }

    /** Вводит адрес получателя. */
    public void enterRecipient(String to) {
        InputElement.of(driver, TO_FIELD).setValue(to);
    }

    /** Вводит тему письма. */
    public void enterSubject(String subject) {
        InputElement.of(driver, SUBJECT_FIELD).setValue(subject);
    }

    /** Вводит тело письма. */
    public void enterBody(String body) {
        InputElement.of(driver, BODY_FIELD).setValue(body);
    }

    /** Кликает кнопку «Отправить». */
    public void clickSend() {
        ButtonElement.of(driver, SEND_BUTTON).click();
    }
}
