package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.FiltersPage;
import org.example.base.InboxPage;
import org.example.base.SettingsPage;
import org.junit.jupiter.api.Test;

/**
 * Тест создания фильтра «Никогда не отправлять в спам» для заданного адреса.
 */
public class FiltersTest extends BaseTest {
    private static final String USER_EMAIL    = "логин@gmail.com";
    private static final String USER_PASSWORD = "пароль";
    private static final String FROM_ADDRESS  = "no-reply@etu.ru";

    @Test
    public void shouldCreateNeverSpamFilter() {
        // Авторизация и переход в «Входящие»
        InboxPage inbox = auth(USER_EMAIL, USER_PASSWORD);

        // Переходим в настройки и открываем вкладку фильтров
        SettingsPage settings = inbox.clickSettingsIcon();
        settings.clickAllSettings();
        FiltersPage filters = settings.openFiltersPage();

        // Создаём фильтр «Никогда не отправлять в спам»
        filters.clickCreateNewFilter();
        filters.enterFromAddress(FROM_ADDRESS);
        filters.clickCreateFilterLink();
        filters.clickNeverSpamOption();
        filters.clickFinalCreateFilter();
    }
}
