package com.agorafy.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.seleniumhq.jetty7.util.resource.Resource;

import com.agorafy.automation.automationframework.AutomationLog;
import com.sun.jna.platform.unix.X11.Window;

public class LoginPage extends Page 
{
    private WebElement element = null;

    public LoginPage(WebDriver driver) 
    {
        super(driver);
    }

    protected boolean isSecured()
    {
        return true;
    }

    public Homepage doSuccessfulLogin(String username, String password) throws Exception
    {
        Homepage element = null;
        try
        {
            doLoginWithCredentials(username, password);
            element = Homepage.homePage();
            
        }
        catch (Exception e)
        {
            AutomationLog.error("Login failed.");
            throw(e);
        }
        return element;
    }

    public LoginPage doInvalidLogin(String username, String password) throws Exception
    {
        LoginPage element = null;
        try
        {
            doLoginWithCredentials(username, password);
            element = new LoginPage(driver);
        }
        catch (Exception e)
        {
            AutomationLog.error("Invalid Login Failed");
            throw(e);
        }
        return element;
    }

    private void doLoginWithCredentials(String username, String password) throws Exception
    {
        try
        {
            WebElement userNameTextBox = txtbx_UserName();
            userNameTextBox.clear();
            userNameTextBox.sendKeys(username);
            WebElement passwordTextBox = txtbx_Password();
            passwordTextBox.clear();
            passwordTextBox.sendKeys(password);
            button_LoginToAccount().click();
        }
        catch (Exception e)
        {
            AutomationLog.error("Login Failed");
            throw(e);
        }
    }

    public WebElement txtbx_UserName() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='LoginPageForm']/div[1]/input"));
            AutomationLog.info("Username text box found on the Login Page");
        }
        catch (Exception e)
        {
            AutomationLog.error("UserName text box was not found on the Login Page");
            throw(e);
        }
        return element;
    }

    public WebElement txtbx_Password() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='LoginPageForm']/div[2]/input"));
            AutomationLog.info("Password text box found on the Login Page");
        }
        catch (Exception e)
        {
            AutomationLog.error("Password text box was not found on the Login Page");
            throw(e);
        }
        return element;
    }

    public WebElement button_LoginToAccount() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='LoginPageForm']/div[5]/input"));
            AutomationLog.info("Login to Account button found on the Login Page");
        }
        catch (Exception e)
        {
            AutomationLog.error("Login to Account button was not found on the Login Page");
            throw(e);
        }
        return element;
    }

    public WebElement image_Captcha() throws Exception
    {
        try 
        {
            element = driver.findElement(By.id("recaptcha_challenge_image"));
            AutomationLog.info("Captcha found on the Login Page");
        } 
        catch (Exception e) 
        {
            AutomationLog.error("Captcha not found on the Login Page");
            throw(e);
        }
        return element;
    }

    public boolean isCaptchaPresent() throws Exception
    {
        Boolean bool = null;
        try
        {
            bool = driver.findElement(By.id("recaptcha_challenge_image")).isDisplayed();
        }
        catch(Exception e)
        {
            AutomationLog.error("Captcha element is not displayed");
        }
        return bool;
    }

    public WebElement txtbx_Captcha() throws Exception
    {
        try 
        {
            element = driver.findElement(By.id("recaptcha_response_field"));
            AutomationLog.info("Captcha text box found on the Login Page");
        } 
        catch (Exception e) 
        {
            AutomationLog.error("Captcha text box not found on the Login Page");
            throw(e);
        }
    	return element;
    }

    public WebElement link_ForgotPassword() throws Exception
    {
        try 
        {
            element = driver.findElement(By.id("forgotpass"));
            AutomationLog.info("Forgot password link found on the Login Page");
        }
        catch (Exception e) 
        {
            AutomationLog.error("Forgot password link not found on the Login Page");
            throw(e);
        }
        return element;
    }

    public WebElement checkbox_stayLoggedIn() throws Exception
    {
        try 
        {
            element = driver.findElement(By.id("rememberMePage"));
            AutomationLog.info("Checkbox for 'Stay logged in' found");
        } 
        catch (Exception e) 
        {
            AutomationLog.error("checkbox for 'Stay logged in' not found");
            throw(e);
        }
        return element;
    }

    public WebElement label_stayLoggedIn() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='LoginPageForm']/div[4]/div/label"));
            AutomationLog.info("Label 'Stay logged in' found");
        } 
        catch (Exception e)
        {
            AutomationLog.error("Label 'Stay logged in' not found");
        }
        return element;
    }

    public WebElement message_InvalidEmailPassword() throws Exception
    {
        try 
        {
            //element = driver.findElement(By.xpath(".//*[@id='login_form']/p"));
            element = driver.findElement(By.id("login_form")).findElement(By.className("error"));
            AutomationLog.info("Invalid email or password message found on the form");
        } 
        catch (Exception e)
        {
            AutomationLog.error("Invalid email password message not found on the form");
            throw(e);
        }
        return element;
    }

    public WebElement link_Facebook() throws Exception
    {
        try 
        {
            element = driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[2]/ul/li[1]/a"));
        }
        catch (Exception e)
        {
            throw(e);
        }
        return element;
    }

    public WebElement link_Twitter() throws Exception
    {
        try 
        {
            element = driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[2]/ul/li[2]/a"));
        }
        catch (Exception e)
        {
            throw(e);
        }
        return element;
    }

    public ForgotPassword clickOnForgotPassword() throws Exception
    {
        ForgotPassword forgotpasswordlink = null;
        try
        {
            link_ForgotPassword().click();
            forgotpasswordlink = new ForgotPassword(driver);
            AutomationLog.info("Forgot Password Link is clicked");
        }
        catch(Exception e)
        {
            AutomationLog.error("Forgot Password Link could Not be clicked");
            throw(e);
        }
        return forgotpasswordlink;
     }

    public String LoginPageHeading_BackToLoginLink() throws Exception
    {
        String PageHeader="";
        try
        {
            PageHeader =driver.findElement(By.xpath("//*[@id='page-title']")).getText();
        }
        catch(Exception e)
        {
            AutomationLog.error("LoginPageHeading_BackToLoginLink PageHeading not found in login page");
            throw(e);
        }
        return PageHeader;
    }
    public WebElement LoginPageFormId_BackToLoginLink() throws Exception
    {
        try
        {
        	element =driver.findElement(By.id("login_form"));
        }
        catch(Exception e)
        {
            AutomationLog.error("LoginPageFormId not found in login page");
            throw(e);
        }
        return element;
    }

    public String getPageUrl()
    {
        return (applicationUrl()+"/login");
    }
}
