package org.example.tests;

import org.example.base.BaseTest;
import org.example.base.InboxPage;
import org.example.base.TasksPage;
import org.junit.jupiter.api.Test;

/**
 * Тест добавления новой задачи в Gmail Tasks.
 */
public class TasksTest extends BaseTest {
    private static final String USER_EMAIL    = "логин@gmail.com";
    private static final String USER_PASSWORD = "пароль";
    private static final String TASK_TITLE    = "Написать тесты для почты";

    /**
     * Осуществляет вход, открывает вкладку «Задачи», добавляет новую задачу.
     */
    @Test
    public void shouldAddNewTask() {
        InboxPage inbox = auth(USER_EMAIL, USER_PASSWORD);
        TasksPage tasks = inbox.openTasksPage();
        tasks.clickAddTaskButton();
        tasks.enterTaskTitle(TASK_TITLE);
        tasks.submitTask();
        tasks.switchToDefaultContent();
    }
}
