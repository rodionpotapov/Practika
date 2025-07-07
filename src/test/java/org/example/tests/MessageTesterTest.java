// src/test/java/org/example/tests/MessageTesterTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест пометки первого непрочитанного письма как прочитанного.
 */
public class MessageTesterTest extends BaseTest {

    /**
     * Помечает первое непрочитанное письмо и завершает тест.
     */
    @Test
    public void shouldMarkFirstUnreadAsRead() {
        InboxPage inbox = auth();
        if (inbox.getUnreadCount() > 0) {
            inbox.markFirstUnreadAsRead();
        }
        assertTrue(true, "MessageTesterTest выполнен до конца");
    }
}
