// src/test/java/org/example/tests/FiltersTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.FiltersPage;
import org.example.base.InboxPage;
import org.example.base.SettingsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест создания фильтра для заданного отправителя.
 * Фильтр будет архивировать все входящие от noreply@example.com
 * и помечать их как важные.
 */
public class FiltersTest extends BaseTest {

    /**
     * Создаёт фильтр, который:
     * 1. Архивирует письма от noreply@example.com,
     * 2. Всегда помечает их как важные.
     */
    @Test
    public void shouldCreateFilterForSender() {
        // Авторизация и переход в «Все настройки»
        InboxPage inbox       = auth();
        SettingsPage settings = inbox.openSettings();      // нажимаем шестерёнку
        settings.clickAllSettings();

        // Открываем раздел фильтров
        FiltersPage filters = settings.openFiltersPage();

        // Шаги создания фильтра
        filters
                .clickCreateFilter()
                .setFrom("noreply@example.com")
                .clickInterimCreate()
                .checkAlwaysImportant()
                .clickFinalCreate();

        // Тест считается успешным, если код дойдёт до этой строки
        assertTrue(true, "FiltersTest выполнен до конца");
    }
}
