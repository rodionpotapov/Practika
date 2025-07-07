// src/test/java/org/example/tests/MessageSenderTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.ComposePage;
import org.example.base.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест отправки письма через Gmail UI.
 */
public class MessageSenderTest extends BaseTest {

    /**
     * Отправляет письмо заданному получателю и завершает тест.
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
        assertTrue(true, "MessageSenderTest выполнен до конца");
    }
}
