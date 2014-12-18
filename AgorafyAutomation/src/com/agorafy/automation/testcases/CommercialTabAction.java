package com.agorafy.automation.testcases;

import java.util.HashMap;

import org.testng.Assert;

import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.pageobjects.CommercialTab;
import com.agorafy.automation.pageobjects.Page;

/**
 * To Test Commercial Tab on Edit/View profile  Page
 * Precondition : Page redirected to Edit/View profile  Page
 * Commercial Tab is present
 * verify Exclusive Tenant representation text area takes only 1000 charactes
 * verify Less character input user can add upto 1000 characters 
 * verify Characters remaining increases or decreases as character are added or removed from Exclusive Tenant representation text area 
 */
public class CommercialTabAction extends OverviewTabAction
{
    private CommercialTab commercial;

    public CommercialTabAction()
    {
        super();
    }

    @Override
    public void setup()
    {
        try
        {
            commercial = new CommercialTab(Page.driver);
            super.setup();
            commercial.clickOnCommercialTab();
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    protected void verifyTestCases() throws Exception
    {
        HashMap<String, String> tenantText = testCaseData.get("ExclusiveTenantText");
        verifyIfMoreThanThousandCharactersCanbeAddedInExclusiveTenantTextBox(commercial,tenantText);
        verifyIfLessThanThousandCharactesInExclusiveTenantTextBoxAddsRemainingCharacters(commercial,tenantText);
        verifyIfAddOrRemoveCharacterFromExclusiveTenantRepresentationTextBoxChangesCharactersRemaining(commercial,tenantText);
        verifyIfCharacterRemainingShowsSameNoOfRemainingCharactersAfterSave(commercial,tenantText);
    }

    public void verifyIfMoreThanThousandCharactersCanbeAddedInExclusiveTenantTextBox(CommercialTab commercial,HashMap<String, String> tenantText) throws Exception
    {
        commercial.txtbx_ExclusiveTenantRepresentation().clear();
        commercial.enterTextInExclusiveTenantRepresentationTextBox(tenantText.get("text"));
        String str2 = commercial.txtbx_ExclusiveTenantRepresentation().getAttribute("value");
        String ch = "x";
        commercial.enterTextInExclusiveTenantRepresentationTextBox(ch);
        String str = commercial.txtbx_ExclusiveTenantRepresentation().getAttribute("value");
        String str1 = str2 + ch;
        Assert.assertEquals(str.equalsIgnoreCase(str1), false, "More than Thousand characters can be entered in Exclusive tenant Representation text box ");
        AutomationLog.info("Could not enter more than thousand characters in Exclusive tenant representation textbox is Successfull ");
        
    }

    public void verifyIfLessThanThousandCharactesInExclusiveTenantTextBoxAddsRemainingCharacters(CommercialTab commercial,HashMap<String, String> tenantText) throws Exception
    {
        commercial.txtbx_ExclusiveTenantRepresentation().clear();
        commercial.enterTextInExclusiveTenantRepresentationTextBox(tenantText.get("text").substring(0, 990));
        String charsRemaining = commercial.text_CharactersRemaining().getText();
        Assert.assertEquals(charsRemaining, "10", "Expected characters remaining is wrong");
        AutomationLog.info("Check for If less than thousand charactes entered, shows no of characters remaining is Successfull");
        commercial.enterTextInExclusiveTenantRepresentationTextBox(tenantText.get("text").substring(990, 1000));
        charsRemaining = commercial.text_CharactersRemaining().getText();
        Assert.assertEquals(charsRemaining, "0", "Expected characters remaining is wrong");
        AutomationLog.info("Check for If no of characters remaining accepts same no of characters  is Successfull");

    }

    public void verifyIfAddOrRemoveCharacterFromExclusiveTenantRepresentationTextBoxChangesCharactersRemaining(CommercialTab commercial,HashMap<String, String> tenantText) throws Exception
    {
    	commercial.txtbx_ExclusiveTenantRepresentation().clear();
        commercial.enterTextInExclusiveTenantRepresentationTextBox(tenantText.get("text").substring(0,100));
        String charsRemaining = commercial.text_CharactersRemaining().getText();
        Assert.assertEquals(charsRemaining, "900", "Expected characters remaining decrease is failed");
        AutomationLog.info("Check for If adding charactes decrease no of characters remaining is Successfull");
        commercial.RemoveCharactersFromExclusiveTenantRepresentationTextBox(10);
        charsRemaining = commercial.text_CharactersRemaining().getText();
        Assert.assertEquals(charsRemaining, "910", "Expected characters remaining increase is failed");
        AutomationLog.info("Check for If removing charactes increase no of characters remaining is Successfull");
        
        
    }

    public void verifyIfCharacterRemainingShowsSameNoOfRemainingCharactersAfterSave(CommercialTab commercial,HashMap<String, String> tenantText) throws Exception
    {
    	commercial.txtbx_ExclusiveTenantRepresentation().clear();
        commercial.enterTextInExclusiveTenantRepresentationTextBox(tenantText.get("text").substring(0,100));
        String charsRemaining = commercial.text_CharactersRemaining().getText();
        commercial.clickOnSaveButton();
        String charRemainingAfterSave = commercial.text_CharactersRemaining().getText();
        Assert.assertEquals(charsRemaining.equalsIgnoreCase(charRemainingAfterSave), true, "Characters remaining After save is not same ");
        AutomationLog.info("Characters remaining is same after save for Exclusive Tenant representation is Successfull");
    }
}
