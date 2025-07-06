// src/main/java/org/example/base/ComposePage.java
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

    private final InputElement toInput;
    private final InputElement subjInput;
    private final InputElement bodyInput;
    private final ButtonElement sendBtn;

    public ComposePage(WebDriver driver) {
        super(driver);
        wait.until(d -> d.findElement(TO_FIELD).isDisplayed());
        this.toInput   = new InputElement(wait, TO_FIELD);
        this.subjInput = new InputElement(wait, SUBJECT_FIELD);
        this.bodyInput = new InputElement(wait, BODY_FIELD);
        this.sendBtn   = new ButtonElement(driver, wait, SEND_BUTTON);
    }

    public void enterRecipient(String to) {
        toInput.setValue(to);
    }

    public void enterSubject(String subject) {
        subjInput.setValue(subject);
    }

    public void enterBody(String body) {
        bodyInput.setValue(body);
    }

    public void clickSend() {
        sendBtn.click();
    }
}
