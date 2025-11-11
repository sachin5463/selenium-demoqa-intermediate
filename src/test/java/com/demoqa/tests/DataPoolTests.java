package com.demoqa.tests;

import com.demoqa.utils.ConfigLoader;
import com.demoqa.utils.ExcelUtil;
import org.testng.annotations.Test;
import java.util.Map;

public class DataPoolTests extends BaseTest {

    @Test
    public void verifyExcelDataRead() {
        String path = "src/test/resources/testdata/UserData.xlsx";
        Map<String, String> data = ExcelUtil.readRow(path, "Sheet1", 1);
        System.out.println("Username: " + data.get("username"));
        System.out.println("Password: " + data.get("password"));
        System.out.println("Email: " + data.get("email"));
    }

    @Test
    public void verifyPropertyData() {
        String username = ConfigLoader.getTestData("login.username");
        System.out.println("Username: " + username);
    }
}
