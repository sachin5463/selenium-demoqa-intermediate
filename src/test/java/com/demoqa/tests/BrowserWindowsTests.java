package com.demoqa.tests;

import com.demoqa.pages.BrowserWindowsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserWindowsTests extends BaseTest {

    @Test(groups = {"windows", "smoke"})
    public void verifyNewTabHeading() {
        // ✅ using correct demoqa URL (tabButton exists here)
        driver.get("https://demoqa.com/browser-windows");

        BrowserWindowsPage page = new BrowserWindowsPage(driver, timeout);
        String heading = page.openNewTabAndGetHeading();

        System.out.println("🔹 New tab heading text: " + heading);

        Assert.assertTrue(
                heading.toLowerCase().contains("sample"),
                "❌ Expected heading not found! Actual: " + heading
        );
    }
}
