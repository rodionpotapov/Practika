// src/main/java/org/example/base/ComposePage.java
package org.example.base;

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

    private final Input toInput;
    private final Input subjInput;
    private final Input bodyInput;
    private final Button sendBtn;

    public ComposePage(WebDriver driver) {
        super(driver);
        wait.until(d -> d.findElement(TO_FIELD).isDisplayed());
        this.toInput   = new Input(wait, TO_FIELD);
        this.subjInput = new Input(wait, SUBJECT_FIELD);
        this.bodyInput = new Input(wait, BODY_FIELD);
        this.sendBtn   = new Button(driver, wait, SEND_BUTTON);
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
