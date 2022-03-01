package com.nextbasecrm.tests;

import com.github.javafaker.Faker;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.module.Configuration;
import java.util.concurrent.TimeUnit;

public class US_5_SendMessage {

    public WebDriver driver;

    @BeforeMethod
    public void setupMethod() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver, ConfigurationReader.getProperty("username"), "password");


    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test()
    public void crm_sendMessage() {
        Faker name1= new Faker();
        String random = name1.name().name();
        // Click to Message Tab
        WebElement messageBox = driver.findElement(By.xpath("//*[@id='microoPostFormLHE_blogPostForm']"));
        messageBox.click();

        //We need to switch driver's focus to iframe
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']")));

        WebElement iframeMessage = driver.findElement(By.xpath("//html[1]//body[@contenteditable='true']"));
        iframeMessage.sendKeys(random);

        //switch back to "main HTML"
        driver.switchTo().parentFrame();

        WebElement sendButton = driver.findElement(By.xpath("//div[@id='feed-add-buttons-blockblogPostForm']/button[1]"));
        sendButton.click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[.='"+random+"']")).isDisplayed());
    }
    @Test()
    public void crm_warningMessage_Test() {



        // Click to Message Tab
        WebElement messageBox =driver.findElement(By.xpath("//*[@id='microoPostFormLHE_blogPostForm']"));
        messageBox.click();


        //switch back to "main HTML"
        driver.switchTo().parentFrame();

        // Click to send button
        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();

        //8.Displayed warning message "The message title is not specified"
        WebElement warningMessage = driver.findElement(By.xpath("//span[@class='feed-add-info-text']"));
        System.out.println("warningMessage.isDisplayed() = " + warningMessage.isDisplayed());

        String expectedWarningMessage ="The message title is not specified";
        String actualWarningMessage =warningMessage.getText();

        Assert.assertEquals(actualWarningMessage,expectedWarningMessage);

    }
}