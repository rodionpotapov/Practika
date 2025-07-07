// src/main/java/org/example/base/ProfileMenuPage.java
package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для меню аккаунта (после клика по аватару).
 */
public class ProfileMenuPage extends BasePage {
    private static final By ACCOUNT_IFRAME  = BaseComponent.byCss("iframe[name='account']");
    private static final By MY_ACCOUNT_LINK = BaseComponent.byCss("a[aria-label^='Управление аккаунтом Google']");

    /**
     * Конструктор: переключается в iframe меню.
     * @param driver WebDriver
     */
    public ProfileMenuPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ACCOUNT_IFRAME));
        wait.until(ExpectedConditions.elementToBeClickable(MY_ACCOUNT_LINK));
    }

    /**
     * Переходит в Google Account → «Личные данные».
     * @param originalHandle дескриптор основного окна
     * @return MyAccountPage
     */
    public MyAccountPage goToMyAccount(String originalHandle) {
        ButtonElement.of(driver, MY_ACCOUNT_LINK).click();
        driver.switchTo().defaultContent();
        for (String h : driver.getWindowHandles()) {
            if (!h.equals(originalHandle)) {
                driver.switchTo().window(h);
                break;
            }
        }
        driver.get("https://myaccount.google.com/personal-info?gar=WzEyMF0&hl=ru");
        return new MyAccountPage(driver);
    }
}
