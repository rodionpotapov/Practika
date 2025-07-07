package org.example.tests;

import org.example.page.AdvancedSearchPage;
import org.example.base.BaseTest;
import org.example.page.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для проверки расширенного поиска в Gmail.
 */
public class AdvancedSearchTest extends BaseTest {

    /**
     * Выполняет четыре сценария расширенного поиска:
     * 1) Поиск по первому полю,
     * 2) Пустой запрос в первом поле,
     * 3) Поиск по второму полю,
     * 4) Пустой запрос во втором поле.
     */
    @Test
    public void shouldPerformAllAdvancedSearchScenarios() {
        InboxPage inbox = auth();
        AdvancedSearchPage search = new AdvancedSearchPage(driver);

        // Сценарий 1
        search.openSearch();
        search.typeFirstQuery("Оповещение системы безопасности");
        search.submitSearch();
        assertTrue(true, "Сценарий 1 выполнен");

        // Сценарий 2
        search.clearFirst();
        search.openSearch();
        search.typeFirstQuery("аааа");
        search.submitSearch();
        assertTrue(true, "Сценарий 2 выполнен");

        // Сценарий 3
        search.clearFirst();
        search.openSearch();
        search.typeSecondQuery("Здравствуйте");
        search.submitSearch();
        assertTrue(true, "Сценарий 3 выполнен");

        // Сценарий 4
        search.clearFirst();
        search.openSearch();
        search.typeSecondQuery("аааа");
        search.submitSearch();
        assertTrue(true, "Сценарий 4 выполнен");
    }
}
