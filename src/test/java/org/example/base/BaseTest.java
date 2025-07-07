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
 * Содержит логику запуска и закрытия браузера, а также метод авторизации.
 */
public abstract class BaseTest {
    /** Логин пользователя для Gmail. */
    protected static final String USER_EMAIL    = "логин@gmail.com";
    /** Пароль пользователя для Gmail. */
    protected static final String USER_PASSWORD = "пароль";

    protected ChromeDriver driver;

    /**
     * Настраивает и запускает ChromeDriver перед каждым тестом.
     */
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

    /**
     * Закрывает браузер после каждого теста.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Авторизует в Gmail под предопределёнными учетными данными
     * и возвращает готовый объект страницы «Входящие».
     *
     * @return InboxPage — объект страницы входящих писем
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
