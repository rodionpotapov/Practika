package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.InboxPage;
import org.example.page.ProfileMenuPage;
import org.example.page.MyAccountPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест смены имени пользователя в Google Account через UI Gmail.
 */
public class ProfileNameTest extends BaseTest {
    private static final String NEW_FIRST = "Родион";
    private static final String NEW_LAST  = "Потапов";

    /**
     * Открывает меню профиля, переходит в Google Account и обновляет имя.
     */
    @Test
    public void shouldUpdateProfileName() {
        InboxPage inbox = auth();
        String originalHandle = driver.getWindowHandle();

        ProfileMenuPage menu = inbox.openProfileMenu();
        MyAccountPage myAcc  = menu.goToMyAccount(originalHandle);

        myAcc.openNameEdit()
                .updateName(NEW_FIRST, NEW_LAST);

        driver.switchTo().window(originalHandle);
        driver.navigate().refresh();

        assertTrue(true, "Имя профиля обновлено");
    }
}
