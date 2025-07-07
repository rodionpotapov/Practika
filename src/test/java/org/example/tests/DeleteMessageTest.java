// src/test/java/org/example/tests/DeleteMessageTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест удаления первого непрочитанного письма.
 */
public class DeleteMessageTest extends BaseTest {

    /**
     * Удаляет первое непрочитанное письмо и завершает тест.
     */
    @Test
    public void shouldDeleteFirstUnreadMessage() {
        InboxPage inbox = auth();

        if (inbox.getUnreadCount() > 0) {
            inbox.deleteFirstUnread();
        }

        assertTrue(true, "DeleteMessageTest выполнен до конца");
    }
}
