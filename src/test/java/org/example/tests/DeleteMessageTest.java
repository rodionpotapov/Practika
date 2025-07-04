package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.junit.jupiter.api.Test;

/**
 * Тест удаления первого непрочитанного письма.
 */
public class DeleteMessageTest extends BaseTest {
    private static final String USER_EMAIL    = "логин@gmail.com";
    private static final String USER_PASSWORD = "пароль";

    @Test
    public void shouldDeleteFirstUnreadMessage() {
        // Авторизация и переход в «Входящие»
        InboxPage inbox = auth(USER_EMAIL, USER_PASSWORD);

        // Получаем текущее число непрочитанных
        int before = inbox.getUnreadCount();
        if (before == 0) {
            System.out.println("Нет непрочитанных писем для удаления.");
            return;
        }

        // Отмечаем первое непрочитанное
        inbox.selectFirstUnread();

        // Удаляем письмо
        inbox.clickDelete();

        // Ждём, что число непрочитанных уменьшится
        inbox.waitForUnreadCountLessThan(before);
    }
}
