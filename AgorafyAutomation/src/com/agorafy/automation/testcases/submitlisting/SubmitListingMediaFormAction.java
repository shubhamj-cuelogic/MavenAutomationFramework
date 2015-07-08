package com.agorafy.automation.testcases.submitlisting;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.automationframework.Configuration;
import com.agorafy.automation.automationframework.WaitFor;
import com.agorafy.automation.pageobjects.Page;
import com.agorafy.automation.pageobjects.submitlisting.SubmitListingContactsFormPage;
import com.agorafy.automation.pageobjects.submitlisting.SubmitListingDetailsFormBasePage;
import com.agorafy.automation.pageobjects.submitlisting.SubmitListingMediaFormPage;

public class SubmitListingMediaFormAction extends SubmitListingBaseAction
{
    private SubmitListingMediaFormPage media = new SubmitListingMediaFormPage(Page.driver);
    private SubmitListingDetailsFormBasePage details ;
    private SubmitListingDetailsFormRetailAction detailsRetailAction = new SubmitListingDetailsFormRetailAction();
    private SubmitListingContactsFormPage contacts = null;
    public String exefilepath = System.getProperty("user.dir") + Configuration.getConfigurationValueForProperty("image-path-to-upload");
    HashMap<String, String> mediadata; 

    public SubmitListingMediaFormAction()
    {
        super();
    }

    @Override
    public void setup()
    {
        super.setup();

        try
        {
            media = detailsRetailAction.fillDetailsFormAndMoveToMediaForm(testCaseData);
            mediadata = testCaseData.get("MediaFormData");
            AutomationLog.info("Successfully reached to Media form");
        }
        catch(Exception e)
        {
            AutomationLog.error("Failed to reach Media form");
        }
    }

    @Override
    protected void verifyTestCases() throws Exception 
    {
        String highResImagePath = exefilepath + mediadata.get("highResImage");
        String lowResImagePath = exefilepath + mediadata.get("lowResImage");
        String pdfFilePath = exefilepath + mediadata.get("pdfupload");

        verifyIfClickedOnAddfilesButton(highResImagePath); 
        verifyIfClickedOnCancelButtonForAddedImage();
        verifyIfClickedOnStartUpload(highResImagePath);
        verifyIfClickedOnDeleteButtonOnUploadedImage();
        verifyIfOnlyPDFIsUploaded(pdfFilePath);
        verifyIfPdfIsUploadedWithHighResolutionImage(highResImagePath);
        HashMap<String, String> upload = testCaseData.get("MultipleImageUpload");
        verifyIfmultipleImageFilesCanBeUploaded(upload);
        verifyIfLowResolutionImageCantBeUploaded(lowResImagePath);
        verifyIfClickedOnSaveAndContinueWithNoImage();
        verifyIfClickedOnBackButton();
        verifyIfClickedOnSaveAndContinueAfterValidImageUpload();
    }

    public void verifyIfClickedOnAddfilesButton(String highResImagePath) throws Exception
    {
        try
        {
            media.btn_AddFiles().sendKeys(highResImagePath);
            WaitFor.presenceOfTheElement(Page.driver, media.listingImageLocator()); 
            Assert.assertEquals(media.img_Listing().isDisplayed(), true, "Expected AddFiles button does not add image for upload");
            AutomationLog.info("Clicking AddFiles adds image to be uploaded successfully");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not open windows file upload");
            throw(e);
        }
    }

    public void verifyIfClickedOnCancelButtonForAddedImage() throws Exception
    {
        try
        {
            media.clickOnCancelBbutton();
            Thread.sleep(1000);
            Assert.assertEquals(media.template_Upload(), false, "Expected Cancel button does not remove image ");
            AutomationLog.info("Clicking cancel removes image to be uploaded ");
        }
        catch(Exception e)
        {
            AutomationLog.error("Could not open windows file upload");
            throw(e);
        }
    }

    public void verifyIfClickedOnStartUpload(String highResImagePath) throws Exception
    {
        verifyIfClickedOnAddfilesButton(highResImagePath); 
        media.clickOnStartUploadButton();
        WaitFor.ElementToBeDisplayed(Page.driver, media.uploadImagelocator());
        Assert.assertEquals(media.img_Uploaded().isDisplayed(), true, "Expected image is not uploaded");
        AutomationLog.info("Image Uploaded successfully");
    }

    public void verifyIfClickedOnDeleteButtonOnUploadedImage() throws Exception
    {
        WaitFor.ElementToBeDisplayed(Page.driver, media.uploadImagelocator());
        media.clickOnDeleteButton();
        Thread.sleep(2000);
        Assert.assertEquals(media.template_Upload(), false, "Expected image is not removed ");
        AutomationLog.info("Clicking delete button removes uploaded image");
    }

