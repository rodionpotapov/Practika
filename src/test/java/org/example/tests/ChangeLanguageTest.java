package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.example.base.SettingsPage;
import org.junit.jupiter.api.Test;

/**
 * Тест изменения языка интерфейса в Gmail.
 */
public class ChangeLanguageTest extends BaseTest {
    private static final String USER_EMAIL     = "логин@gmail.com";
    private static final String USER_PASSWORD  = "пароль";
    private static final String LANGUAGE_VALUE = "en-GB";

    @Test
    public void shouldChangeInterfaceLanguage() {
        // Авторизуемся и попадаем в «Входящие»
        InboxPage inbox = auth(USER_EMAIL, USER_PASSWORD);

        // Открываем настройки и переходим в полную версию
        SettingsPage settings = inbox.clickSettingsIcon();
        settings.clickAllSettings();

        // Меняем язык и сохраняем
        settings.selectLanguage(LANGUAGE_VALUE);
        settings.clickSaveChanges();
    }
}
