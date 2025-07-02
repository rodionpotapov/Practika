package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MessageTester {
    public static void main(String[] args) {
        GmailLogin.LoginResult result = GmailLogin.login(
                "логин@gmail.com",
                "пароль"
        );
        ChromeDriver driver = result.driver;
        WebDriverWait wait = result.wait;

        try {
            testMarkFirstUnreadAsRead(driver, wait);
        } catch (TimeoutException e) {
            System.err.println("Не удалось выполнить тест: " + e.getMessage());
        } finally {
        }
    }

    /**
     * Тест: найти первое непрочитанное письмо и пометить его как прочитанное.
     */
    public static void testMarkFirstUnreadAsRead(ChromeDriver driver, WebDriverWait wait) {
        // Ждём, пока загрузится таблица писем и появится хотя бы одно непрочитанное
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("table.F.cf.zt")
        ));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("tr.zA.zE"), 0
        ));

        // Берём первое непрочитанное
        List<WebElement> unread = driver.findElements(
                By.cssSelector("tr.zA.zE")
        );
        if (unread.isEmpty()) {
            System.out.println("Непрочитанных писем не найдено.");
            return;
        }
        WebElement firstRow = unread.get(0);

        //Отмечаем чекбокс
        WebElement checkbox = firstRow.findElement(By.cssSelector("div.oZ-jc"));
        wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
        System.out.println("Письмо выделено.");

        //Нажимаем кнопку "Отметить как прочитанное"
        By markAsReadBtn = By.cssSelector(
                "li.bqX.brr[data-tooltip='Отметить как прочитанное']"
        );
        WebElement markBtn = wait.until(
                ExpectedConditions.elementToBeClickable(markAsReadBtn)
        );
        markBtn.click();
        System.out.println("Письмо помечено как прочитанное.");

        //Снимаем выделение
        WebElement checkboxAgain = firstRow.findElement(By.cssSelector("div.oZ-jc"));
        wait.until(ExpectedConditions.elementToBeClickable(checkboxAgain)).click();
        System.out.println("Выделение снято.");
    }
}
