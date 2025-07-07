package org.example.page;

import org.example.element.BaseComponent;
import org.example.element.ButtonElement;
import org.example.element.ClickElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object для страницы «Входящие» Gmail.
 */
public class InboxPage extends BasePage {
    private static final By COMPOSE_BTN     = BaseComponent.byCss("div[gh='cm']");
    private static final By UNREAD_ROWS     = BaseComponent.byCss("tr.zA.zE");
    private static final By ROW_CHECKBOX    = BaseComponent.byCss("div.oZ-jc");
    private static final By MARK_READ_BTN   = BaseComponent.byCss("li.bqX.brr[data-tooltip^='Отметить как прочитанное']");
    private static final By DELETE_BTN      = BaseComponent.byCss("li.bqX.bru[data-tooltip^='Удалить']");
    private static final By HEADER_CB       = BaseComponent.byCss("div[role='checkbox'][aria-label='Выбрать']");
    private static final By SETTINGS_BTN    = BaseComponent.byCss("div.FI > a[aria-label='Настройки']");
    private static final By CONTACTS_TAB    = BaseComponent.byCss("div[role='tab'][aria-label='Контакты']");
    private static final By TASKS_TAB       = BaseComponent.byCss("div[role='tab'][aria-label='Задачи']");
    private static final By PROFILE_AVATAR  = BaseComponent.byCss("a[aria-label^='Аккаунт Google']");

    /**
     * Конструктор.
     * @param driver WebDriver
     */
    public InboxPage(WebDriver driver) {
        super(driver);
        wait.until(d -> d.getTitle().contains("Входящие"));
    }

    /** Открывает окно «Написать письмо». */
    public void clickCompose() {
        ButtonElement.of(driver, COMPOSE_BTN).click();
    }

    /** Возвращает число непрочитанных писем. */
    public int getUnreadCount() {
        List<?> rows = driver.findElements(UNREAD_ROWS);
        return rows.size();
    }

    /** Выбирает первое непрочитанное письмо. */
    public void selectFirstUnread() {
        wait.until(d -> d.findElements(UNREAD_ROWS).size() > 0);
        WebElement first = driver.findElements(UNREAD_ROWS).get(0);
        first.findElement(ROW_CHECKBOX).click();
    }

    /** Помечает выделенные письма как прочитанные. */
    public void clickMarkAsRead() {
        ButtonElement.of(driver, MARK_READ_BTN).click();
    }

    /** Удаляет выделенные письма. */
    public void clickDelete() {
        ButtonElement.of(driver, DELETE_BTN).click();
    }

    /** Снимает выделение со всех писем. */
    public void clearSelection() {
        ButtonElement.of(driver, HEADER_CB).click();
    }

    /** Помечает первое непрочитанное письмо и ждёт обновления. */
    public void markFirstUnreadAsRead() {
        int before = getUnreadCount();
        selectFirstUnread();
        clickMarkAsRead();
        wait.until(d -> d.findElements(UNREAD_ROWS).size() < before);
    }

    /** Удаляет первое непрочитанное письмо и ждёт обновления. */
    public void deleteFirstUnread() {
        int before = getUnreadCount();
        selectFirstUnread();
        clickDelete();
        wait.until(d -> d.findElements(UNREAD_ROWS).size() < before);
    }

    /** Открывает панель «Настройки». */
    public SettingsPage openSettings() {
        ClickElement.of(driver, SETTINGS_BTN).click();
        return new SettingsPage(driver);
    }

    /** Переходит в «Контакты». */
    public ContactsPage openContactsPage() {
        ClickElement.of(driver, CONTACTS_TAB).click();
        return new ContactsPage(driver);
    }

    /** Переходит в «Задачи». */
    public TasksPage openTasksPage() {
        ClickElement.of(driver, TASKS_TAB).click();
        return new TasksPage(driver);
    }

    /** Открывает меню профиля. */
    public ProfileMenuPage openProfileMenu() {
        ButtonElement.of(driver, PROFILE_AVATAR).click();
        return new ProfileMenuPage(driver);
    }
}
