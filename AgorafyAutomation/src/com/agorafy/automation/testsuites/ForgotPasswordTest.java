package com.agorafy.automation.testsuites;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.agorafy.automation.automationframework.Init;
import com.agorafy.automation.testcases.ForgotPasswordAction;

public class ForgotPasswordTest
{
    @BeforeSuite
    public void Init()
    {
        Init.globalConfiguration();
    }

    @Test
    public void testForgotPasswordActionTestCases() throws Exception
    {
        try
        {
            new ForgotPasswordAction().Execute();
        }
        catch (Exception e) 
        {
            throw (e);
        }
    }
}