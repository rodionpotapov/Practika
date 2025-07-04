package org.example.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.logging.Level;

/**
 * Базовый класс для всех тестов.
 * Настраивает и завершает работу WebDriver, предоставляет метод auth().
 */
public abstract class BaseTest {
    protected ChromeDriver driver;
    protected WebDriverWait wait;

    private static final String BASE_URL =
            "https://accounts.google.com/signin/v2/identifier?service=mail";

    /**
     * Настройка драйвера и открытие стартовой страницы перед каждым тестом.
     */
    @BeforeEach
    public void setUp() {
        java.util.logging.Logger
                .getLogger("org.openqa.selenium.devtools")
                .setLevel(Level.SEVERE);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(
                "excludeSwitches",
                Collections.singletonList("enable-automation")
        );
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get(BASE_URL);
    }

    /**
     * Завершение работы драйвера после каждого теста.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
        }
    }

    /**
     * Выполняет логин в Gmail и возвращает объект InboxPage.
     *
     * @param email    логин пользователя
     * @param password пароль пользователя
     * @return InboxPage после успешного входа
     */
    protected InboxPage auth(String email, String password) {
        LoginPage login = new LoginPage(driver);
        login.enterEmail(email);
        login.clickEmailNext();
        login.enterPassword(password);
        login.clickPasswordNext();
        return new InboxPage(driver);
    }
}
