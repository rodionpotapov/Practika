package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.AdvancedSearchPage;
import org.example.base.InboxPage;
import org.junit.jupiter.api.Test;

/**
 * Проверка всех кейсов расширённого поиска.
 */
public class AdvancedSearchTest extends BaseTest {
    private static final String USER_EMAIL    = "логин@gmail.com";
    private static final String USER_PASSWORD = "пароль";

    @Test
    public void shouldPerformAllAdvancedSearchScenarios() {
        // Авторизация и попадание в Inbox
        InboxPage inbox = auth(USER_EMAIL, USER_PASSWORD);

        // Создание Page Object
        AdvancedSearchPage search = new AdvancedSearchPage(driver);

        // 1-й сценарий
        search.openSearch();
        search.typeFirstQuery("Оповещение системы безопасности");
        search.submitSearch();
        search.pauseFiveSeconds();

        // 2-й сценарий
        search.clearFirst();
        search.openSearch();
        search.typeFirstQuery("аааа");
        search.submitSearch();
        search.pauseFiveSeconds();

        // 3-й сценарий
        search.clearFirst();
        search.openSearch();
        search.typeSecondQuery("Здравствуйте");
        search.submitSearch();
        search.pauseFiveSeconds();

        // 4-й сценарий
        search.clearFirst();
        search.openSearch();
        search.typeSecondQuery("аааа");
        search.submitSearch();
        search.pauseFiveSeconds();
    }
}
