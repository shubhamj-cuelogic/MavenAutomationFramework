package com.agorafy.automation.testcases.upsellpopups;

import java.util.HashMap;
import java.util.Set;

import org.testng.Assert;

import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.automationframework.AutomationTestCaseVerification;
import com.agorafy.automation.automationframework.Credentials;
import com.agorafy.automation.automationframework.WaitFor;
import com.agorafy.automation.pageobjects.Homepage;
import com.agorafy.automation.pageobjects.Page;
import com.agorafy.automation.pageobjects.SearchResultsPage;
import com.agorafy.automation.pageobjects.upsellpopups.ListingDetailPage;
import com.agorafy.automation.pageobjects.upsellpopups.LoginPopUp;

/**
 * Verify whether login pop up is seen when 'Subscribe to Listing' link is clicked in Logged out state
 * Verify whether login pop up is seen when 'Send Email' link provided below Agent name is clicked in Logged out state
 * Verify whether login pop up is seen when 'Add to Report' link is clicked in Logged out state
 */

public class ListingDetailPageAction extends AutomationTestCaseVerification 
{
    private ListingDetailPage listingDetailPage = null;
    private SearchResultsPage searchResultsPage = null;
    private LoginPopUp loginpopup = null;

    public ListingDetailPageAction()
    {
        super();
    }

    public void setup()
    {
        listingDetailPage = ListingDetailPage.listingDetailPage();
        try
        {
            super.setup();

            Homepage homepage = Homepage.homePage();
            HashMap<String, String> data = testCaseData.get("SearchData");
            searchResultsPage = homepage.populateSearchTermTextBox(data.get("borough"),data.get("listingcategory"),data.get("searchterm"));
            String handle = Page.driver.getWindowHandle();
            listingDetailPage = searchResultsPage.clickSearchResult();

            Set<String> numbers = Page.driver.getWindowHandles();
            numbers.remove(handle);
            String newHandle = numbers.iterator().next();
            Page.driver.switchTo().window(newHandle);
        }
        catch (Exception e)
        {
            AutomationLog.error("Could not Navigate to Listing Detail Page");
        }
    }

    @Override
    protected void verifyTestCases() throws Exception
    {
        Credentials validCredentials = userCredentials();
        verifySubscribeToListingLinkUpsell(validCredentials);

        preconditionForNextTest();

        verifySendEmailLinkUpsell(listingDetailPage,validCredentials);

        preconditionForNextTest();
        verifyAddToReportLinkUpsell(listingDetailPage,validCredentials);

        verifySessionExpireTestCases(validCredentials);
    }

    public void verifySubscribeToListingLinkUpsell(Credentials getValidCredentials) throws Exception
    {
        try
        {
            boolean loginStatus = false;
            loginpopup = (LoginPopUp) listingDetailPage.clickOnSubscribeToListingLink(loginStatus);
            Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on Subscribe to Listing");
            loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
            loginpopup.btn_LogIntoMyAccount().click();
            Assert.assertEquals(listingDetailPage.isUpdateListingLinkPresent(), true, "Login action was unsuccessful");
        }
        catch(Exception e)
        {
            AutomationLog.error("could not send credentials to login pop up of ListingDetailPage");
        }
    }

    public void verifySendEmailLinkUpsell(ListingDetailPage subscribeListingPopup, Credentials getValidCredentials)throws Exception
    {
        loginpopup = subscribeListingPopup.clickSendEmailLink();
        Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on send email");
        loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
        listingDetailPage = (ListingDetailPage) loginpopup.clickLoginButtonOnUpsell();
        Assert.assertEquals(listingDetailPage.isUpdateListingLinkPresent(), true, "Login action was unsuccessful");
        AutomationLog.info("Test for Send email link Upsell is successful");
    }

    public void verifyAddToReportLinkUpsell(ListingDetailPage subscribeListingPopup, Credentials getValidCredentials)throws Exception
    {
        boolean isLoggedIn = false;
        loginpopup = (LoginPopUp) subscribeListingPopup.clickOnAddToReportLink(isLoggedIn);
        Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on Add To Report");
        loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
        listingDetailPage = (ListingDetailPage) loginpopup.clickLoginButtonOnUpsell();
        Assert.assertEquals(listingDetailPage.isUpdateListingLinkPresent(), true, "Login action was unsuccessful");
        AutomationLog.info("Test for Add To Report link Upsell is successful");
    }

    public void preconditionForNextTest() throws Exception
    {
        String currentUrl = listingDetailPage.currentURL();
        Page.header().logout();
        Page.driver.get(currentUrl);
    }

