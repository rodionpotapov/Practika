package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdvancedSearchTester {
    public static void main(String[] args) {
        GmailLogin.LoginResult result = GmailLogin.login(
                "kulachdv@gmail.com",
                "an0656ke"
        );
        ChromeDriver driver = result.driver;
        WebDriverWait wait = result.wait;

        try {
            testAdvancedSearch(driver, wait);
        } catch (TimeoutException e) {
            System.err.println("Не удалось выполнить расширенный поиск: " + e.getMessage());
        } finally {
        }
    }

    /**
     * Делает серию расширённых поисковых запросов в Gmail:
     * 1) «Оповещение системы безопасности»
     * 2) «аааа»
     * 3) «Здравствуйте»
     * 4) снова «аааа» во втором поле
     */
    public static void testAdvancedSearch(ChromeDriver driver, WebDriverWait wait) {
        // Убедиться, что мы в «Входящие»
        wait.until(ExpectedConditions.titleContains("Входящие"));

        // Helper: клик по кнопке расширенного поиска
        Runnable clickAdvanced = () -> {
            WebElement btn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("button[aria-label='Расширенный поиск']")
                    )
            );
            btn.click();
        };

        //клик по кнопке поиска
        Runnable clickSearch = () -> {
            WebElement btn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("div[aria-label='Поиск почты']")
                    )
            );
            btn.click();
        };

        try {
            // 1-й запрос
            clickAdvanced.run();
            WebElement input1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input.ZH.nr.aQd")
                    )
            );
            input1.sendKeys("Оповещение системы безопасности");
            clickSearch.run();
            Thread.sleep(10_000);

            // 2-й запрос
            clickAdvanced.run();
            input1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input.ZH.nr.aQd")
                    )
            );
            input1.clear();
            input1.sendKeys("аааа");
            clickSearch.run();
            Thread.sleep(10_000);

            // Очистить поле поиска
            WebElement clearBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("button[aria-label='Очистить поле поиска']")
                    )
            );
            clearBtn.click();

            // 3-й запрос (второе поле)
            clickAdvanced.run();
            WebElement input2 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input.ZH.nr.aQb")
                    )
            );
            input2.sendKeys("Здравствуйте");
            clickSearch.run();
            Thread.sleep(10_000);

            // 4-й запрос (очищаем и вводим в том же поле)
            clickAdvanced.run();
            input2 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input.ZH.nr.aQb")
                    )
            );
            input2.clear();
            input2.sendKeys("аааа");
            clickSearch.run();
            Thread.sleep(10_000);

            System.out.println("Все поисковые сценарии выполнены.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Ожидание было прервано.");
        }
    }
}
