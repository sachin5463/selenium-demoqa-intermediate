package com.demoqa.tests;

import com.demoqa.utils.JsonUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JsonDataProviderTests extends BaseTest {

    @BeforeClass
    public void check() {
        System.out.println("✅ JsonDataProviderTests initialized successfully!");
    }

    @DataProvider(name = "jsonLoginData")
    public Object[][] getJsonData() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/LoginData.json";
        return JsonUtils.getJsonData(path);
    }

    @Test(dataProvider = "jsonLoginData")
    public void verifyJsonLogin(String username, String password) {
        System.out.println("🔹 JSON login attempt: " + username + " / " + password);

        boolean isValid = username.equals("admin") && password.equals("admin123");
        Assert.assertTrue(isValid, "❌ Invalid JSON credentials: " + username);
    }

    @Test
    public void debugJson() {
        String path = System.getProperty("user.dir") + "/src/test/resources/testdata/LoginData.json";
        Object[][] data = com.demoqa.utils.JsonUtils.getJsonData(path);
        System.out.println("Loaded data rows: " + data.length);
    }
}
