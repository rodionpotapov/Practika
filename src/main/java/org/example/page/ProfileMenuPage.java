// src/main/java/org/example/base/ProfileMenuPage.java
package org.example.page;

import org.example.element.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для меню аккаунта (появляется после клика по аватару).
 * Позволяет перейти в Google Account → «Личные данные».
 */
public class ProfileMenuPage extends BasePage {
    /** iframe с меню аккаунта после клика по аватару */
    private static final By ACCOUNT_IFRAME  = BaseComponent.byCss("iframe[name='account']");
    /** Ссылка «Управление аккаунтом Google» внутри iframe */
    private static final By MY_ACCOUNT_LINK = BaseComponent.byCss("a[aria-label^='Управление аккаунтом Google']");

    /**
     * Ждёт наличия iframe меню аккаунта и переключается в него.
     */
    public ProfileMenuPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ACCOUNT_IFRAME));
        wait.until(ExpectedConditions.elementToBeClickable(MY_ACCOUNT_LINK));
    }

    /**
     * Кликает «Управление аккаунтом Google», переключается в новую вкладку,
     * сразу переходит на страницу «Личные данные» и возвращает MyAccountPage.
     *
     * @param originalHandle дескриптор окна до клика
     */
    public MyAccountPage goToMyAccount(String originalHandle) {
        WebElement link = driver.findElement(MY_ACCOUNT_LINK);
        link.click();

        // вернуться в основной контент, чтобы управлять окнами
        driver.switchTo().defaultContent();

        // переключиться на новую вкладку
        for (String h : driver.getWindowHandles()) {
            if (!h.equals(originalHandle)) {
                driver.switchTo().window(h);
                break;
            }
        }

        // сразу перейти на личные данные
        driver.get("https://myaccount.google.com/personal-info?gar=WzEyMF0&hl=ru&utm_source=OGB&utm_medium=act");
        return new MyAccountPage(driver);
    }
}
