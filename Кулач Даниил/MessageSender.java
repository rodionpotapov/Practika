package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MessageSender {
    public static void main(String[] args) {
        GmailLogin.LoginResult result = GmailLogin.login(
                "логин@gmail.com",
                "пароль"
        );
        ChromeDriver driver = result.driver;
        WebDriverWait wait = result.wait;

        try {
            sendTestEmail(
                    driver, wait,
                    "nesyshestvyushiyadress@gmail.com",
                    "a",
                    "a"
            );
        } catch (TimeoutException e) {
            System.err.println("Не удалось отправить тестовое письмо: " + e.getMessage());
        } finally {
        }
    }

    /**
     * Открывает окно создания письма, заполняет поля и отправляет.
     */
    public static void sendTestEmail(
            ChromeDriver driver,
            WebDriverWait wait,
            String to,
            String subject,
            String body
    ) {
        //Клик по кнопке «Написать»
        WebElement composeButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("div[gh='cm']"))
        );
        composeButton.click();

        //Заполнить поле «Кому»
        WebElement toField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("input[aria-label^='Получатели']")
                )
        );
        toField.sendKeys(to);

        //Заполнить тему
        WebElement subjectField = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("subjectbox"))
        );
        subjectField.sendKeys(subject);

        //Заполнить сообщение письма
        WebElement bodyField = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("div[aria-label='Текст письма']")
                )
        );
        bodyField.sendKeys(body);

        //Нажать «Отправить»
        WebElement sendButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("div[role='button'][aria-label*='Отправить']")
                )
        );
        sendButton.click();

        System.out.println("Письмо отправлено!");
    }
}
