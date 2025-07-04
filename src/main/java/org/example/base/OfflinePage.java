package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

/**
 * Page Object для работы с offline/online через CDP в Gmail.
 */
public class OfflinePage extends BasePage {
    private static final By ERROR_MSG      = BaseComponent.byCss("#main-message");

    /**
     * Конструктор: просто наследует драйвер и wait.
     * @param driver ChromeDriver
     */
    public OfflinePage(ChromeDriver driver) {
        super(driver);
    }

    /** Включает CDP-сетевой модуль. */
    public void enableNetwork() {
        driver.executeCdpCommand("Network.enable", Map.of());
    }

    /** Эмулирует офлайн. */
    public void emulateOffline() {
        var params = new HashMap<String,Object>();
        params.put("offline", true);
        params.put("latency", 0);
        params.put("downloadThroughput", 0);
        params.put("uploadThroughput", 0);
        params.put("connectionType", "none");
        driver.executeCdpCommand("Network.emulateNetworkConditions", params);
    }

    /** Эмулирует онлайн. */
    public void emulateOnline() {
        var params = new HashMap<String,Object>();
        params.put("offline", false);
        params.put("latency", 0);
        params.put("downloadThroughput", 1_000_000);
        params.put("uploadThroughput", 1_000_000);
        params.put("connectionType", "wifi");
        driver.executeCdpCommand("Network.emulateNetworkConditions", params);
    }

    /** Перезагружает текущую страницу. */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Возвращает текст сообщения об offline-ошибке.
     * @return текст элемента с id="main-message"
     */
    public String getOfflineErrorText() {
        WebElement msg = wait.until(
                ExpectedConditions.presenceOfElementLocated(ERROR_MSG)
        );
        return msg.getText();
    }
}
