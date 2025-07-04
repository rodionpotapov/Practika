package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.ContactsPage;
import org.example.base.InboxPage;
import org.junit.jupiter.api.Test;

/**
 * Тест создания нового контакта в Google Contacts.
 */
public class ContactsTest extends BaseTest {
    private static final String USER_EMAIL    = "логин@gmail.com";
    private static final String USER_PASSWORD = "пароль";
    private static final String CONTACT_NAME  = "Родион";
    private static final String CONTACT_EMAIL = "rodionpotapov@gmail.com";

    @Test
    public void shouldAddNewContact() {
        // Авторизация и переход в «Входящие»
        InboxPage inbox = auth(USER_EMAIL, USER_PASSWORD);

        // Открываем раздел «Контакты» и создаём новый контакт
        ContactsPage contacts = inbox.openContactsPage();
        contacts.clickNewContact();
        contacts.enterName(CONTACT_NAME);
        contacts.enterEmail(CONTACT_EMAIL);
        contacts.saveContact();
        contacts.waitForSaveAndReturn();
    }
}
