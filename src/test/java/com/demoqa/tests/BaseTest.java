//package com.demoqa.tests;
//
//import com.demoqa.base.DriverFactory;
//import com.demoqa.utils.ConfigLoader;
//import lombok.Getter;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.*;
//
//public class BaseTest {
//    protected WebDriver driver;
//    protected long timeout;
//
//    @Parameters({"browser"})
//    @BeforeMethod
//    public void setup(@Optional String browser) {
//        DriverFactory.initDriver(browser != null ? browser : ConfigLoader.get("browser"));
//        driver = DriverFactory.getDriver();
//        timeout = Long.parseLong(ConfigLoader.get("timeout"));
//    }
//
//    // ✅ Add this
//    @SuppressWarnings("Lombok")
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        DriverFactory.quitDriver();
//    }
//
//
//}

package com.demoqa.tests;

import com.demoqa.base.DriverFactory;
import com.demoqa.listeners.TestListener;
import com.demoqa.utils.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(TestListener.class) // ✅ global listener for all test classes
public class BaseTest {
    protected WebDriver driver;
    protected long timeout;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional String browser) {
        DriverFactory.initDriver(browser != null ? browser : ConfigLoader.get("browser"));
        driver = DriverFactory.getDriver();
        timeout = Long.parseLong(ConfigLoader.get("timeout"));
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
