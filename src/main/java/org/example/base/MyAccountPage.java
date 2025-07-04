package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;

/**
 * Page Object для страницы Google My Account → Личные данные.
 */
public class MyAccountPage extends BasePage {
    private static final By PERSONAL_INFO_URL  = By.cssSelector("a[href*='personal-info']");
    private static final By NAME_EDIT_BTN      = BaseComponent.byCss("a[href*='profile/name']");
    private static final By NAME_FIELD_EXPAND  = BaseComponent.byCss("div.Ivdcjd");
    private static final By FIRST_NAME_INPUT   = By.id("i6");
    private static final By LAST_NAME_INPUT    = By.id("i11");
    private static final By SAVE_BTN           = BaseComponent.byXpath("//button[.//span[text()='Сохранить']]");

    /**
     * Конструктор: ждёт, пока URL содержит «myaccount».
     *
     * @param driver ChromeDriver
     */
    public MyAccountPage(ChromeDriver driver) {
        super(driver);
        wait.until(d -> d.getCurrentUrl().contains("myaccount"));
    }

    /**
     * Переходит в раздел «Личные данные».
     *
     * @return self
     */
    public MyAccountPage openPersonalInfo() {
        driver.get("https://myaccount.google.com/personal-info?hl=ru");
        wait.until(d -> d.getCurrentUrl().contains("personal-info"));
        return this;
    }

    /**
     * Открывает форму редактирования имени (нажимает ссылку и стрелку-раскрытие).
     *
     * @return self
     */
    public MyAccountPage openNameEdit() {
        // кликаем по ссылке «Имя»
        wait.until(ExpectedConditions.elementToBeClickable(NAME_EDIT_BTN)).click();
        // ждём появления стрелки раскрытия поля
        WebElement expand = wait.until(ExpectedConditions.elementToBeClickable(NAME_FIELD_EXPAND));
        // кликаем по стрелке
        new Actions(driver).moveToElement(expand).click().perform();
        // теперь поле для ввода готово
        wait.until(ExpectedConditions.elementToBeClickable(FIRST_NAME_INPUT));
        return this;
    }

    /**
     * Устанавливает имя и фамилию и сохраняет.
     *
     * @param firstName новое имя
     * @param lastName  новая фамилия
     * @return self
     */
    public MyAccountPage updateName(String firstName, String lastName) {
        WebElement f = wait.until(ExpectedConditions.elementToBeClickable(FIRST_NAME_INPUT));
        f.clear(); f.sendKeys(firstName);
        WebElement l = wait.until(ExpectedConditions.elementToBeClickable(LAST_NAME_INPUT));
        l.clear(); l.sendKeys(lastName);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(SAVE_BTN));
        new Actions(driver).moveToElement(btn).click().perform();
        return this;
    }

    /**
     * Возвращает управление в исходное окно Gmail.
     *
     * @param originalHandle хэндл исходного окна
     */
    public void returnToOriginal(String originalHandle) {
        driver.switchTo().window(originalHandle);
    }
}
