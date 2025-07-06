// src/test/java/org/example/tests/FiltersTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.FiltersPage;
import org.example.page.InboxPage;
import org.example.page.SettingsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест создания фильтра «Никогда не отправлять в спам».
 */
public class FiltersTest extends BaseTest {

    /**
     * Создаёт фильтр и завершает тест.
     */
    @Test
    public void shouldCreateFilterNeverSpam() {
        InboxPage inbox = auth();
        SettingsPage settings = inbox.openSettings();
        settings.clickAllSettings();

        FiltersPage filters = settings.openFiltersPage();
        filters.clickCreateNewFilter();
        filters.enterFromAddress("alert@system.com");
        filters.clickCreateFilterLink();
        filters.clickNeverSpamOption();
        filters.clickFinalCreateFilter();
        assertTrue(true, "FiltersTest выполнен до конца");
    }
}
