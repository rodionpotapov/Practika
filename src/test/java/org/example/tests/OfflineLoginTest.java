package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.example.base.OfflinePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест авторизации в Gmail и проверки поведения при отключённом интернете.
 */
public class OfflineLoginTest extends BaseTest {
    private static final String USER_EMAIL    = "логин@gmail.com";
    private static final String USER_PASSWORD = "пароль";
    private static final String INBOX_URL     = "https://mail.google.com/mail/u/0/#inbox";

    @Test
    @DisplayName("Login + offline/online simulation")
    public void shouldShowOfflineErrorWhenNetworkDisconnected() {
        // Включаем CDP и логинимся
        OfflinePage offlinePage = new OfflinePage(driver);
        offlinePage.enableNetwork();
        InboxPage inbox = auth(USER_EMAIL, USER_PASSWORD);

        // Эмулируем offline и обновляем страницу
        offlinePage.emulateOffline();
        offlinePage.refresh();

        // Проверяем сообщение об отсутствии интернета
        String errorText = offlinePage.getOfflineErrorText().toLowerCase();
        assertTrue(
                errorText.contains("offline") || errorText.contains("internet"),
                "Ожидалось сообщение об offline, но получили: " + errorText
        );

        // Восстанавливаем сеть и возвращаемся во «Входящие»
        offlinePage.emulateOnline();
        driver.navigate().to(INBOX_URL);
        wait.until(ExpectedConditions.titleContains("Входящие"));
    }
}
