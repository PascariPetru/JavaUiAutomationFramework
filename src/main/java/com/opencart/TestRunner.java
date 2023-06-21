package com.opencart;

import com.opencart.Managers.DataFakerManager;
import com.opencart.Managers.DriverManager;
import com.opencart.Managers.ScrollManager;
import com.opencart.Pageobjects.AccountCreatedPage;
import com.opencart.Pageobjects.HomePage;
import com.opencart.Pageobjects.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();

        driver.get("https://andreisecuqa.host/");

        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPageFromHeaderMenu();


        String firstName = DataFakerManager.getRandomName();

        String lastName = DataFakerManager.getRandomName();

        String email = DataFakerManager.getRandomEmail();

        String password = DataFakerManager.getRandomPassword(14, 20);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.logOutFromTheAccount();
        ;
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        Thread.sleep(3000);


        driver.close();

        driver.quit();

        System.out.println("The Execution was finished");


    }
}
