package com.agorafy.automation.testsuites;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.agorafy.automation.automationframework.AutomationFramework;
import com.agorafy.automation.automationframework.AutomationLog;
import com.agorafy.automation.testcases.ChangePasswordNegativeAction;
import com.agorafy.automation.testcases.ChangePasswordPositiveAction;

public class ChangePasswordTest 
{
    @BeforeSuite
    public void Init()
    {
        // TODO: Move this to some TestNg XML configuration file, so that we can set config file path
        // when running in headless mode.
        String globalConfigureationFileWithPath = "src/com/agorafy/automation/configuration/config.properties";
        AutomationFramework.initWithGlobalConfiguration(globalConfigureationFileWithPath);
    }

    @Test
    public void ChangePasswordNegativeActionTest() throws Exception 
    {
        try
        {
           new ChangePasswordNegativeAction().Execute();
        }
        catch (Exception e) 
        {
           AutomationLog.error(e.getMessage());
           throw (e);
        }
    }
    
    @Test
    public void ChangePasswordPositiveActionTest () throws Exception
    {
        try
        {
            new ChangePasswordPositiveAction().Execute();
        }
        catch (Exception e)
        {
            AutomationLog.error(e.getMessage());
            throw (e);
        }
    }
}