package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegistrationFLowTestSuite {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    static int counter = 0;


    @BeforeEach
    public void executeTheCodeBeforeEachTestFromThisClass() {
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The test number: " + counter + " started");


    }

    @Test
    @DisplayName("The URL contains success keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectUrl() throws InterruptedException {

        homePage.navigateToRegisterPageFromHeaderMenu();


        String firstName = DataFakerManager.getRandomName();

        String lastName = DataFakerManager.getRandomName();

        String email = DataFakerManager.getRandomEmail();

        String password = DataFakerManager.getRandomPassword(14, 20);

        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("success");
        Assertions.assertTrue(urlContainsTheCorrectKeyWords, "The url " + driver.getCurrentUrl() + "Contains success keywork");


        DriverManager.getInstance().quitTheDriver();

        System.out.println("The Execution was finished");


    }

    @Test
    @DisplayName("The URL contains register keyword when privacy toggle is not enabled")
    public void registerFlowIsBlockedByPrivacyPolicyToggleIsNotAccepted() throws InterruptedException {


        homePage.navigateToRegisterPageFromHeaderMenu();


        String firstName = DataFakerManager.getRandomName();

        String lastName = DataFakerManager.getRandomName();

        String email = DataFakerManager.getRandomEmail();

        String password = DataFakerManager.getRandomPassword(14, 20);

        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        // Do not enable privacy toggle
        // registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(2000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("success");
        Assertions.assertFalse(urlContainsTheCorrectKeyWords, "The url " + driver.getCurrentUrl() + "does not contain success keyword");

        boolean urlCOntainsRegisterKeyword = driver.getCurrentUrl().contains("register");
        Assertions.assertTrue(urlCOntainsRegisterKeyword, "The URL belongs to the register page");

        driver.close();

        DriverManager.getInstance().quitTheDriver();

        System.out.println("The Execution was finished");


    }

    @AfterEach
    public void executeThisMethodAfterEachTestCase() {
        DriverManager.getInstance().quitTheDriver();
        System.out.println("The test was Finished");
    }
}
