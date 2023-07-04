package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.ScrollManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GenericSteps {

    WebDriver driver = DriverManager.getInstance().getDriver();

    @Given("the {string} is accessed")
    public void theIsAccessed(String urlValue) {
        driver.get(urlValue);
        System.out.println("The " + urlValue + "was accessed by the Driver! ");


    }

    @Then("the following error messages are displayed:")
    public void theFollowingErrorMessagesAreDisplayed(List<String> errorMessagesList) throws InterruptedException {
        for (int i = 0; i < errorMessagesList.size(); i++) {
            Thread.sleep(500);

            String elementXpath = "//*[text()='" + errorMessagesList.get(i) + " ']";
            WebElement errorMessageElement = driver.findElement(By.xpath(elementXpath));
            boolean isErrorMessageNumberIDisplayed = errorMessageElement.isDisplayed();
            Assertions.assertTrue(isErrorMessageNumberIDisplayed, "The error message " + errorMessagesList.get(i) + "is displayed");


        }
    }

    @Then("the current url contains the following keyword: {string}")
    public void theCurrentUrlContainsTheFollowingKeyword(String keyword) {

        boolean urlContainsCollectString = driver.getCurrentUrl().contains(keyword);

        System.out.println(driver.getCurrentUrl());
        Assertions.assertTrue(urlContainsCollectString, "The" + keyword + "is present within the URL");

    }

    @When("{string} from {string} is clicked")
    public void fromIsClicked(String elementName, String elementContainingPage) {
        try {
            Class classInstance = Class.forName("com.opencart.pageobjects." + elementContainingPage);
            Field webElementField = classInstance.getDeclaredField(elementName);
            webElementField.setAccessible(true);
            WebElement webElementToBeClicked = (WebElement) webElementField.get(classInstance.getConstructor(WebDriver.class).newInstance(driver));
            ScrollManager.scrollToElement(webElementToBeClicked);
            webElementToBeClicked.click();
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
