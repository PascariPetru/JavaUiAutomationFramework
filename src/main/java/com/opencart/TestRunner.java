package com.opencart;

import com.opencart.Managers.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();

        driver.get("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java");

        String currentWindowName = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://999.md/ru/");
        Thread.sleep(5000);

        driver.close();

        driver.switchTo().window(currentWindowName);
        Thread.sleep(5000);

        driver.get("https://diez.md/");


        driver.quit();

        System.out.println("The Execution was finished");






    }
}
