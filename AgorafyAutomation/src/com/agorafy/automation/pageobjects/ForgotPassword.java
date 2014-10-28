package com.agorafy.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.datamodel.profile.EmailData;

public class ForgotPassword extends Page
{
    private WebElement element = null;
    public ForgotPassword(WebDriver driver) 
    {
        super(driver);
    }

    public WebElement txtbox_EmailAddress() throws Exception
    {
        try
        {
            element=driver.findElement(By.id("forgotPassInput"));
            AutomationLog.info("Email Address found in the Forgot Password Page");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not found the email address");
            throw(e);
        }
            return element;
    }

    public WebElement btn_RequestNewPassword() throws Exception
    {
        try
        {
            element=driver.findElement(By.id("forgotPassSubmit"));
            AutomationLog.info("RequestNewPassword button found in the Forgot Password Page");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not found the RequestNewPassword button");
            throw(e);
        }
            return element;
    }

    public WebElement errorMessageEnterValidMail() throws Exception
    {
        try
        {
            element=driver.findElement(By.xpath(".//*[@id='forgotPassError']"));
            AutomationLog.info("Appropriate error message shown");
        }
        catch(Exception e)
        {
            AutomationLog.error("Error message not shown");
            throw(e);
        }
           return element;
    }

    public WebElement messageEnterValidMail() throws Exception
    {
        try
        {
            element=driver.findElement(By.xpath(".//*[@id='forgot_confirm_error']/h5"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Error message not shown");
            throw(e);
        }
           return element;
    }

    public WebElement messageEnterValidRegisteredMail() throws Exception
    {
        try
        {
            element=driver.findElement(By.xpath(".//*[@id='forgot_confirm']/h5"));
            AutomationLog.info("Appropriate error message shown");
        }
        catch(Exception e)
        {
            AutomationLog.error("Error message not shown");
            throw(e);
        }
           return element;
    }


    public ForgotPassword clickOnRequestNewPassword() throws Exception
    {
        ForgotPassword forgotpassword = null;
        try
        {
            btn_RequestNewPassword().click();
            forgotpassword = new ForgotPassword(driver);
            AutomationLog.info("Successfully clicked on RequestNewPassword button");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not clicked on RequestNewPassword button ");
            throw(e);
        }
        return forgotpassword;
    }

    public void populateForgotPasswordData(EmailData forgotpassworddata) throws Exception
    {
        try
        {
            txtbox_EmailAddress().clear();
            txtbox_EmailAddress().sendKeys(forgotpassworddata.getEmailAddress());
        }
            catch(Exception e)
        {
            AutomationLog.info("Successfully populated email address details");
            throw(e);
        }
    }
}