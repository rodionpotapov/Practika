// src/main/java/org/example/base/InboxPage.java
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
 * Обеспечивает методы для:
 *  - написания письма
 *  - работы с письмами (выбор, пометка как прочитанное, удаление)
 *  - навигации в настройки, Контакты, Задачи, Профиль
 */
public class InboxPage extends BasePage {
    private static final By COMPOSE_BTN     = BaseComponent.byCss("div[gh='cm']");
    private static final By UNREAD_ROWS     = BaseComponent.byCss("tr.zA.zE");
    private static final By ROW_CHECKBOX    = BaseComponent.byCss("div.oZ-jc");
    private static final By MARK_READ_BTN   = BaseComponent.byCss("li.bqX.brr[data-tooltip^='Отметить как прочитанное']");
    private static final By DELETE_BTN      = BaseComponent.byCss("li.bqX.bru[data-tooltip^='Удалить']");
    private static final By HEADER_CB       = BaseComponent.byCss("div[role='checkbox'][aria-label='Выбрать']");
    private static final By SETTINGS_BTN    = BaseComponent.byCss("a[aria-label='Настройки']");
    private static final By CONTACTS_TAB    = BaseComponent.byCss("div[role='tab'][aria-label='Контакты']");
    private static final By TASKS_TAB       = BaseComponent.byCss("div[role='tab'][aria-label='Задачи']");
    private static final By PROFILE_AVATAR  = BaseComponent.byCss("a[aria-label^='Аккаунт Google']");

    private final ButtonElement composeBtn;
    private final ButtonElement markReadBtn;
    private final ButtonElement deleteBtn;
    private final ButtonElement headerCheckbox;
    private final ClickElement settingsBtn;
    private final ClickElement contactsTab;
    private final ClickElement tasksTab;
    private final ButtonElement profileAvatar;

    /**
     * Инициализирует элементы страницы «Входящие».
     *
     * @param driver WebDriver, управляющий браузером
     */
    public InboxPage(WebDriver driver) {
        super(driver);
        wait.until(d -> d.getTitle().contains("Входящие"));

        this.composeBtn     = new ButtonElement(driver, wait, COMPOSE_BTN);
        this.markReadBtn    = new ButtonElement(driver, wait, MARK_READ_BTN);
        this.deleteBtn      = new ButtonElement(driver, wait, DELETE_BTN);
        this.headerCheckbox = new ButtonElement(driver, wait, HEADER_CB);

        WebElement settingsElem = wait.until(d -> d.findElement(SETTINGS_BTN));
        this.settingsBtn      = new ClickElement(wait, settingsElem);

        WebElement contactsElem = wait.until(d -> d.findElement(CONTACTS_TAB));
        this.contactsTab      = new ClickElement(wait, contactsElem);

        WebElement tasksElem    = wait.until(d -> d.findElement(TASKS_TAB));
        this.tasksTab         = new ClickElement(wait, tasksElem);

        this.profileAvatar     = new ButtonElement(driver, wait, PROFILE_AVATAR);
    }

    /** Нажимает кнопку «Написать письмо». */
    public void clickCompose() {
        composeBtn.click();
    }

    /** Возвращает текущее количество непрочитанных писем. */
    public int getUnreadCount() {
        List<?> rows = driver.findElements(UNREAD_ROWS);
        return rows.size();
    }

    /** Выбирает первый непрочитанный чекбоксом. */
    public void selectFirstUnread() {
        wait.until(d -> d.findElements(UNREAD_ROWS).size() > 0);
        WebElement first = driver.findElements(UNREAD_ROWS).get(0);
        first.findElement(ROW_CHECKBOX).click();
    }

    /** Помечает выделенное письмо как прочитанное. */
    public void clickMarkAsRead() {
        markReadBtn.click();
    }

    /** Удаляет выделенное письмо. */
    public void clickDelete() {
        deleteBtn.click();
    }

    /** Снимает выделение со всех писем. */
    public void clearSelection() {
        headerCheckbox.click();
    }

    /**
     * Помечает первое непрочитанное письмо как прочитанное и ждёт обновления счётчика.
     */
    public void markFirstUnreadAsRead() {
        int before = getUnreadCount();
        selectFirstUnread();
        clickMarkAsRead();
        wait.until(d -> d.findElements(UNREAD_ROWS).size() < before);
    }

    /**
     * Удаляет первое непрочитанное письмо и ждёт обновления счётчика.
     */
    public void deleteFirstUnread() {
        int before = getUnreadCount();
        selectFirstUnread();
        clickDelete();
        wait.until(d -> d.findElements(UNREAD_ROWS).size() < before);
    }

    /** Открывает панель настроек Gmail. */
    public SettingsPage openSettings() {
        settingsBtn.click();
        return new SettingsPage(driver);
    }

    /** Открывает раздел «Контакты». */
    public ContactsPage openContactsPage() {
        contactsTab.click();
        return new ContactsPage(driver);
    }

    /** Открывает вкладку «Задачи». */
    public TasksPage openTasksPage() {
        tasksTab.click();
        return new TasksPage(driver);
    }

    /** Открывает меню профиля пользователя. */
    public ProfileMenuPage openProfileMenu() {
        profileAvatar.click();
        return new ProfileMenuPage(driver);
    }
}
