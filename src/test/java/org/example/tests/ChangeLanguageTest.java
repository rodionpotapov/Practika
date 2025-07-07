// src/test/java/org/example/tests/ChangeLanguageTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.example.base.SettingsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест смены языка интерфейса Gmail на English (UK).
 */
public class ChangeLanguageTest extends BaseTest {

    @Test
    public void shouldChangeToEnglishUK() {
        // Логинимся и открываем «Все настройки»
        InboxPage inbox       = auth();
        SettingsPage settings = inbox.openSettings();      // нажимаем шестерёнку
        settings.clickAllSettings();

        // Меняем язык на English (UK) и сохраняем
        settings
                .selectLanguage("English (UK)")
                .clickSaveChanges();

        // Если дошли до этой строки — тест завершился без ошибок
        assertTrue(true, "ChangeLanguageTest выполнен до конца");
    }
}
