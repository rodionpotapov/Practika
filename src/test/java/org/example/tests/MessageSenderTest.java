package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.ComposePage;
import org.example.base.InboxPage;
import org.example.base.LoginPage;
import org.junit.jupiter.api.Test;

/**
 * Тест отправки письма.
 */
public class MessageSenderTest extends BaseTest {
    private final String EMAIL     = "логин@gmail.com";
    private final String PASSWORD  = "пароль";
    private final String TO        = "nesyshestvyushiyadress@gmail.com";
    private final String SUBJECT   = "a";
    private final String BODY      = "a";

    @Test
    public void shouldSendEmailSuccessfully() {
        LoginPage login = new LoginPage(driver);
        login.enterEmail(EMAIL);
        login.clickEmailNext();
        login.enterPassword(PASSWORD);
        login.clickPasswordNext();

        InboxPage inbox = new InboxPage(driver);
        inbox.clickCompose();

        ComposePage compose = new ComposePage(driver);
        compose.enterRecipient(TO);
        compose.enterSubject(SUBJECT);
        compose.enterBody(BODY);
        compose.clickSend();
    }
}
