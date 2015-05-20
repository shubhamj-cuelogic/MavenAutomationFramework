package com.agorafy.automation.pageobjects.subnavigationmenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.automationframework.WaitFor;
import com.agorafy.automation.pageobjects.Page;
import com.agorafy.automation.pageobjects.updatelisting.*;
import com.agorafy.automation.pageobjects.submitlisting.SubmitListingMediaFormPage;
import com.agorafy.automation.pageobjects.showings.FrontEndShowings;
import com.agorafy.automation.pageobjects.upsellpopups.ListingDetailPage;

public class MyListings extends Page 
{
    private WebElement element;
    private int count;

    public MyListings(WebDriver driver) 
    {
        super(driver);
    }	

    public void setCount(int cnt)
    {
        count = cnt;
    }

    public int getCount()
    {
        return count;
    }

    public WebElement MyListingsContentBlock() throws Exception
    {
        try 
        {
            element = driver.findElement(By.className("tabContainer"));
            AutomationLog.info("My listings content block found");
        }
        catch (Exception e) 
        {
            AutomationLog.error("failed to find my listings content block");
            throw (e);
        }
        return element;
    }

    public WebElement link_OnMarket() throws Exception
    {
        try 
        {
            element = MyListingsContentBlock().findElement(By.linkText("On-market")); 
            AutomationLog.info("My listings On-market link found");
        }
        catch (Exception e) 
        {
            AutomationLog.error("failed to find My listings On-market link");
            throw (e);
        }
        return element;
    }

    public WebElement link_OffMarket() throws Exception
    {
        try 
        {
            element = MyListingsContentBlock().findElement(By.linkText("Off-market")); 
            AutomationLog.info("My listings Off-market link found");
        }
        catch (Exception e) 
        {
            AutomationLog.error("failed to find My listings Off-market link");
            throw (e);
        }
        return element;
    }

    public WebElement element_PageHeading() throws Exception
    {
        WebElement parent;
        try 
        {
            parent = driver.findElements(By.className("content-block")).get(0);
            element = parent.findElement(By.tagName("h2"));
            AutomationLog.info("My listing heading found");
        }
        catch (Exception e) 
        {
            AutomationLog.error("failed to find My listings heading");
            throw (e);
        }
        return element;
    }

    public WebElement firstOnMarketListing() throws Exception
    {
        WebElement parent;
        WebElement child;
        try 
        {
           //parent = driver.findElement(By.id("DataTables_Table_0")).findElement(By.tagName("tbody"));
           parent = getVisibleElement(By.className("dataTable")).findElement(By.tagName("tbody"));
           child = parent.findElements(By.tagName("tr")).get(0);
           element = child.findElements(By.tagName("td")).get(0);
           AutomationLog.info("first On Market Listing found");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to find first On Market Listing");
            throw (e);
        }
        return element;
    }

    public String txt_FirstListing() throws Exception
    {
        String firstListing = null;
        try
        {
           element = firstOnMarketListing().findElement(By.className("listing-details")).findElement(By.tagName("h4"));
           firstListing = element.getText();
           AutomationLog.info("Sucessfully found txt of first listing");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to found txt of first listing");
            throw (e);
        }
        return firstListing;
    }

    public WebElement hoverOverFirstListing() throws Exception
    {
        try
        {
           
           Actions actions = new Actions(driver);
           element = firstOnMarketListing();
           actions.moveToElement(element).build().perform();;
           AutomationLog.info("Sucessfully performed mouse hovering over first listing");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to perform mouse hovering over first listing");
            throw (e);
        }
        return element;
    }

    public WebElement hoverOverUpdate() throws Exception
    {
        try
        {
           Actions actions = new Actions(driver);
           element = updateListing();
           WaitFor.sleepFor(2000);
           actions.moveToElement(element).build().perform();
           AutomationLog.info("Sucessfully performed mouse hovering over update");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to perform mouse hovering over update");
            throw (e);
        }
        return element;
    }

    public WebElement hoverOverReportLeased() throws Exception
    {
        try
        {
           Actions actions = new Actions(driver);
           element = reportLeased();
           WaitFor.sleepFor(2000);
           actions.moveToElement(element).build().perform();
           AutomationLog.info("Sucessfully performed mouse hovering over Report Leased");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to perform mouse hovering over Report Leased");
            throw (e);
        }
        return element;
    }

    public WebElement updateListing() throws Exception
    {
        WebElement parent;
        try
        {
           parent = firstOnMarketListing().findElement(By.className("listing-details")).findElement(By.className("listing-actions"));
           element = parent.findElements(By.tagName("a")).get(0);
           AutomationLog.info("Sucessfully found update link on first listing");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to found update link on first listing");
            throw (e);
        }
        return element;
    }

