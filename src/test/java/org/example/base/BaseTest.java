// src/test/java/org/example/base/BaseTest.java
package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.page.InboxPage;
import org.example.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

/**
 * Базовый класс для всех UI-тестов.
 * Содержит логику запуска драйвера и общие данные (email/password).
 */
public abstract class BaseTest {
    /** Логин пользователя для Gmail. */
    protected static final String USER_EMAIL    = "kulachdv@gmail.com";
    /** Пароль пользователя для Gmail. */
    protected static final String USER_PASSWORD = "an0656ke";

    protected ChromeDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars", "--disable-notifications");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
        }
    }

    /**
     * Выполняет авторизацию в Gmail и возвращает объект InboxPage.
     */
    protected InboxPage auth() {
        LoginPage login = new LoginPage(driver);
        login.enterEmail(USER_EMAIL);
        login.clickEmailNext();
        login.enterPassword(USER_PASSWORD);
        login.clickPasswordNext();
        return new InboxPage(driver);
    }
}
