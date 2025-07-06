// src/main/java/org/example/base/MyAccountPage.java
package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ClickElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для раздела «Личные данные» Google My Account.
 * Позволяет открыть редактор имени, перейти к форме ввода, изменить и сохранить его.
 */
public class MyAccountPage extends BasePage {
    /** Контейнер поля «Имя» (div[jscontroller='fjYfSd']) */
    private static final By NAME_FIELD_CONTAINER       = By.cssSelector("div[jscontroller='fjYfSd']");
    /** Кнопка «Редактировать имя» (div[jscontroller='FS1FEb']) */
    private static final By EDIT_NAME_CONTAINER        = By.cssSelector("div[jscontroller='FS1FEb']");
    /** Поля ввода имени и фамилии */
    private static final By FIRST_NAME_INPUT           = By.id("i6");
    private static final By LAST_NAME_INPUT            = By.id("i11");
    /** Кнопка «Сохранить» */
    private static final By SAVE_BUTTON                = BaseComponent.byXpath("//button[.//span[text()='Сохранить']]");

    private final WebDriver driver;

    /**
     * Конструктор.
     * Ждёт, что страница personal-info загружена и контейнер «Имя» виден.
     *
     * @param driver WebDriver
     */
    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        // Перейти на страницу личных данных заранее
        wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_FIELD_CONTAINER));
    }

    /**
     * Открывает сначала контейнер «Имя», затем кликает кнопку редактирования,
     * и дожидается загрузки полей ввода.
     *
     * @return this
     */
    public MyAccountPage openNameEdit() {
        // Шаг 1: клик по контейнеру с jscontroller='fjYfSd'
        WebElement nameContainer = wait.until(d -> d.findElement(NAME_FIELD_CONTAINER));
        new ClickElement(wait, nameContainer).click();

        // Шаг 2: дождаться появления кнопки редактирования и кликнуть её
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(EDIT_NAME_CONTAINER));
        new ClickElement(wait, editButton).click();

        // Шаг 3: дождаться отображения полей ввода
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_INPUT));
        return this;
    }

    /**
     * Вводит новое имя и фамилию и сохраняет изменения.
     *
     * @param first новое имя
     * @param last  новая фамилия
     * @return this
     */
    public MyAccountPage updateName(String first, String last) {
        WebElement firstInput = driver.findElement(FIRST_NAME_INPUT);
        WebElement lastInput  = driver.findElement(LAST_NAME_INPUT);

        firstInput.clear();
        firstInput.sendKeys(first);
        lastInput.clear();
        lastInput.sendKeys(last);

        driver.findElement(SAVE_BUTTON).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SAVE_BUTTON));
        return this;
    }
}
