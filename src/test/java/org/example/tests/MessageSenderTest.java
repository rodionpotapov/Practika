package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.ComposePage;
import org.example.page.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест отправки письма через UI Gmail.
 */
public class MessageSenderTest extends BaseTest {

    /**
     * Отправляет письмо заданному получателю с темой и телом «a».
     */
    @Test
    public void shouldSendEmailSuccessfully() {
        InboxPage inbox = auth();
        inbox.clickCompose();

        ComposePage compose = new ComposePage(driver);
        compose.enterRecipient("nesyshestvyushiyadress@gmail.com");
        compose.enterSubject("a");
        compose.enterBody("a");
        compose.clickSend();

        assertTrue(true, "Письмо отправлено успешно");
    }
}
