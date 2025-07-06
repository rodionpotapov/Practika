// src/main/java/org/example/base/OfflinePage.java
package org.example.page;

import org.example.element.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;
import java.util.HashMap;

/**
 * Page Object для эмуляции offline/online режимов через CDP в Gmail.
 */
public class OfflinePage extends BasePage {
    private static final By ERROR_MSG = BaseComponent.byCss("#main-message");

    public OfflinePage(WebDriver driver) {
        super(driver);
    }

    /** Включает CDP-Network */
    public void enableNetwork() {
        ((ChromeDriver) driver).executeCdpCommand("Network.enable", Map.of());
    }

    /** Эмулирует offline */
    public void emulateOffline() {
        Map<String, Object> params = new HashMap<>();
        params.put("offline", true);
        params.put("latency", 0);
        params.put("downloadThroughput", 0);
        params.put("uploadThroughput", 0);
        params.put("connectionType", "none");
        ((ChromeDriver) driver).executeCdpCommand("Network.emulateNetworkConditions", params);
    }

    /** Эмулирует online */
    public void emulateOnline() {
        Map<String, Object> params = new HashMap<>();
        params.put("offline", false);
        params.put("latency", 0);
        params.put("downloadThroughput", 1_000_000);
        params.put("uploadThroughput", 1_000_000);
        params.put("connectionType", "wifi");
        ((ChromeDriver) driver).executeCdpCommand("Network.emulateNetworkConditions", params);
    }

    /** Обновляет страницу */
    public void refresh() {
        driver.navigate().refresh();
    }

    /** Возвращает текст ошибки offline */
    public String getOfflineErrorText() {
        WebElement msg = wait.until(d -> d.findElement(ERROR_MSG));
        return msg.getText();
    }
}
