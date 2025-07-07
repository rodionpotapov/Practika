package org.example.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Общий базовый класс для всех Page Object’ов.
 */
public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    /**
     * Создаёт страницу с дефолтным ожиданием 10 сек.
     * @param driver WebDriver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
