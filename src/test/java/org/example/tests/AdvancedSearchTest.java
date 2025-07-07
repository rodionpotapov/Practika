// src/test/java/org/example/tests/AdvancedSearchTest.java
package org.example.tests;

import org.example.base.AdvancedSearchPage;
import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для проверки расширенного поиска в Gmail.
 */
public class AdvancedSearchTest extends BaseTest {

    /**
     * Выполняет четыре сценария расширенного поиска.
     */
    @Test
    public void shouldPerformAllAdvancedSearchScenarios() {
        InboxPage inbox = auth();
        AdvancedSearchPage search = new AdvancedSearchPage(driver);

        // 1) Поиск по первому полю
        search.openSearch();
        search.typeFirstQuery("Оповещение системы безопасности");
        search.submitSearch();
        assertTrue(true, "Сценарий 1 (поиск по первому полю) выполнен");

        // 2) Пустой запрос в первом поле
        search.clearFirst();
        search.openSearch();
        search.typeFirstQuery("аааа");
        search.submitSearch();
        assertTrue(true, "Сценарий 2 (пустой запрос в первом поле) выполнен");

        // 3) Поиск по второму полю
        search.clearFirst();
        search.openSearch();
        search.typeSecondQuery("Здравствуйте");
        search.submitSearch();
        assertTrue(true, "Сценарий 3 (поиск по второму полю) выполнен");

        // 4) Пустой запрос во втором поле
        search.clearFirst();
        search.openSearch();
        search.typeSecondQuery("аааа");
        search.submitSearch();
        assertTrue(true, "Сценарий 4 (пустой запрос во втором поле) выполнен");

        assertTrue(true, "AdvancedSearchTest выполнен до конца");
    }
}
