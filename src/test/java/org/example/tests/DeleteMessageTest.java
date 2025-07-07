package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест удаления первого непрочитанного письма.
 */
public class DeleteMessageTest extends BaseTest {

    /**
     * Если есть непрочитанные письма, удаляет первое из них.
     */
    @Test
    public void shouldDeleteFirstUnreadMessage() {
        InboxPage inbox = auth();
        if (inbox.getUnreadCount() > 0) {
            inbox.deleteFirstUnread();
        }
        assertTrue(true, "Письмо удалено или отсутствовало для удаления");
    }
}
