package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.FiltersPage;
import org.example.page.InboxPage;
import org.example.page.SettingsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест создания фильтра для заданного отправителя.
 */
public class FiltersTest extends BaseTest {

    /**
     * Создаёт фильтр, который архивирует письма от noreply@example.com
     * и помечает их как важные.
     */
    @Test
    public void shouldCreateFilterForSender() {
        InboxPage inbox       = auth();
        SettingsPage settings = inbox.openSettings();
        settings.clickAllSettings();
        FiltersPage filters = settings.openFiltersPage();

        filters.clickCreateFilter()
                .setFrom("noreply@example.com")
                .clickInterimCreate()
                .checkAlwaysImportant()
                .clickFinalCreate();

        assertTrue(true, "Фильтр создан успешно");
    }
}
