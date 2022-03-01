package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US_4_MyProfile_options {

    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));


    }

    @Test
    public void MyProfile_options_user() {
        CRM_Utilities.crm_login(driver, ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
        WebElement profileBtn = driver.findElement(By.xpath("//span[@id='user-name']"));
        profileBtn.click();

        List<WebElement> profileOptionsList = driver.findElements(By.xpath("//div[@class='menu-popup-items']/*"));

        List<String> expectedOptionsList = new ArrayList<>(Arrays.asList("My Profile","Edit Profile Settings","Themes",
                "Configure notifications", "Logout"));
        List<String>actualOptionsList = new ArrayList<>();

        int index=0;
        for (WebElement each : profileOptionsList) {

            BrowserUtils.sleep(2);
            Assert.assertEquals(each.getText(),expectedOptionsList.get(index++));
        }
    }
    @AfterMethod
    public void tear_down() {
        driver.close();
//

    }
    }






