package com.nextbasecrm.tests;

import com.google.common.base.Verify;
import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

 public class US_3_LogOut {

    //As a user, I should log out from the NextBaseCRM app.

    WebDriver driver;

    String username = "Hr4@cydeo.com";
    String password = "UserUser";


    @BeforeMethod
    public void setup(){
       driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.get(ConfigurationReader.getProperty("env"));
       CRM_Utilities.crm_login(driver);

    }
    @AfterClass
    public void afterClass(){
       BrowserUtils.sleep(10);
       driver.close();
    }

    /* Users are on the homepage
        Users click the user profile name
        Users select the “Log Out” option
         */

    @Test
    public void logOut(){
       WebElement profileBtn = driver.findElement(By.xpath("//div[@id='user-block']"));
       profileBtn.click();
       WebElement logOutBtn = driver.findElement(By.xpath("//a[@href=\"/auth/?logout=yes&backurl=%2Fstream%2F\"]"));
       logOutBtn.click();


      // Verify the user back to the login page

       BrowserUtils.verifyTitle(driver,"Authorization");
    }


}
