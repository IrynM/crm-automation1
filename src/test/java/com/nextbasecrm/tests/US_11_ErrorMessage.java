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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US_11_ErrorMessage {
    public WebDriver driver;

    @BeforeTest
    public void beforeClass() {

        String browserType = ConfigurationReader.getProperty("browser");
        String env = ConfigurationReader.getProperty("env");
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(env);

    }

    @Test
    public void errorMessage() {
        //   String[] username ={"hr4@cydeo.com","hr5@cydeo.com","hr6@cydeo.com","helpdesk4@cydeo.com",
        //  "helpdesk5@cydeo.com","helpdesk6@cydeo.com","marketing4@cydeo.com","marketing5@cydeo.com",
        //  "marketing6@cydeo.com"};
        //  for (String eachUserName : username) {
        String username=ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");
        CRM_Utilities.crm_login(driver, username, password);
        WebElement taskTab = driver.findElement(By.xpath("//div[@class ='feed-add-post-form-variants']//span[2]"));
        taskTab.click();

        WebElement iframe = driver.findElement(By.xpath("//div[@id='bx-html-editor-iframe-cnt-lifefeed_task_form']/iframe"));
        driver.switchTo().frame(iframe);
        BrowserUtils.sleep(3);
        WebElement taskContentWindow = driver.findElement(By.xpath("//body[@contenteditable='true']"));
        taskContentWindow.sendKeys("Aziza automated task 30");
        driver.switchTo().defaultContent();
        WebElement sendButton = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendButton.click();
        String expectedErrorMessage = "The task name is not specified.";
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class='task-message-label error']")).getText();
        Assert.assertTrue(expectedErrorMessage.equals(actualErrorMessage));

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
