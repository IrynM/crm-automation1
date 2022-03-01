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

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class US10_Access_The_Correct_Modules_Margie {

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
    public void ActivityStreamModule() {

        //locate Activity Stream Module WebElement
        WebElement ActivityStreamModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[1]"));
        //click on the Activity Stream Module
        ActivityStreamModule.click();

        //compare expected title to the actual title of the tab
        String expectedTitleOfActivityStreamModule = "Portal";
        String actualTitleOfActivityStreamModule = driver.getTitle();

        Assert.assertEquals(actualTitleOfActivityStreamModule, expectedTitleOfActivityStreamModule);
    }

    @Test
    public void TasksModule() {

        //locate the Tasks Module.
        WebElement TasksModule = driver.findElement(By.xpath("(//span[@class='menu-item-link-text'])[2]"));
        //click on the Tasks Module.
        TasksModule.click();
        //compare expected title to the actual title of the tab
        String expectedTasksModule = "Site map";
        String actualTasksModule = driver.getTitle();

        Assert.assertEquals(actualTasksModule, expectedTasksModule);

    }

    @Test
    public void Chat_and_CallsModule() {

        //locate the Chat and Calls Module.
        WebElement Chat_and_CallsModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[3]"));
        //click on the Chat and Calls Module.
        Chat_and_CallsModule.click();
        //compare expected title to the actual title of the tab
        String expectedChat_and_CallsModule = "Chat and Calls";
        String actualChat_and_CallsModule = driver.getTitle();

        Assert.assertEquals(actualChat_and_CallsModule, expectedChat_and_CallsModule);
    }

    @Test
    public void WorkgroupsModule() {

        //locate the Workgroups Module.
        WebElement WorkgroupsModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[4]"));
        //clicks on the Workgroups Module.
        WorkgroupsModule.click();
        //compare expected title to the actual title of the tab
        String expectedWorkgroupsModule = "Workgroups and projects";
        String actualWorkgroupsModule = driver.getTitle();

        Assert.assertEquals(actualWorkgroupsModule, expectedWorkgroupsModule);
    }


    @Test
    public void DriveModule() {

        //locate the Drive Module.
        WebElement DriveModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[5]"));
        //clicks on the Drive Module.
        DriveModule.click();
        //compare expected title to the actual title of the tab
        String expectedDriveModule = "Site map";
        String actualDriveModule = driver.getTitle();

        Assert.assertEquals(actualDriveModule, expectedDriveModule);
    }
    @Test
    public void CalendarModule(){
        //locate the Calendar Module
        WebElement CalendarModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[6]"));
        //click on the Calendar Module
        CalendarModule.click();
        //compare expected title to the actual title of the tab
        String expectedCalendarModule = "Site map";
        String actualCalendarModule = driver.getTitle();

        Assert.assertEquals(actualCalendarModule, expectedCalendarModule);
}
    @Test
    public void ContactCenterModule(){

          //locate the Contact Center Module.
        WebElement ContactCenterModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[8]"));
         //click on the Contact Center Module.
        ContactCenterModule.click();
         //compare expected title to the actual title of the tab
        String expectedContactCenterModule = "Contact Center";
        String actualContactCenterModule = driver.getTitle();

        Assert.assertEquals(actualContactCenterModule, expectedContactCenterModule);
}
@Test
    public void Time_And_ReportsModule(){

        //locate the Time_And_ReportsModule
        WebElement Time_And_ReportsModule= driver.findElement(By.xpath("(//span[@data-role='item-text'])[9]"));
       //click on Time_And_ReportsModule
       Time_And_ReportsModule.click();
       //compare expected title to the actual title of the tab
       String expectedTime_And_ReportsModule = "Absence Chart";
       String actualTime_And_ReportsModule = driver.getTitle();

       Assert.assertEquals(actualTime_And_ReportsModule, expectedTime_And_ReportsModule);


}
    @Test
    public void EmployeesModule(){

        //locate the Employees Module
       WebElement EmployeesModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[10]"));
       //click on EmployeesModule
       EmployeesModule.click();
       //compare expected title to the actual title of the tab
       String expectedEmployeesModule = "Company Structure";
       String actualEmployeesModule = driver.getTitle();

       Assert.assertEquals(actualEmployeesModule, expectedEmployeesModule);

}
    @Test
    public void ServicesModule(){
        //locate the Services Module
        WebElement ServicesModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[11]"));
       //click on Services Module
        ServicesModule.click();
       //compare expected title to the actual title of the tab
       String expectedServicesModule ="Meeting Rooms";
       String actualServicesModule = driver.getTitle();

       Assert.assertEquals(actualServicesModule, expectedServicesModule);

}
    @Test
    public void CompanyModule(){
        //locate the Company Module
    WebElement CompanyModule = driver.findElement(By.xpath("(//span[@data-role='item-text'])[12]"));
    // click on the Company Module
    CompanyModule.click();
    //compare expected title to the actual title of the tab
    String expectedCompanyModule = "Company";
    String actualCompanyModule = driver.getTitle();

    Assert.assertEquals(actualCompanyModule, expectedCompanyModule);

}
@Test
public void Modules(){
    List<WebElement>Modules = driver.findElements(By.xpath("//ul[@class='menu-items']//li[@data-status='show']"));
    for (WebElement each : Modules) {
        //each.click();
       // Assert.assertEquals(each.getText(), driver.getTitle());
       // BrowserUtils.sleep(1);
        System.out.println("each.getText() = " + each.getText());
    }
}
    @AfterMethod
    public void tearDownMethod(){
        driver.close();
}



}

