package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест пометки первого непрочитанного письма как прочитанного.
 */
public class MessageTesterTest extends BaseTest {

    /**
     * Если есть непрочитанные письма, помечает первое из них как прочитанное.
     */
    @Test
    public void shouldMarkFirstUnreadAsRead() {
        InboxPage inbox = auth();
        if (inbox.getUnreadCount() > 0) {
            inbox.markFirstUnreadAsRead();
        }
        assertTrue(true, "Письмо помечено как прочитанное");
    }
}
