// src/main/java/org/example/base/TasksPage.java
package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ButtonElement;
import org.example.element.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Page Object для вкладки «Задачи» в Gmail.
 */
public class TasksPage extends BasePage {
    private static final By IFRAME_TASKS     = BaseComponent.byCss("iframe[title='Задачи']");
    private static final By ADD_TASK_BTN     = BaseComponent.byCss("button[jsname='MhySTb']");
    private static final By TASK_TITLE_INPUT = BaseComponent.byCss("textarea[jsname='YPqjbf']");

    /**
     * Конструктор: переключается в iframe «Задачи».
     * @param driver WebDriver
     */
    public TasksPage(WebDriver driver) {
        super(driver);
        wait.until(d -> {
            var frame = d.findElement(IFRAME_TASKS);
            d.switchTo().frame(frame);
            return true;
        });
    }

    /** Кликает «Добавить задачу». */
    public void clickAddTaskButton() {
        ButtonElement.of(driver, ADD_TASK_BTN).click();
    }

    /** Вводит заголовок задачи. */
    public void enterTaskTitle(String title) {
        InputElement.of(driver, TASK_TITLE_INPUT).setValue(title);
    }

    /** Отправляет задачу (Enter). */
    public void submitTask() {
        var el = driver.findElement(TASK_TITLE_INPUT);
        el.sendKeys(Keys.ENTER);
    }

    /** Возвращается из iframe. */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
