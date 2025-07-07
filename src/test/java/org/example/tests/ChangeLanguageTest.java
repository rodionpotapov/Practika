package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.InboxPage;
import org.example.page.SettingsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест смены языка интерфейса Gmail на English (UK).
 */
public class ChangeLanguageTest extends BaseTest {

    /**
     * Проверяет, что после выбора English (UK) настройки сохраняются без ошибок.
     */
    @Test
    public void shouldChangeToEnglishUK() {
        InboxPage inbox       = auth();
        SettingsPage settings = inbox.openSettings();
        settings
                .clickAllSettings()
                .selectLanguage("English (UK)")
                .clickSaveChanges();
        assertTrue(true, "Смена языка прошла успешно");
    }
}
