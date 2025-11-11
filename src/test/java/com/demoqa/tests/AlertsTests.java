package com.demoqa.tests;

import com.demoqa.pages.AlertsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertsTests extends BaseTest {

    @Test(groups = {"alerts"})
    public void verifySimpleAlert() {
        driver.get("https://demoqa.com/alerts");
        AlertsPage alerts = new AlertsPage(driver, timeout);
        String msg = alerts.handleSimpleAlert();
        Assert.assertTrue(msg.toLowerCase().contains("you clicked"),
                "❌ Simple alert text mismatch → " + msg);
    }

    @Test(groups = {"alerts"})
    public void verifyConfirmAlertAccept() {
        driver.get("https://demoqa.com/alerts");
        AlertsPage alerts = new AlertsPage(driver, timeout);
        String result = alerts.handleConfirmAlert(true);
        Assert.assertTrue(result.toLowerCase().contains("ok"),
                "❌ Confirm alert result mismatch → " + result);
    }

    @Test(groups = {"alerts"})
    public void verifyPromptAlert() {
        driver.get("https://demoqa.com/alerts");
        AlertsPage alerts = new AlertsPage(driver, timeout);
        String result = alerts.handlePromptAlert("Sachin");
        Assert.assertTrue(result.toLowerCase().contains("sachin"),
                "❌ Prompt alert result mismatch → " + result);
    }
}
