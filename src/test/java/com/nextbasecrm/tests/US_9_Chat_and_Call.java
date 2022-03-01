package com.nextbasecrm.tests;

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

import java.util.concurrent.TimeUnit;

public class US_9_Chat_and_Call {

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){

        String browserType= ConfigurationReader.getProperty("browser");
        String env = ConfigurationReader.getProperty("env");

        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(env);


    }

    @Test
    public void test1(){

        String eachUsername = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");

        CRM_Utilities.crm_login(driver,eachUsername,password);


        WebElement chatAndCallsButton= driver.findElement(By.xpath("//a[@class='menu-item-link' and @title='Chat and Calls']"));
        chatAndCallsButton.click();

        WebElement messageModule= driver.findElement(By.xpath("//div[@id='bx-desktop-tab-im']"));
        Assert.assertTrue(messageModule.isDisplayed());
        System.out.println("messageModule.isDisplayed() = " + messageModule.isDisplayed());

        WebElement notificationsModule = driver.findElement(By.xpath("//div[@id='bx-desktop-tab-notify']"));
        Assert.assertTrue(notificationsModule.isDisplayed());
        System.out.println("notificationsModule.isDisplayed() = " + notificationsModule.isDisplayed());

        WebElement settingsModule= driver.findElement(By.xpath("//div[@id='bx-desktop-tab-config']"));
        Assert.assertTrue(settingsModule.isDisplayed());
        System.out.println("settingsModule.isDisplayed() = " + settingsModule.isDisplayed());

        WebElement activityStreamModule=driver.findElement(By.xpath("//div[@id='bx-desktop-tab-im-lf']"));
        Assert.assertTrue(activityStreamModule.isDisplayed());
        System.out.println("activityStreamModule.isDisplayed() = " + activityStreamModule.isDisplayed());
    }
    @AfterMethod
    public void tearDownMethod(){

        driver.close();

    }
}
