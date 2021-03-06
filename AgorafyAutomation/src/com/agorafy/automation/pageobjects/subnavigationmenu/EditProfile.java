package com.agorafy.automation.pageobjects.subnavigationmenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.pageobjects.Page;

public class EditProfile extends Page  
{
    WebElement element;

    public EditProfile(WebDriver driver) 
    {
        super(driver);
    }

    public WebElement EditProfileMainBlock() throws Exception
    {
        try 
        {
            element = driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div[2]/div[2]"));
            AutomationLog.info("edit profile main block found");
        }
        catch (Exception e) 
        {
            AutomationLog.error("failed to find edit profile main block");
            throw (e);
        }
        return element;
    }

    public WebElement element_PageHeading() throws Exception
    {
        try 
        {
            element = driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div[2]/div[1]/h2"));
            AutomationLog.info("edit profile page title found");
        }
        catch (Exception e) 
        {
            AutomationLog.error("failed to find edit profile title");
            throw (e);
        }
        return element;
    }

    public String getURL()
    {
        return applicationUrl() + "/manage/profile";
    }

    public String pageHeading() throws Exception
    {
        return element_PageHeading().getText();
    }
}
