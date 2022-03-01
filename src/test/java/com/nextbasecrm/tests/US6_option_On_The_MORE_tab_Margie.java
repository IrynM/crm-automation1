package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US6_option_On_The_MORE_tab_Margie {

    public WebDriver driver;

    @BeforeMethod
    public void setupMethod() {

        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //to get to the webpage
        driver.get(ConfigurationReader.getProperty("env"));

        //user logs in
        CRM_Utilities.crm_login(driver, ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
    }

    @Test
    public void MORE_Tab() throws InterruptedException {

        //3. Four options should be displayed under the MORE tab:

        //identify the dropdown with multiple select option
       int index=0;
       WebElement MOREButton = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
       MOREButton.click();
       List<WebElement>dropDownList=driver.findElements(By.xpath("//div[@class='menu-popup']//div/span"));

       List<String> expectedOptions = new ArrayList<>(Arrays.asList("File","Appreciation","Announcement","Workflow"));
        List<String>actualOptions = new ArrayList<>();
        for (WebElement each : dropDownList) {

            //System.out.println(each.getText());
          Assert.assertEquals(each.getText(), expectedOptions.get(index++));
        }


        }

    @AfterMethod
    public void tearDownMethod(){
        driver.close();
    }

    }

