package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;
import java.util.Collections;
import java.util.logging.Level;

public class GmailLogin {
    public static class LoginResult {
        public final ChromeDriver driver;
        public final WebDriverWait wait;

        public LoginResult(ChromeDriver driver, WebDriverWait wait) {
            this.driver = driver;
            this.wait = wait;
        }
    }

    /**
     * Выполняет вход в Gmail и возвращает драйвер и WebDriverWait.
     */
    public static LoginResult login(String email, String password) {
        // Отключаем лишние CDP-предупреждения
        java.util.logging.Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);

        // Настраиваем опции ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches",
                Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Создаём драйвер и WebDriverWait
        ChromeDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            // Открываем страницу входа в Gmail
            driver.get("https://accounts.google.com/signin/v2/identifier?service=mail");

            WebElement emailField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("identifierId"))
            );
            emailField.sendKeys(email);
            wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("identifierNext"))
            ).click();

            // Скрываем флаг автоматизации
            ((JavascriptExecutor) driver).executeScript(
                    "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
            );

            WebElement passwordField = wait.until(
                    ExpectedConditions.elementToBeClickable(By.name("Passwd"))
            );
            passwordField.sendKeys(password);
            wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("passwordNext"))
            ).click();

            // Ждём полной загрузки страницы
            wait.until(d ->
                    ((JavascriptExecutor) d)
                            .executeScript("return document.readyState")
                            .equals("complete")
            );

        } catch (TimeoutException e) {
            System.err.println("Не удалось войти в почту: " + e.getMessage());
            driver.quit();
            throw e;
        }

        return new LoginResult(driver, wait);
    }
}
