package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для меню профиля в Gmail.
 */
public class ProfileMenuPage extends BasePage {
    private static final By IFRAME_ACCOUNT   = BaseComponent.byCss("iframe[name='account']");
    private static final By MY_ACCOUNT_LINK  = BaseComponent.byCss("a.coHE2");

    /**
     * Конструктор: переключается в iframe аккаунта и ждёт появления ссылки «Мой аккаунт».
     * @param driver ChromeDriver
     */
    public ProfileMenuPage(ChromeDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IFRAME_ACCOUNT));
        wait.until(ExpectedConditions.elementToBeClickable(MY_ACCOUNT_LINK));
    }

    /**
     * Кликает «Мой аккаунт», переключается обратно в основной контекст и
     * возвращает MyAccountPage (новое окно).
     * @return MyAccountPage
     */
    public MyAccountPage goToMyAccount() {
        driver.findElement(MY_ACCOUNT_LINK).click();
        driver.switchTo().defaultContent();
        // ждём новое окно и переключаем
        String original = driver.getWindowHandle();
        wait.until(d -> d.getWindowHandles().size() > 1);
        for (String h : driver.getWindowHandles()) {
            if (!h.equals(original)) {
                driver.switchTo().window(h);
                break;
            }
        }
        return new MyAccountPage(driver);
    }
}
