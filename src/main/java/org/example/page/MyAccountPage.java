// src/main/java/org/example/base/MyAccountPage.java
package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ButtonElement;
import org.example.element.ClickElement;
import org.example.element.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для раздела «Личные данные» Google Account.
 */
public class MyAccountPage extends BasePage {
    private static final By NAME_FIELD_CONTAINER = BaseComponent.byCss("div[jscontroller='fjYfSd']");
    private static final By EDIT_NAME_CONTAINER  = BaseComponent.byCss("div[jscontroller='FS1FEb']");
    private static final By FIRST_NAME_INPUT     = By.id("i6");
    private static final By LAST_NAME_INPUT      = By.id("i11");
    private static final By SAVE_BUTTON          = BaseComponent.byXpath("//button[.//span[text()='Сохранить']]");

    /**
     * Конструктор: ждёт загрузки контейнера «Имя».
     * @param driver WebDriver
     */
    public MyAccountPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_FIELD_CONTAINER));
    }

    /** Открывает редактор имени. */
    public MyAccountPage openNameEdit() {
        ClickElement.of(driver, NAME_FIELD_CONTAINER).click();
        ClickElement.of(driver, EDIT_NAME_CONTAINER).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FIRST_NAME_INPUT));
        return this;
    }

    /**
     * Обновляет имя и фамилию.
     * @param first новое имя
     * @param last новая фамилия
     */
    public MyAccountPage updateName(String first, String last) {
        InputElement.of(driver, FIRST_NAME_INPUT).setValue(first);
        InputElement.of(driver, LAST_NAME_INPUT).setValue(last);
        ButtonElement.of(driver, SAVE_BUTTON).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SAVE_BUTTON));
        return this;
    }
}
