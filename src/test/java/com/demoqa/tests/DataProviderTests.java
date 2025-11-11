package com.demoqa.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTests extends BaseTest {

    @DataProvider(name = "userCredentials")
    public Object[][] provideLoginData() {
        return new Object[][]{
                {"admin", "admin123"},
                {"tester", "test@123"},
                {"user", "pass123"}
        };
    }

    @Test(dataProvider = "userCredentials")
    public void verifyLogin(String username, String password) {
        System.out.println("🔹 Trying login with: " + username + " / " + password);

        // Example assertion logic (dummy)
        boolean valid = username.equals("admin") && password.equals("admin123");
        if (valid) {
            Assert.assertTrue(true, "✅ Login successful for " + username);
        } else {
            Assert.assertTrue(false, "❌ Invalid credentials: " + username);
        }
    }
}