    public WebElement reportLeased() throws Exception
    {
        WebElement parent;
        try
        {
           parent = firstOnMarketListing().findElement(By.className("listing-details")).findElement(By.className("listing-actions"));
           element = parent.findElement(By.className("report-leased"));
           AutomationLog.info("Sucessfully found Report Leased link on first listing");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to found Report Leased link on first listing");
            throw (e);
        }
        return element;
    }

    public WebElement renew() throws Exception
    {
        WebElement parent;
        try
        {
           parent = firstOnMarketListing().findElement(By.className("listing-details")).findElement(By.className("listing-actions"));
           element = parent.findElement(By.className("renew"));
           AutomationLog.info("Sucessfully found Renew link on first listing");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to found Renew link on first listing");
            throw (e);
        }
        return element;
    }

    public WebElement addMedia() throws Exception
    {
        WebElement parent;
        try
        {
           parent = firstOnMarketListing().findElement(By.className("listing-details")).findElement(By.className("listing-actions"));
           element = parent.findElement(By.className("add-media"));
           AutomationLog.info("Sucessfully found Add Media link on first listing");
        }
        catch (Exception e)
        {
            AutomationLog.error("failed to found Add Media link on first listing");
            throw (e);
        }
        return element;
    }

    public AvailabilityAndDetailsForm clickUpdateOfFirstListing() 
    {
        AvailabilityAndDetailsForm updateListingPage = null;
        try 
        {
             Actions actions = new Actions(driver);
             actions.moveToElement(updateListing()).click().build().perform();
             updateListingPage = new AvailabilityAndDetailsForm(driver);
             AutomationLog.info("Sucessfully clicked update link found after hovering over first listing");
        }
        catch (Exception e) 
        {
            AutomationLog.error("Failed to click update link found after hovering over first listing");
        }
        return updateListingPage;
    }

    public SubmitListingMediaFormPage clickAddMediaOfFirstListing() 
    {
        SubmitListingMediaFormPage mediaPage = null;
        try 
        {
             Actions actions = new Actions(driver);
             actions.moveToElement(addMedia()).click().build().perform();
             mediaPage = new SubmitListingMediaFormPage(driver);
             AutomationLog.info("Sucessfully clicked Add Media link found after hovering over first listing");
        }
        catch (Exception e) 
        {
            AutomationLog.error("Failed to click Add Media link found after hovering over first listing");
        }
        return mediaPage;
    }

    public List<WebElement> allListingElements() 
    {
        List<WebElement> allListingElements = new ArrayList<>();
        WebElement parent;
        try 
        {
            parent = driver.findElement(By.id("DataTables_Table_0")).findElement(By.tagName("tbody"));
            allListingElements = parent.findElements(By.tagName("tr"));
            AutomationLog.info("All listing elements are located successfully");
        }
        catch (Exception e) 
        {
            AutomationLog.error("Failed to locate all listing elements");
        }
        return allListingElements;
    }

    public HashMap<String, String> allListingTypes() 
    {
        HashMap<String, String> allListingTypes = new HashMap<>();
        List<WebElement> allListingElements = new ArrayList<>();
        WebElement parent;
        try 
        {
            allListingElements = allListingElements();
            String listingName = null;
            String listingType = null;
            for(WebElement singleListing : allListingElements)
            {
                 parent = singleListing.findElements(By.tagName("td")).get(0);
                 element = parent.findElements(By.tagName("div")).get(1);
                 listingName = element.findElement(By.tagName("h4")).findElement(By.tagName("a")).getText();
                 element = singleListing.findElements(By.tagName("td")).get(1);
                 listingType = element.findElement(By.tagName("div")).getText();
                 allListingTypes.put(listingType,listingName);
            }
            AutomationLog.info("Successfully got all listing types on My Listings page");
        }
        catch (Exception e) 
        {
            AutomationLog.error("Failed to get all listing types on My Listings page");
        }
        return allListingTypes;
    }

    public void clickThisListingsUpdate(WebElement singleListing) 
    {
        WebElement parent;
        WebElement update;
        Actions hover = new Actions(driver);
        try 
        {
            parent = singleListing.findElements(By.tagName("td")).get(0);
            element = parent.findElements(By.tagName("div")).get(1);
            update = element.findElement(By.id("update"));
            
            
            int elementLocation =  singleListing.getLocation().getX();
            scrollPage(0, (elementLocation+230) );
            
            
            /*JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", singleListing);*/
            
            hover.moveToElement(singleListing);
            hover.moveToElement(update).click().build().perform();
            AutomationLog.info("Successfully clicked single listings update");
        }
        catch (Exception e) 
        {
            AutomationLog.error("Failed to click single listings update");
        }
    }

