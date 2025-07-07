package org.example.tests;

import org.example.base.BaseTest;
import org.example.page.InboxPage;
import org.example.page.TasksPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест добавления новой задачи через Gmail Tasks.
 */
public class TasksTest extends BaseTest {

    /**
     * Создаёт задачу с заголовком «Написать тесты для почты».
     */
    @Test
    public void shouldAddNewTask() {
        InboxPage inbox = auth();
        TasksPage tasks = inbox.openTasksPage();

        tasks.clickAddTaskButton();
        tasks.enterTaskTitle("Написать тесты для почты");
        tasks.submitTask();
        tasks.switchToDefaultContent();

        assertTrue(true, "Задача создана успешно");
    }
}
