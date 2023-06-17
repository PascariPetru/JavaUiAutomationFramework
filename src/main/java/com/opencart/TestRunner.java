package com.opencart;

import com.opencart.Managers.DataFakerManager;
import com.opencart.Managers.DriverManager;
import com.opencart.Managers.ScrollManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();

        driver.get("https://andreisecuqa.host/");

        String currentWindowName = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://andreisecuqa.host/");

        WebElement accountIcon = driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
        accountIcon.click();

        WebElement registerBtn = driver.findElement(By.xpath("//a[@class='dropdown-item'][normalize-space()='Register']"));
        registerBtn.click();

        String firstName = DataFakerManager.getRandomName();
        System.out.println("The generated first name is " + firstName);

        String lastName = DataFakerManager.getRandomName();
        System.out.println("The generated last name is " + lastName);

        String email = DataFakerManager.getRandomEmail();
        System.out.println("The generated email is " + email);

        String password = DataFakerManager.getRandomPassword(14, 20);
        System.out.println("The generated password is " + password);

        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys(lastName);

        WebElement emailInput = driver.findElement(By.id("input-email"));
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        passwordInput.sendKeys(password);


        Thread.sleep(500);
        WebElement privacyToggleBox = driver.findElement(By.xpath("//input[@name='agree']"));
        ScrollManager.scrollToElement(driver, privacyToggleBox);
        privacyToggleBox.click();

        WebElement continueButton = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
        continueButton.click();


        Thread.sleep(5000);


        driver.close();

        driver.switchTo().window(currentWindowName);
        Thread.sleep(5000);

        driver.get("https://andreisecuqa.host/");


        driver.quit();

        System.out.println("The Execution was finished");


    }
}