    public String getSingleListingName(WebElement singleListing) 
    {
        WebElement parent;
        WebElement name;
        String listingName = null;
        try 
        {
            parent = singleListing.findElements(By.tagName("td")).get(0);
            element = parent.findElements(By.tagName("div")).get(1);
            name = element.findElement(By.tagName("h4"));
            listingName = name.findElement(By.tagName("a")).getText();
            AutomationLog.info("Successfully got selected listings name");
        }
        catch (Exception e) 
        {
            AutomationLog.error("Failed to get selected listings name");
        }
        return listingName;
    }

    public AvailabilityAndDetailsForm selectRequiredListingsUpdate(String expectedListingName)
    {
        List<WebElement> allListingElements = new ArrayList<>();
        String actualListingName;
        AvailabilityAndDetailsForm updateListingPage = null;
        try
        {
            allListingElements = allListingElements();
            for(WebElement singleListing : allListingElements)
            {
                actualListingName = getSingleListingName(singleListing);
                if(actualListingName.equals(expectedListingName))
                {
                    clickThisListingsUpdate(singleListing);
                    updateListingPage = new AvailabilityAndDetailsForm(driver);
                    break;
                }
            }
            AutomationLog.info("Successfully selected required listing depending upon listing type");
        }
        catch (Exception e) 
        {
            AutomationLog.error("Failed to select required listing depending upon listing type");
        }
        return updateListingPage;
    }

    public String getApplicationUrl()
    {
        return applicationUrl();
    }

    public String pageHeading() throws Exception
    {
        return element_PageHeading().getText();
    }

    //Showings on my listings
    public WebElement listingsContainer() throws Exception
    {
        try
        {
            element = driver.findElement(By.id("DataTables_Table_0")).findElement(By.tagName("tbody"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Couid not find listing container");
            throw(e);
        }
        return element;
    }

    public WebElement link_ListingName(String listingName) throws Exception 
    {
        WebElement listingLink = null;
        List<WebElement> list = new ArrayList<WebElement>();
        List<WebElement> list1 = new ArrayList<WebElement>();
        list = listingsContainer().findElements(By.tagName("tr"));
        for(WebElement opt : list)
        {
            list1 = opt.findElements(By.tagName("td"));
            element = list1.get(0).findElement(By.className("listing-details")).findElement(By.tagName("a"));
            if(element.getText().equals(listingName))
            {
                listingLink = element;
                break;
            }
        }
        return listingLink;
    }

    public ListingDetailPage clickOnListingNameLink(String listingName) throws Exception
    {
        ListingDetailPage listingdetail = null;
        try
        {
            link_ListingName(listingName).click();
            listingdetail = new ListingDetailPage(driver);
            AutomationLog.info("Successfully clicked on ListingName link");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not click on listing name link");
            throw(e);
        }
        return listingdetail;
    }

    public WebElement getListingName(String listingName) throws Exception
    {
        int cnt = -1;
        List<WebElement> list = new ArrayList<WebElement>();
        List<WebElement> list1 = new ArrayList<WebElement>();
        WebElement listingrow = null;
        list = listingsContainer().findElements(By.tagName("tr"));
        for(WebElement opt : list)
        {
            cnt++;
            list1 = opt.findElements(By.tagName("td"));
            element = list1.get(0).findElement(By.className("listing-details")).findElement(By.tagName("a"));
            if(element.getText().equals(listingName))
            {
                listingrow = opt;
                break;
            }
        }
        setCount(cnt);
        return listingrow;
    }

    public WebElement div_showing(String listingName) throws Exception 
    {
        try
        {
            element = getListingName(listingName).findElements(By.tagName("td")).get(2);
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not find Showing div");
            throw(e);
        }
        return element;
    }

    public WebElement link_ScheduleNow(String listingName) throws Exception
    {
        try
        {
            element = div_showing(listingName).findElement(By.tagName("a"));
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not find Schedule Now link");
            throw(e);
        }
        return element;
    }

    public WebElement txt_UpcomingShowings(String listingName) throws Exception 
    {
        try
        {
            element = div_showing(listingName).findElement(By.tagName("span"));
        }
        catch(Exception e)
        {
            AutomationLog.error("could not find Upcoming showings text");
            throw(e);
        }
        return element;
    }

    public FrontEndShowings clickOnScheduleNowLink(String listingName) throws Exception 
    {
        FrontEndShowings frontendshowing = null;
        try
        {
            link_ScheduleNow(listingName).click();
            WaitFor.sleepFor(2000);
            frontendshowing = new FrontEndShowings(driver);
            AutomationLog.info("Successfully clicked on Showings link");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not click on Showings link");
            throw(e);
        }
        return frontendshowing;
    }

    public void clickOnUpcomingShowingsLink() throws Exception
    {
        element = listingsContainer().findElements(By.tagName("tr")).get(getCount());
        WebElement showing = element.findElements(By.tagName("td")).get(2).findElement(By.tagName("a"));
        showing.click();
        AutomationLog.info("Succesfully clicked on upcoming showings link");
    }

}
