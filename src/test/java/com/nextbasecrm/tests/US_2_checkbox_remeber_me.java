package com.nextbasecrm.tests;

import com.google.common.base.Verify;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US_2_checkbox_remeber_me {
    WebDriver driver;
    String url = "https://login2.nextbasecrm.com"; //login page

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Users go to the login page
        driver.get(url);
    }

    @Test
    public void checkBoxLabel() throws InterruptedException {
        int index = 1;
        //locate checkbox element
        WebElement checkBox = driver.findElement(By.xpath("//input[@id='USER_REMEMBER']"));

        //check if checkbox is displayed
        Assert.assertTrue(checkBox.isDisplayed());
        System.out.println(index++ +".CheckBox is displayed: " + checkBox.isDisplayed());

        Thread.sleep(1000);
        //click to check checkbox
        checkBox.click();

        //Verify user can check the remember me checkbox
        System.out.println(index++ +".User can check checkbox: " + checkBox.isSelected());
        Assert.assertTrue(checkBox.isSelected());

        //locate and verify if label "Remember me on this computer" is displayed
        WebElement labeRemeberMe = driver.findElement(By.xpath("//label[@class='login-item-checkbox-label']"));
        Assert.assertTrue(labeRemeberMe.isDisplayed());

        String expectedMessageDisplayed = "Remember me on this computer";
        String actualMessageDisplayed = labeRemeberMe.getText();

        //compare if acutal label message is displayed and matching with expected
        Assert.assertTrue(labeRemeberMe.isDisplayed());
        System.out.println(index++ +".Message Remember me on this computer is diplayed: " + labeRemeberMe.isDisplayed());
        System.out.println(index++ +".Expected message matching with actual: " + expectedMessageDisplayed.equals(actualMessageDisplayed));
    }

}
/*
User Story:
As a user, I should be able to save my user credential on a computer.
AC:
1. There should be a Checkbox label displayed to enable users to save their user credentials on computers.
2. “Remember me on this computer” should be displayed on the right side of the checkbox label.

Test cases #1
Description:  check the “Remember me” checkbox
Environment:  https://login2.nextbasecrm.com/
Steps:: Users go to the login page
            Verify user can check the remember me checkbox
            Verify there is a “Remember me on this computer” message displayed

 */