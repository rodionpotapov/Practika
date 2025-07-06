// src/main/java/org/example/base/TasksPage.java
package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ButtonElement;
import org.example.element.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object для вкладки «Задачи» в Gmail.
 */
public class TasksPage extends BasePage {
    private static final By IFRAME_TASKS     = BaseComponent.byCss("iframe[title='Задачи']");
    private static final By ADD_TASK_BTN     = BaseComponent.byCss("button[jsname='MhySTb']");
    private static final By TASK_TITLE_INPUT = BaseComponent.byCss("textarea[jsname='YPqjbf']");

    private final ButtonElement addTaskBtn;
    private final InputElement titleInput;

    public TasksPage(WebDriver driver) {
        super(driver);
        // Переключаемся в iframe «Задачи»
        wait.until(d -> {
            WebElement frame = d.findElement(IFRAME_TASKS);
            d.switchTo().frame(frame);
            return true;
        });

        this.addTaskBtn = new ButtonElement(driver, wait, ADD_TASK_BTN);
        this.titleInput = new InputElement(wait, TASK_TITLE_INPUT);
    }

    /** Нажать кнопку «Добавить задачу». */
    public void clickAddTaskButton() {
        addTaskBtn.click();
    }

    /** Ввести заголовок задачи. */
    public void enterTaskTitle(String title) {
        titleInput.setValue(title);
    }

    /** Отправить задачу (Enter). */
    public void submitTask() {
        driver.findElement(TASK_TITLE_INPUT).sendKeys(Keys.ENTER);
    }

    /** Вернуться из iframe обратно к основному контенту. */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