    public void verifyIfLowResolutionImageCantBeUploaded(String lowResImagePath) throws Exception
    {
        media.btn_AddFiles().sendKeys(lowResImagePath);
        WaitFor.presenceOfTheElement(Page.driver, media.listingImageLocator()); 
        media.clickOnStartUploadButton();
        Thread.sleep(10000);
        Assert.assertEquals(media.template_Upload(), false, "Low resolution image can be uploaded");
        AutomationLog.info("Low resolution image cant be uploaded");
    }

    public void verifyIfClickedOnSaveAndContinueAfterValidImageUpload() throws Exception
    {
        contacts = moveToContactsForm(testCaseData);
        Assert.assertEquals(contacts.form_Contacts().isDisplayed(), true, "Expected contacts form is not shown");
        AutomationLog.info("Contacts form is shown after clicking save and continue button with valid media form  input ");
    }

    public void verifyIfClickedOnSaveAndContinueWithNoImage() throws Exception
    {
        media.clickOnSaveAndContinueButton();
        Page.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String msg = media.msg_MediaError().getText();
        Assert.assertEquals(msg, "Minimum one image file is required", "Expected Media Error Message is not Shown");
        AutomationLog.info("Media Error message is shown if clicked on save without uploading an Image");
    }

    public void verifyIfOnlyPDFIsUploaded(String pdfFilePath) throws Exception
    {
        media.btn_AddFiles().sendKeys(pdfFilePath);
        WaitFor.sleepFor(10000);
        media.clickOnStartUploadButton();
        Thread.sleep(10000);
        media.clickOnSaveAndContinueButton();
        String msg = media.msg_MediaError().getText();
        Assert.assertEquals(msg, "Minimum one image file is required", "Expected Media Error Message is not Shown");
        AutomationLog.info("Low resolution image cant be uploaded");
    }

    public void verifyIfPdfIsUploadedWithHighResolutionImage(String highResImagePath) throws Exception 
    {
        media.btn_AddFiles().sendKeys(highResImagePath);
        WaitFor.sleepFor(10000);
        media.clickOnStartUploadButton();
        WaitFor.sleepFor(10000);
        contacts = media.clickOnSaveAndContinueButton();
        WaitFor.sleepFor(2000);
        Assert.assertTrue(contacts.form_Contacts().isDisplayed(), "Expected contacts form is not displayed");
        AutomationLog.info("Adding Image file uploads pdf file successfully");
        contacts.clickOnBackButton();
        media.deleteUploadedfiles();
        WaitFor.sleepFor(10000);
    }

    public void verifyIfmultipleImageFilesCanBeUploaded(HashMap<String, String> upload) throws Exception 
    {
        int count = 3;
        for(int i=1;i<=count;i++)
        {
            String filename ="image"+i;
            String uploadfile = exefilepath + upload.get(filename);
            media.btn_AddFiles().sendKeys(uploadfile);
            WaitFor.sleepFor(10000);
        }
        media.clickOnStartUploadButton();
        WaitFor.sleepFor(10000);
        Assert.assertEquals(media.getCountOfUploadedFiles(), count, "Expected image files are not uploaded");
        AutomationLog.info("Multiple iamge files uploaded successfully");
        media.deleteUploadedfiles();
    }

    public SubmitListingContactsFormPage moveToContactsForm(HashMap<String, HashMap<String, String>> data) throws Exception
   {
       SubmitListingContactsFormPage contact = null;
       mediadata = data.get("MediaFormData");
       String highResFile = exefilepath + mediadata.get("highResImage");
       try
       {
           if(!(media.form_Media().isDisplayed()))
           {
               detailsRetailAction.fillDetailsFormAndMoveToMediaForm(data);
           }
           media.btn_AddFiles().sendKeys(highResFile);
           WaitFor.presenceOfTheElement(Page.driver, media.listingImageLocator()); 
           media.clickOnStartUploadButton();
           WaitFor.presenceOfTheElement(Page.driver, media.uploadImagelocator());
           media.clickOnSaveAndContinueButton();
           WaitFor.sleepFor(5000);
           Page.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
           contact = new SubmitListingContactsFormPage(Page.driver);
       }
       catch(Exception e)
       {
           AutomationLog.error("Could not go to Contacts form ");
           throw(e);
       }
       return contact;
       
   }

    public void verifyIfClickedOnBackButton() throws Exception
    {
        details = media.clickOnBackButton();
        Page.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(details.form_Property().isDisplayed(), true, "Expected form is not shown");
        AutomationLog.info("Clicking back button on media form redirects details page");
        details.clickSaveAndContinue();
    }

    @Override
    protected String successMessage() {
        return "Test cases passed for SubmitListing Media form";
    }

    @Override
    protected String failureMessage() {
        return "Test cases failed for SubmitListing Media form";
    }

}
