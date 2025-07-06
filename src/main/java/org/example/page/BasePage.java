// src/main/java/org/example/base/BasePage.java
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

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
