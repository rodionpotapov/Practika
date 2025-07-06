// src/test/java/org/example/tests/OfflineLoginTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.OfflinePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест оффлайн-режима Gmail через CDP.
 */
public class OfflineLoginTest extends BaseTest {

    /**
     * Проверяет, что при эмуляции оффлайна показывается сообщение об ошибке.
     */
    @Test
    public void shouldShowOfflineError() {
        OfflinePage offline = new OfflinePage(driver);
        offline.enableNetwork();
        offline.emulateOffline();
        offline.refresh();
        String error = offline.getOfflineErrorText();
        assertTrue(error.contains("Нет подключения"), "OfflineLoginTest: сообщение об ошибке отображено");
        assertTrue(true, "OfflineLoginTest выполнен до конца");
    }
}
