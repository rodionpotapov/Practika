package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object для вкладки «Задачи» в Gmail.
 */
public class TasksPage extends BasePage {
    private static final By TASKS_IFRAME     = BaseComponent.byCss("iframe[title='Задачи']");
    private static final By ADD_TASK_BUTTON  = BaseComponent.byCss("button[jsname='MhySTb']");
    private static final By TASK_TITLE_INPUT = BaseComponent.byCss("textarea[jsname='YPqjbf']");

    /**
     * Конструктор: ждёт iframe с задачами и переключается в него.
     *
     * @param driver ChromeDriver
     */
    public TasksPage(ChromeDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(TASKS_IFRAME));
    }

    /** Кликает кнопку «Добавить задачу». */
    public void clickAddTaskButton() {
        wait.until(ExpectedConditions.elementToBeClickable(ADD_TASK_BUTTON)).click();
    }

    /**
     * Вводит заголовок задачи.
     *
     * @param title текст задачи
     */
    public void enterTaskTitle(String title) {
        wait.until(ExpectedConditions.elementToBeClickable(TASK_TITLE_INPUT)).sendKeys(title);
    }

    /** Подтверждает добавление задачи нажатием Enter. */
    public void submitTask() {
        driver.findElement(TASK_TITLE_INPUT).sendKeys(Keys.ENTER);
    }

    /** Возвращает управление в основной контекст страницы. */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
