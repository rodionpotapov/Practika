// src/test/java/org/example/tests/OfflineLoginTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.example.base.OfflinePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест оффлайн-режима Gmail через CDP.
 */
public class OfflineLoginTest extends BaseTest {

    /**
     * Проверяет, что при эмуляции оффлайна показывается сообщение об ошибке,
     * а затем восстанавливает сеть.
     */
    @Test
    public void shouldShowOfflineError() {
        // Логинимся и попадаем на «Входящие»
        InboxPage inbox = auth();

        // Переходим к OfflinePage и переключаем сеть
        OfflinePage offline = new OfflinePage(driver);
        offline.enableNetwork();    // активируем CDP Network
        offline.emulateOffline();   // эмулируем offline
        offline.refresh();          // перезагружаем страницу

        // Проверяем текст ошибки об отсутствии подключения
        String error = offline.getOfflineErrorText();
        assertTrue(error.contains("Нет подключения"),
                "OfflineLoginTest: сообщение об ошибке отображено");

        // Восстанавливаем сеть для последующих тестов
        offline.emulateOnline();

        // Завершающий assert
        assertTrue(true, "OfflineLoginTest выполнен до конца");
    }
}
