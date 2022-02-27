package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US_1_login {
// Nextbase CRM.  An ideal summary of all customer data and sales activities in one place.
    //Developing and maintaining test tools and frameworks to validate unit,
    // integration and functional tests of the system.

    // As a user, I should be able to log in to the NextBaseCRM.

    // Scenarios
    //1. Verify the title
    //2. Verify Users (HR, marketing, Helpdesk) login successfully
    //3. Verify Users see “Incorrect username or password” for the invalid login attempt
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        driver =WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5);
        driver.close();
    }

    @Test(priority = 1)
    public void title_verification() {
        //1. The login page title should be “Authorization”.
        System.out.println(driver.getTitle());
        String expectedLogInTitle = "Authorization";
        String actualLogInTitle = driver.getTitle();
        Assert.assertEquals(actualLogInTitle, expectedLogInTitle);
    }
    @Test(priority = 2)
    public void checkBoxLableIsDisplayed() {
        // 2. Checkbox label should be displayed on the left side of “Remember me on this computer”.
        WebElement checkBoxLable = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkBoxLable.isDisplayed();
    }

    @Test(priority = 3)
    public void login_with_valid_credentials_with_login_btn() {
        // 2-write username
        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
        userName.sendKeys(ConfigurationReader.getProperty("helpdesl"));
        //     * 3-write password
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        //     * 4-click login button
        WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        BrowserUtils.sleep(3);
        loginBtn.click();
        // 5 verify title
        String expectedTitle="Portal";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(priority = 4)
    public void login_with_valid_credentials_with_enter_btn() {
        // 2-write username
        //     * 3-write password
        //     * 4-click login button
        // 5 verify title
        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
        userName.sendKeys(ConfigurationReader.getProperty("hr"));
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        password.sendKeys(ConfigurationReader.getProperty("password")+ Keys.ENTER);
        String expectedTitle="Portal";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

    }

    @Test(priority = 5)
    public void login_with_valid_username_invalid_password() {
        WebElement logInUserNamwe = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        logInUserNamwe.sendKeys(ConfigurationReader.getProperty("marketing"));
        WebElement logInPassword = driver.findElement(By.xpath("//input[@type='password']"));
        logInPassword.sendKeys("UserUser1");
        WebElement logInButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
        logInButton.click();
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class='errortext']")).getText();
        String expectedErrorMessage = "Incorrect username or password";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}
