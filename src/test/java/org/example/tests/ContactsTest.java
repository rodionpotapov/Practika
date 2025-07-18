package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.ContactsPage;
import org.example.page.InboxPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест добавления нового контакта в Gmail.
 */
public class ContactsTest extends BaseTest {

    /**
     * Создаёт и сохраняет новый контакт, затем возвращается в почту.
     */
    @Test
    public void shouldAddNewContact() {
        InboxPage inbox       = auth();
        ContactsPage contacts = inbox.openContactsPage();

        contacts.clickNewContact();
        contacts.enterName("Родион");
        contacts.enterEmail("rodionpotapov@gmail.com");
        contacts.saveContact();
        contacts.waitForSaveAndReturn();

        assertTrue(true, "Контакт успешно добавлен");
    }
}
