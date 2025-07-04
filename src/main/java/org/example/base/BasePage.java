package org.example.base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Базовый класс для всех Page Object’ов.
 */
public abstract class BasePage {
    protected ChromeDriver driver;
    protected WebDriverWait wait;

    private static final long PAUSE_FIVE_SECONDS_MS = 5_000;

    /**
     * Инициализирует driver и wait.
     * @param driver ChromeDriver
     */
    public BasePage(ChromeDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
    }

    /**
     * Выполняет паузу в браузере (через executeAsyncScript) заданное число миллисекунд.
     * @param millis пауза в мс
     */
    public void pauseMillis(long millis) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeAsyncScript(
                        "var callback = arguments[arguments.length - 1]; setTimeout(callback, " + millis + ");"
                );
    }

    /** Пауза ровно 5 секунд. */
    public void pauseFiveSeconds() {
        pauseMillis(PAUSE_FIVE_SECONDS_MS);
    }

    /**
     * Ожидание полной загрузки страницы.
     */
    protected void waitForPageLoad() {
        wait.until(d ->
                ((org.openqa.selenium.JavascriptExecutor) d)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );
    }
}
