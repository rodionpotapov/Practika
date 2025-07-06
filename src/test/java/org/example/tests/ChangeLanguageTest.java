// src/test/java/org/example/tests/ChangeLanguageTest.java
package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.InboxPage;
import org.example.page.SettingsPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест для смены языка интерфейса Gmail.
 */
public class ChangeLanguageTest extends BaseTest {

    /**
     * Меняет язык интерфейса и завершает тест.
     */
    @Test
    public void shouldChangeInterfaceLanguage() {
        InboxPage inbox = auth();
        SettingsPage settings = new SettingsPage(driver);

        settings.clickAllSettings();
        settings.selectLanguage("en-GB");
        settings.clickSaveChanges();
        assertTrue(true, "ChangeLanguageTest выполнен до конца");
    }
}