    public void verifySessionExpireTestCases(Credentials getValidCredentials) throws Exception 
    {
        verifyIfClickedOnSubscribeToListingLinkAfterSessionExpire(getValidCredentials); 
        verifyIfClickedOnUnsubscribeToListingLinkAfterSessionExpire(getValidCredentials);
        verifyIfClickedOnUpdateListingLinkAfterSessionExpire(getValidCredentials);
        verifyIfClickedOnAddToReportLinkAfterSessionExpire(getValidCredentials);
        verifyIfClickedOnRemoveFromReportLinkAfterSessionExpire(getValidCredentials);
        verifyIfClickedOnSendEmailLinkAfteSessionExpire(getValidCredentials);
    }

    public void verifyIfClickedOnSubscribeToListingLinkAfterSessionExpire(Credentials getValidCredentials) throws Exception
    {
        Page.driver.manage().deleteAllCookies();
        listingDetailPage.link_SubscribeToListing().click();
        WaitFor.sleepFor(5000);
        Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on Add To Report");
        AutomationLog.info("Clicking SubscribeToListing after session expire shows login PopUp");
        loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
        loginpopup.btn_LogIntoMyAccount().click();
    }

    public void verifyIfClickedOnUnsubscribeToListingLinkAfterSessionExpire(Credentials getValidCredentials) throws Exception 
    {
        boolean status = true;
        Page.driver.navigate().refresh();
        listingDetailPage.clickOnSubscribeToListingLink(status);
        Assert.assertTrue(listingDetailPage.link_SubscribeToListing().isDisplayed(), "Expected UnsubscribeToListing Link is not Shown after clicking SubscribeToListing Link");
        Page.driver.manage().deleteAllCookies();
        listingDetailPage.clickOnUnsubscribeListingLink();
        WaitFor.sleepFor(5000);
        Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on Unsubscribe To Listing Link");
        AutomationLog.info("Clicking UnsubscribeToListing link after session expire shows login PopUp");
        loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
        loginpopup.btn_LogIntoMyAccount().click();
    }

    public void verifyIfClickedOnUpdateListingLinkAfterSessionExpire(Credentials getValidCredentials) throws Exception
    {
        Page.driver.manage().deleteAllCookies();
        listingDetailPage.link_UpdateListing().click();
        WaitFor.sleepFor(5000);
        Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on Add To Report");
        AutomationLog.info("Clicking UpdateListing after session expire shows login PopUp");
        loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
        loginpopup.btn_LogIntoMyAccount().click();
    }

    public void verifyIfClickedOnAddToReportLinkAfterSessionExpire(Credentials getValidCredentials) throws Exception
    {
        Page.driver.manage().deleteAllCookies();
        listingDetailPage.link_addToReport().click();
        WaitFor.sleepFor(5000);
        Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on Add To Report");
        AutomationLog.info("Clicking AddToReport after session expire shows login PopUp");
        loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
        loginpopup.btn_LogIntoMyAccount().click();
    }

    public void verifyIfClickedOnRemoveFromReportLinkAfterSessionExpire(Credentials getValidCredentials) throws Exception 
    {
        boolean isLoggedIn = true;
        Page.driver.navigate().refresh();
        listingDetailPage.clickOnAddToReportLink(isLoggedIn);
        Assert.assertTrue(listingDetailPage.link_removeFromReport().isDisplayed(), "Expected RemoveFromReport link is not shown after clicking AddToReport link ");
        Page.driver.manage().deleteAllCookies();
        listingDetailPage.clickOnRemoveFromReportLink();
        WaitFor.sleepFor(5000);
        Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on Remove From Report Link");
        AutomationLog.info("Clicking RemoveFromReport link after session expire shows login PopUp");
        loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
        loginpopup.btn_LogIntoMyAccount().click();
    }

    public void verifyIfClickedOnSendEmailLinkAfteSessionExpire(Credentials getValidCredentials) throws Exception 
    {
        Page.driver.manage().deleteAllCookies();
        listingDetailPage.link_sendEmailContactSection().get(0).click();
        WaitFor.sleepFor(5000);
        Assert.assertEquals(loginpopup.checkingLogInPopUp(), true, "Login pop up is not seen after clicking on Add To Report");
        AutomationLog.info("Clicking AddToReport after session expire shows login PopUp");
        loginpopup.populateLoginPopUpData(getValidCredentials.getEmail(),getValidCredentials.getPassword());
        loginpopup.btn_LogIntoMyAccount().click();
    }

    @Override
    protected String successMessage()
     {
        return "Test for verifying login pop-ups on listing detail page has Passed";
     }

@Override
    protected String failureMessage()
    {
        return "Test for verifying login pop-ups on listing detail page has Failed";
    }
}
