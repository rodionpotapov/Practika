package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.example.base.ProfileMenuPage;
import org.example.base.MyAccountPage;
import org.junit.jupiter.api.Test;

/**
 * Тест изменения имени и фамилии профиля Google.
 */
public class ProfileNameTest extends BaseTest {
    private static final String USER_EMAIL    = "логин@gmail.com";
    private static final String USER_PASSWORD = "пароль";
    private static final String NEW_FIRST     = "Родион";
    private static final String NEW_LAST      = "Потапов";

    @Test
    public void shouldUpdateProfileName() {
        // 1) Логинимся и попадаем в Inbox
        InboxPage inbox = auth(USER_EMAIL, USER_PASSWORD);

        // 2) Открываем меню профиля и переходим в MyAccount
        ProfileMenuPage menu = inbox.openProfileMenu();
        String originalHandle = driver.getWindowHandle();
        MyAccountPage myAcc = menu.goToMyAccount();

        // 3) Открываем личные данные, редактируем имя и возвращаемся
        myAcc.openPersonalInfo()
                .openNameEdit()
                .updateName(NEW_FIRST, NEW_LAST)
                .returnToOriginal(originalHandle);

        // 4) Обновляем Gmail-окно, чтобы убедиться, что всё на месте
        driver.navigate().refresh();
    }
}
