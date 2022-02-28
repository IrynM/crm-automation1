package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US_14_user_should_make_appreciation {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String username = ConfigurationReader.getProperty("username");
        String password = "UserUser";
        String url = ConfigurationReader.getProperty("env");

        String browserType = ConfigurationReader.getProperty("browser");
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);//go to login page

        //enter credentials as argument username && password and log in
        CRM_Utilities.crm_login(driver, username, password);
    }
    @Test
    public void makeAppreciationEmptyMessage() {
        //Users are on the homepage
        //Users click MORE tab and select APPRECIATION
        //Users click the SEND button
        //Verify “The message title is not specified” warning
        //message is displayed on the page

        BrowserUtils.sleep(1);
        //locating MORE button and click on it
        WebElement moreButton = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
        moreButton.click();

        BrowserUtils.sleep(1);
        //locating APPRECIATON btn and click on it
        WebElement appreciationButton = driver.findElement(By.xpath("//span[@class='menu-popup-item-text' and .='Appreciation']"));
        appreciationButton.click();

        BrowserUtils.sleep(1);
        //locate SEND btn and click on it
        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        //click send button
        sendButton.click();

        //locating error message
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='feed-add-error']"));
        String actualErrorMessage = errorMessage.getText();
        String expcetedErrorMessage = "The message title is not specified";

        Assert.assertTrue(actualErrorMessage.equals(expcetedErrorMessage), "Message not displayed");
        //System.out.println("The message title is not specified is displayed: " + actualErrorMessage.equals(expcetedErrorMessage));
    }

    @Test
    public void makeAppreciationSuccessfully() {
        //1. Users are on the homepage
        //2. Users click MORE tab and select APPRECIATION
        //3. Users write an Appreciation message
        //4. Users click the SEND button
        //5. Verify the Appreciation is displayed on the feed

        BrowserUtils.sleep(1);
        //locating MORE button and click on it
        WebElement moreButton = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
        moreButton.click();

        BrowserUtils.sleep(1);
        //locating APPRECIATON btn and click on it
        WebElement appreciationButton = driver.findElement(By.xpath("//span[@class='menu-popup-item-text' and .='Appreciation']"));
        appreciationButton.click();

        //locating iframe where to type message
        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']"));
        driver.switchTo().frame(iframe);

        //locate edittable
        WebElement contentEditTable = driver.findElement(By.xpath("//body[@contenteditable='true']"));
        //contentEditTable.click();

        BrowserUtils.sleep(1);
        contentEditTable.sendKeys("Test Auto sent message G2");
        driver.switchTo().parentFrame();
        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        //click send button
        BrowserUtils.sleep(1);
        sendButton.click();
    }

    @Test
    public void check_if_message_displayed(){
        BrowserUtils.sleep(1);
        String expectedMessage = "Test Auto sent message G2";
        WebElement moreEventsButton = driver.findElement(By.xpath("//span[@class='feed-new-message-inf-text']"));
        moreEventsButton.click();
        WebElement message = driver.findElement(By.xpath("//div[@class='feed-post-text-block-inner-inner' and .='"+expectedMessage+"']"));
        String actualMessage = message.getText();
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertEquals(actualMessage,expectedMessage);
    }
}
/*
As a user, I should be able to Make Announcements using the Announcements tab.
AC:
1. Users should be able to write messages in and send announcements by clicking the SEND button.
2. When users attempt to make announcements without a message,
there should be a working message “The message title is not specified”.
 */