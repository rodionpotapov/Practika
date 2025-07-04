package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * Page Object для «Входящие» Gmail.
 */
public class InboxPage extends BasePage {
    private static final By COMPOSE_BTN      = BaseComponent.byCss("div[gh='cm']");
    private static final By UNREAD_ROWS      = BaseComponent.byCss("tr.zA.zE");
    private static final By ROW_CHECKBOX     = BaseComponent.byCss("div.oZ-jc");
    private static final By MARK_AS_READ_BTN = BaseComponent.byCss("li.bqX.brr[data-tooltip='Отметить как прочитанное']");
    private static final By DELETE_BTN       = BaseComponent.byCss("li.bqX.bru[data-tooltip='Удалить']");
    private static final By HEADER_CHECKBOX  = BaseComponent.byCss("div[role='checkbox'][aria-label='Выбрать']");
    private static final By SETTINGS_ICON    = BaseComponent.byCss("a[aria-label='Настройки']");
    private static final By CONTACTS_TAB     = BaseComponent.byCss("#gsc-gab-9");
    private static final By TASKS_TAB        = BaseComponent.byCss("div[role='tab'][aria-label='Задачи']");
    private static final By PROFILE_AVATAR   = BaseComponent.byCss("a[aria-label^='Аккаунт Google']");

    /**
     * Конструктор: ждёт появления титула «Входящие».
     *
     * @param driver экземпляр ChromeDriver
     */
    public InboxPage(ChromeDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.titleContains("Входящие"));
    }

    /** Кликает кнопку «Написать». */
    public void clickCompose() {
        wait.until(ExpectedConditions.elementToBeClickable(COMPOSE_BTN)).click();
    }

    /** Возвращает количество непрочитанных писем. */
    public int getUnreadCount() {
        List<?> rows = driver.findElements(UNREAD_ROWS);
        return rows.size();
    }

    /** Отмечает первое непрочитанное письмо. */
    public void selectFirstUnread() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(UNREAD_ROWS, 0));
        driver.findElements(UNREAD_ROWS)
                .get(0)
                .findElement(ROW_CHECKBOX)
                .click();
    }

    /** Помечает выбранные письма как прочитанные. */
    public void clickMarkAsRead() {
        wait.until(ExpectedConditions.elementToBeClickable(MARK_AS_READ_BTN)).click();
    }

    /** Удаляет выбранные письма. */
    public void clickDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(DELETE_BTN)).click();
    }

    /**
     * Ждёт, пока число непрочитанных станет меньше, чем было.
     *
     * @param previousCount предыдущий счётчик непрочитанных
     */
    public void waitForUnreadCountLessThan(int previousCount) {
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(UNREAD_ROWS, previousCount));
    }

    /** Снимает выделение через общий чекбокс в шапке. */
    public void clearSelection() {
        wait.until(ExpectedConditions.elementToBeClickable(HEADER_CHECKBOX)).click();
    }

    /**
     * Открывает панель быстрых настроек.
     *
     * @return SettingsPage
     */
    public SettingsPage clickSettingsIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(SETTINGS_ICON)).click();
        return new SettingsPage(driver);
    }

    /**
     * Открывает раздел «Контакты».
     *
     * @return ContactsPage
     */
    public ContactsPage openContactsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(CONTACTS_TAB)).click();
        return new ContactsPage(driver);
    }

    /**
     * Открывает вкладку «Задачи».
     *
     * @return TasksPage
     */
    public TasksPage openTasksPage() {
        wait.until(ExpectedConditions.elementToBeClickable(TASKS_TAB)).click();
        return new TasksPage(driver);
    }

    /**
     * Открывает меню профиля (avatar) и возвращает ProfileMenuPage.
     *
     * @return ProfileMenuPage
     */
    public ProfileMenuPage openProfileMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(PROFILE_AVATAR)).click();
        return new ProfileMenuPage(driver);
    }
}
