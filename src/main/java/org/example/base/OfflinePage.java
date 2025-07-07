// src/main/java/org/example/base/OfflinePage.java
package org.example.base;

import org.example.base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

/**
 * Page Object для эмуляции offline/online через CDP.
 */
public class OfflinePage extends BasePage {
    /** Селектор для сообщения об отсутствии сети */
    private static final By ERROR_MSG = BaseComponent.byCss("#main-message");

    /**
     * @param driver WebDriver, фактически ChromeDriver
     */
    public OfflinePage(WebDriver driver) {
        super(driver);
    }

    /** Включает CDP-сеть (Network domain). */
    public void enableNetwork() {
        ((ChromeDriver) driver)
                .executeCdpCommand("Network.enable", Map.of());
    }

    /** Эмулирует offline (отключает сеть). */
    public void emulateOffline() {
        Map<String, Object> params = new HashMap<>();
        params.put("offline", true);
        params.put("latency", 0);
        params.put("downloadThroughput", 0);
        params.put("uploadThroughput", 0);
        params.put("connectionType", "none");
        ((ChromeDriver) driver)
                .executeCdpCommand("Network.emulateNetworkConditions", params);
    }

    /** Эмулирует online (включает сеть обратно). */
    public void emulateOnline() {
        Map<String, Object> params = new HashMap<>();
        params.put("offline", false);
        params.put("latency", 0);
        params.put("downloadThroughput", 1_000_000);
        params.put("uploadThroughput", 1_000_000);
        params.put("connectionType", "wifi");
        ((ChromeDriver) driver)
                .executeCdpCommand("Network.emulateNetworkConditions", params);
    }

    /** Перезагружает страницу. */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Возвращает текст offline-ошибки.
     * @return сообщение об отсутствии подключения
     */
    public String getOfflineErrorText() {
        WebElement el = wait.until(
                ExpectedConditions.presenceOfElementLocated(ERROR_MSG)
        );
        return el.getText();
    }
}
