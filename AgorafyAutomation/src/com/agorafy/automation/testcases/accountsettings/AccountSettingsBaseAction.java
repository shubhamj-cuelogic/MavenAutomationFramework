package com.agorafy.automation.testcases.accountsettings;


import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.automationframework.AutomationTestCaseVerification;
import com.agorafy.automation.automationframework.WaitFor;
import com.agorafy.automation.pageobjects.accountsettings.AccountSettings;
import com.agorafy.automation.pageobjects.Dashboard;
import com.agorafy.automation.pageobjects.Header;
import com.agorafy.automation.pageobjects.HeaderLoginForm;
import com.agorafy.automation.pageobjects.Homepage;
import com.agorafy.automation.pageobjects.Page;
import com.agorafy.automation.pageobjects.subnavigationmenu.SubNavigation;
import com.agorafy.automation.utilities.Login;

public abstract class AccountSettingsBaseAction extends AutomationTestCaseVerification
{
    protected Homepage homePage = null;
    protected HeaderLoginForm loginForm = null;
    protected Dashboard dashboard = null;
    protected Header header = null;
    protected AccountSettings accountSettings = null;
    protected SubNavigation subnavigation = null;;

    protected AccountSettingsBaseAction()
    {
        super(AccountSettingsBaseAction.class.getSimpleName());
    }

    @Override
    public void setup()
    {
        super.setup();
        try
        {
            homePage = Login.doSuccessfullLoginFromHeaderLoginForm();
            WaitFor.presenceOfTheElement(Page.driver, homePage.getHomepageGreetingsLocator());
            header = Header.header();
            subnavigation = Page.subNavigation();
            dashboard = subnavigation.clickLinkMyDashboard();
            accountSettings = dashboard.accountSettings();
        }
        catch (Exception e)
        {
             AutomationLog.error("Navigation to Account Settings Page failed");
        }
    }
}