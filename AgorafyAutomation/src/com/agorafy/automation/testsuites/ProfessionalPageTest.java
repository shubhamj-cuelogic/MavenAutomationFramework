package com.agorafy.automation.testsuites;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.agorafy.automation.automationframework.AutomationFramework;
import com.agorafy.automation.testcases.upsellpopups.ProfessionalProfileLoginUpsellAction;

public class ProfessionalPageTest {

    @BeforeSuite
    public void Init()
    {
        String globalConfigureationFileWithPath = "src/com/agorafy/automation/configuration/config.properties";
        AutomationFramework.initWithGlobalConfiguration(globalConfigureationFileWithPath);
    }

@Test
    public void testProfessionalProfilePageTestCase() throws Exception
    {
        try
        {
            new ProfessionalProfileLoginUpsellAction().Execute();
        }
        catch (Exception e)
        {
            throw (e);
        }
    }
}
