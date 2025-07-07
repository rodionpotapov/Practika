package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.InboxPage;
import org.example.page.OfflinePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест оффлайн-режима Gmail через CDP.
 */
public class OfflineLoginTest extends BaseTest {

    /**
     * Эмулирует offline, проверяет сообщение об ошибке и восстанавливает сеть.
     */
    @Test
    public void shouldShowOfflineError() {
        InboxPage inbox = auth();
        OfflinePage offline = new OfflinePage(driver);

        offline.enableNetwork();
        offline.emulateOffline();
        offline.refresh();

        String error = offline.getOfflineErrorText();
        assertTrue(error.contains("Нет подключения"),
                "Сообщение об отсутствии сети отображено");

        offline.emulateOnline();
    }
}
