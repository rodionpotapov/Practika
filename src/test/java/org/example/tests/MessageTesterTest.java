package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.example.base.LoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Тест пометки первого непрочитанного письма как прочитанного.
 */
public class MessageTesterTest extends BaseTest {
    private final String EMAIL    = "логин@gmail.com";
    private final String PASSWORD = "пароль";

    @Test
    public void shouldMarkFirstUnreadAsRead() {
        LoginPage login = new LoginPage(driver);
        login.enterEmail(EMAIL);
        login.clickEmailNext();
        login.enterPassword(PASSWORD);
        login.clickPasswordNext();

        InboxPage inbox = new InboxPage(driver);

        List<WebElement> unread = driver.findElements(By.cssSelector("tr.zA.zE"));
        if (unread.isEmpty()) {
            return;
        }
        WebElement first = unread.get(0);

        // Отметить как прочитанное
        first.findElement(By.cssSelector("div.oZ-jc")).click();
        driver.findElement(By.cssSelector("li.bqX.brr[data-tooltip='Отметить как прочитанное']"))
                .click();
        // Снять выделение
        first.findElement(By.cssSelector("div.oZ-jc")).click();
    }
}
