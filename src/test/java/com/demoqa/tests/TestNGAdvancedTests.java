package com.demoqa.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGAdvancedTests {

    // 🔹 Parameter from testng.xml
    @Test
    @Parameters("browser")
    public void verifyBrowserParameter(@Optional("chrome") String browser) {
        System.out.println("Running test on browser: " + browser);
        Assert.assertNotNull(browser);
    }

    // 🔹 Groups example
    @Test(groups = {"smoke"})
    public void smokeTest() {
        System.out.println("Smoke test executed!");
    }

    @Test(groups = {"regression"})
    public void regressionTest() {
        System.out.println("Regression test executed!");
    }

    // 🔹 DataProvider example
    @DataProvider(name = "loginData")
    public Object[][] provideLoginData() {
        return new Object[][]{
                {"admin", "admin123"},
                {"sachin", "test123"},
                {"guest", "guest@123"}
        };
    }

    @Test(dataProvider = "loginData")
    public void verifyLoginData(String username, String password) {
        System.out.println("Testing login → Username: " + username + " | Password: " + password);
        Assert.assertTrue(username.length() > 0);
    }

    // 🔹 Dependence example
    @Test(dependsOnMethods = "smokeTest")
    public void dependentTest() {
        System.out.println("This runs only if Smoke test passes.");
    }
}
