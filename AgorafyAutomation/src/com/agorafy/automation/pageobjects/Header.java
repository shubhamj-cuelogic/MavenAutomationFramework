package com.agorafy.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.automationframework.WaitFor;
import com.agorafy.automation.pageobjects.submitlisting.SubmitListingLocationFormPage;
import com.agorafy.automation.pageobjects.upsellpopups.LoginPopUp;

public class Header extends Page
{
    private WebElement element = null;
    private boolean val=false;

    public Header(WebDriver driver)
    {
        super(driver);
        }

    public WebElement link_Login() throws Exception
    {
        try 
        {
            element = driver.findElement(By.linkText("Log In"));
            AutomationLog.info("Login link found in the Header");
            }
        catch (Exception e)
        {
            AutomationLog.error("Login link was not found in the Header");
            throw(e);
        }

        return element;
    }

    public By getProfileNameLocator() 
    {
        return  (By.className("profile-name"));
    }

    public WebElement link_SignUp() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='headerRegisterButton']/span"));
            AutomationLog.info("SignUp link found in the Header");
        }
        catch (Exception e)
        {
            AutomationLog.error("Sign up link was not found in the Header");
            throw(e);
        }

        return element;
    }

    public SignUp clickOnSignUpUpLink() throws Exception
    {
        SignUp signup = null;
        try
        {
            link_SignUp().click();
            signup = new SignUp(driver);
            AutomationLog.info("Successfully clicked on signup Link");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not click on signUp Link");
            throw(e);
        }
        return signup;
    }

    public WebElement link_SubmitListing() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='mainNav']/li[1]/a"));
            AutomationLog.info("Submit listing link found in the Header");
        }
        catch (Exception e)
        {
            AutomationLog.error("Submit listing link was not found in the Header");
            throw(e);
        }
        return element;
    }

    public LoginPopUp clickOnSubmitListingLink() throws Exception
    {
        LoginPopUp loginpopup=null;
        try
        {
            link_SubmitListing().click();
            loginpopup=new LoginPopUp(driver);
            AutomationLog.info("Successfully clicked on submit listing link");
        }
        catch(Exception e)
        {
            AutomationLog.error("could not click on submit listing link");
            throw(e);
        }
        return loginpopup;
    }

    public Page clickSubmitListing(Boolean loginStatus) throws Exception
    {
        Page page= null;
        link_SubmitListing().click();

        if(loginStatus)
        {
            page = new SubmitListingLocationFormPage(driver);
            return page;
        }
        else
        {
            page = new LoginPopUp(driver);
            return page;
        }
    }

    public boolean loginPopUpIsDisplayed(LoginPopUp loginpopup) throws Exception
    {
        try
        {
            val = loginpopup.popUp_Login().isDisplayed();
            AutomationLog.info("login pop up is displayed");
        }
        catch(Exception e)
        {
            AutomationLog.error("Login pop up is not displayed");
            throw(e);
        }
        return val;
    }

    //Links below are seen when user is logged in

    public WebElement link_ProfileNameOnHomepageAfterLogin() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='mainNav']/li[3]/a[1]/span[2]"));
        }
        catch (Exception e)
        {
            AutomationLog.error("Profile name was not found in the Header");
            throw(e);
        }
        return element;
     }

    public WebElement link_ProfileNameOnDashboardAfterLogin() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='mainNav']/li[4]/a[1]/span[2]"));
        }
        catch (Exception e)
        {
            AutomationLog.error("Profile name was not found in the Header");
            throw(e);
        }
        return element;
     }

    public WebElement link_Dashboard() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='mainNav']/li[3]/ul/li[1]/a"));
        }
        catch (Exception e)
        {
            AutomationLog.error("My Dashboard link not found when Profile name is clicked");
            throw(e);
        }
        return element;
    }

    public WebElement link_Logout() throws Exception
    {
        try
        {
            element = driver.findElement(By.linkText("Logout"));
        }
        catch (Exception e)
        {
            AutomationLog.error("Logout link Not found in the Header");
            throw(e);
        }
        return element;
    }

    public void openActiveProfile() throws Exception
    {
        try
        {
            link_ProfileNameOnHomepageAfterLogin().click();
            AutomationLog.info("Opened drop down for active profile link");
        }
        catch (Exception e)
        {
            AutomationLog.error("Not able to open drop down for active profile link");
            throw(e);
        }
    }

    public Dashboard openDashboard() throws Exception
     {
        Dashboard element = null;
        try
        {
             link_Dashboard().click();
             element = new Dashboard(driver);
             AutomationLog.info("Opened Dashboard successfully");
         }
         catch (Exception e)
         {
             AutomationLog.error("Not able to open Dashboard");
             throw(e);
         }
         return element;
     }

    public WebElement text_ProfileName() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath(".//*[@id='mainNav']/li[4]/a[1]/span[2]"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Profile Name not found on header");
            throw(e);
        }
        return element;
    }

    public String profileName() throws Exception
    {
        String profileName = "";
        try
        {
            profileName = text_ProfileName().getText();
            AutomationLog.info("Name of LoggedIn user found on header");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not retrieve Profile Name");
            throw(e);
        }
        return profileName;
    }

    public String greeting() throws Exception
    {
        String greeting="";
        try
        {
            greeting = driver.findElement(By.xpath(".//*[@id='mainNav']/li[3]/a[1]/span[2]")).getText();
            AutomationLog.info("Greeting found after successful Login");
        }
        catch(Exception e)
        {
            AutomationLog.error("Not able to find Greeting after successful Login");
            throw(e);
        }
        return greeting;
    }
    
    public WebElement dropbox_navigateArrowsearchInputBox() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("advancedSearch"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Search Input dropbox is not present");
            throw(e);
        }
        return element;
    }
    
    public void clickOndropbox_searchInputBox() throws Exception
    {
        try
        {
            dropbox_navigateArrowsearchInputBox().click();
            AutomationLog.info("Successfully clicked on navigate arrow of dropbox_searchInputBox");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not clicked on navigate arrow of dropbox_searchInputBox ");
            throw(e);
        }
    }
    
    public WebElement form_AdvancedSearch() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("advancedSearchForm"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not found Advance Search form");
            throw(e);
        }
        return element;
    }

    public WebElement icon_AdvanceSearchDropDown() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("advancedSearch"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not find AdvanceSearch Dropdown icon ");
            throw(e);
        }
        return element;
    }

    public void clickOnAdvanceSearchDropDownIcon() throws Exception
    {
        try
        {
            icon_AdvanceSearchDropDown().click();
            AutomationLog.info("Successfully clicked on AdvanceSearch DropDown icon");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not click on AdvanceSearch DropDown icon");
        }
        
    }

    public WebElement txtbx_BedsInAdvanceSearchForm() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("bedsInput"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not find Textbox for beds in advance search form    ");
            throw(e);
        }
        return element;
    }


    public WebElement txtbx_BathInAdvanceSearchForm() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("bathsInput"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not find Textbox for beds in advance search form    ");
            throw(e);
        }
        return element;
    }

    public void BedsInAdvanceSearchForm(String badNo) throws Exception
    {
        try
        {
            txtbx_BedsInAdvanceSearchForm().sendKeys(badNo);
            AutomationLog.error("Could put the "+badNo+"number of both");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not put the "+badNo+"number of both");
            throw(e);
        }
    }

    public void BathInAdvanceSearchForm(String bathNo) throws Exception
    {
        try
        {
            txtbx_BathInAdvanceSearchForm().sendKeys(bathNo);
            AutomationLog.error("Could put the "+bathNo+"number of both");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not put the "+bathNo+"number of both");
            throw(e);
        }
    }

    public WebElement btn_SearchOnAdvanceSearchForm() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("searchFormAdvancedButton"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not find search button on advance search form");
            throw(e);
        }
        return element;
    }

    public void clickOnSearchButtonOnAdvanceSearchform() throws Exception
    {

        try
        {
            btn_SearchOnAdvanceSearchForm().click();
            AutomationLog.info("Successfully clicked on Search button on Advanced search form");
        }
        catch(Exception e)
        {
            AutomationLog.error("could not click on Search button on Advanced search form");
            throw(e);
        }
    }

    public void searchByNoOfBeds(String val) throws Exception
    {
        clickOnAdvanceSearchDropDownIcon();
        txtbx_BedsInAdvanceSearchForm().clear();
        txtbx_BedsInAdvanceSearchForm().sendKeys(val);
        AutomationLog.info("Successfully entered Search text for no of beds ");
        clickOnSearchButtonOnAdvanceSearchform();
    }

    public void searchByNoOfBaths(String val) throws Exception
    {
        clickOnAdvanceSearchDropDownIcon();
        txtbx_BathInAdvanceSearchForm().clear();
        txtbx_BathInAdvanceSearchForm().sendKeys(val);
        AutomationLog.info("Successfully entered Search text for no of baths ");
        clickOnSearchButtonOnAdvanceSearchform();
    }


    public WebElement reportCount() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("reportCount"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not found report count in prfile name dropdown");
            throw(e);
        }
        return element;
    }

    public WebElement link_Reports() throws Exception
    {
       try
       {
           element = driver.findElement(By.id("viewReport"));
       }
       catch(Exception e)
       {
           AutomationLog.error("Could not found Reports link in Profile DropDown");
           throw(e);
       }
       return element;
    }

    public Reports clickOnReportsLink() throws Exception
    {
        Reports reports = null;
        try
        {
            link_Reports().click();
            reports = new Reports(driver);
            AutomationLog.info("Successfully clicked in Reports link in Profile Dropdown");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not click on Reports link in Profile Dropdown");
            throw(e);
        }
        return reports;
    }

    public WebElement link_profileNameDropdown() throws Exception
    {
        try
        {
            element = driver.findElement(By.className("profile-name"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not found Profile name dropdown");
            throw(e);
        }
        return element;
    }

    public Homepage logout() throws Exception
    {
    	Homepage homepage = null;
        try
        {
            header().link_profileNameDropdown().click();
            header().link_Logout().click();
            WaitFor.waitForPageToLoad(driver);
            homepage = new Homepage();
            AutomationLog.info("Successfully Logout from Property Page");
        }
        catch(Exception e)
        {
            AutomationLog.error("Failed to Logout from Property Page");
            throw(e);
        }
        return homepage;
    }

    public WebElement closeLoginPopUp() throws Exception
    {
        try
        {
            element = driver.findElement(By.xpath("//a[@role='button']"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Login Pop Up close button is not found");
            throw(e);
        }
        return element;
    }

    public void clickOnCloseLoginPopUp() throws Exception
    {
        try
        {
            closeLoginPopUp().click();
            AutomationLog.info("Successfully click on Login pop-up close icon");
        }
        catch(Exception e)
        {
            AutomationLog.error("Failed to click on Login pop-up close icon");
            throw(e);
        }
    }

    public WebElement btn_searchForm() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("searchFormButton"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Search from button is not found on header");
            throw(e);
        }
        return element;
    }

    public void clickOnSearchFormButton() throws Exception
    {
        try
        {
            btn_searchForm().click();
            AutomationLog.info("Successfully click on Search Form button");
        }
        catch(Exception e)
        {
            AutomationLog.error("Failed to click on Search Form button");
            throw(e);
        }
    }

    public WebElement msg_ZebraTooltip() throws Exception
    {
        try
        {
            element = driver.findElement(By.className("Zebra_Tooltip_Message"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Tool Tip Message is not found on Header after clicking on empty search button");
            throw(e);
        }
        return element;
    }

    public WebElement txtbx_SearchInput() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("searchInput"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Search Input textbox is not found");
            throw(e);
        }
        return element;
    }

    public void enterSearchTextInSearchInputTextBox(String data) throws Exception
    {
        try
        {
            txtbx_SearchInput().sendKeys(data);
            AutomationLog.info("Successfully enter data in SearchInput Textbox");
        }
        catch(Exception e)
        {
            AutomationLog.error("Failed to enter data in SearchInput Textbox");
            throw(e);
        }
    }

    public By getAutoCompleteMenuDropboxLoactor() throws Exception
    {
        return By.className("ui-autocomplete");
    }

    public WebElement dropbox_AutoCompleteMenu() throws Exception
    {
        try
        {
            element = driver.findElement(getAutoCompleteMenuDropboxLoactor());
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not found Autocomplete Menu dropbox");
            throw(e);
        }
        return element;
    }

    public void clickOnProfileNameDropdownArrow() throws Exception
    {
        try
        {
            link_profileNameDropdown().click();
            AutomationLog.info("Successfully clicked on Profile name dropdown Arrow");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not found Profile name dropdown Arrow");
            throw(e);
        }
    }
}
