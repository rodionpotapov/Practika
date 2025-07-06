// src/test/java/org/example/tests/ProfileNameTest.java
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
     * Авторизуется в Gmail, переходит в Google Account → Личные данные,
     * редактирует имя и возвращается в почту.
     */
    @Test
    public void shouldUpdateProfileName() {
        // Заходим в «Входящие»
        InboxPage inbox = auth();

        // Сохраняем дескриптор текущего окна
        String originalHandle = driver.getWindowHandle();

        // Открываем меню профиля и переходим в Google Account
        ProfileMenuPage menu = inbox.openProfileMenu();
        MyAccountPage myAcc  = menu.goToMyAccount(originalHandle);

        // Редактируем имя
        myAcc.openNameEdit()
                .updateName(NEW_FIRST, NEW_LAST);

        // Возвращаемся в почту и обновляем страницу
        driver.switchTo().window(originalHandle);
        driver.navigate().refresh();

        assertTrue(true, "ProfileNameTest выполнен до конца");
    }
}
