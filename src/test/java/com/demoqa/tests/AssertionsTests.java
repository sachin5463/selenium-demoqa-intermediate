//package com.demoqa.tests;
//
//import com.demoqa.base.DriverFactory;
//import com.demoqa.listeners.TestListener;
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import java.time.Duration;
//
//@Listeners(TestListener.class)
//public class AssertionsTests extends BaseTest {
//
//    WebDriver driver;
//
//    @BeforeMethod
//    public void setUp() {
//        DriverFactory.initDriver("chrome");
//        driver = DriverFactory.getDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    }

//    @Test
//    public void verifyTitleAndURL() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
//
//        String actualTitle = driver.getTitle().trim();
//        System.out.println("🔹 Actual title: " + actualTitle);
//        // ✅ Updated expected title
//        Assert.assertTrue(actualTitle.contains("Selenium Grid Online"),
//                "❌ Page title mismatch! Expected to contain 'Selenium Grid Online'");
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("selenium-playground"), "❌ URL does not contain selenium-playground!");
//    }

//    @Test
//    public void verifyTitleAndURL() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
//
//        String actualTitle = driver.getTitle().trim();
//        System.out.println("🔹 Actual title: " + actualTitle);
//
//        // ❌ Force-failing the test intentionally
//        Assert.assertTrue(actualTitle.contains("NonExistingKeyword"),
//                "❌ Forced Failure: Expected keyword not found in title!");
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("selenium-playground"), "❌ URL mismatch!");
//    }
//
//
//    @Test
//    public void verifyElementText() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement header = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1"))
//        );
//
//        String headerText = header.getText().trim();
//        System.out.println("🔹 Header text: " + headerText);
//        Assert.assertTrue(headerText.contains("Selenium Playground"), "❌ Header text mismatch!");
//    }
//
//    @Test
//    public void verifyMultipleConditions() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement header = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1"))
//        );
//
//        org.testng.asserts.SoftAssert soft = new org.testng.asserts.SoftAssert();
//        // ✅ Adjusted to real title
//        soft.assertTrue(driver.getTitle().contains("Selenium Grid Online"), "❌ Title mismatch!");
//        soft.assertTrue(driver.getCurrentUrl().contains("lambdatest"), "❌ URL not valid!");
//        soft.assertTrue(header.getText().contains("Selenium Playground"), "❌ Header text incorrect!");
//        soft.assertAll();
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        DriverFactory.quitDriver();
//    }
//}

//package com.demoqa.tests;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import java.time.Duration;
//
//public class AssertionsTests extends BaseTest {
//
//    @Test
//    public void verifyTitleAndURL() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
//
//        String actualTitle = driver.getTitle().trim();
//        System.out.println("🔹 Actual title: " + actualTitle);
//
//        // ❌ Force-failing the test intentionally to verify screenshot
//        Assert.assertTrue(actualTitle.contains("NonExistingKeyword"),
//                "❌ Forced Failure: Expected keyword not found in title!");
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("selenium-playground"), "❌ URL mismatch!");
//    }
//
//    @Test
//    public void verifyElementText() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
//        String headerText = header.getText().trim();
//        System.out.println("🔹 Header text: " + headerText);
//        Assert.assertTrue(headerText.contains("Selenium Playground"), "❌ Header text mismatch!");
//    }
//
//    @Test
//    public void verifyMultipleConditions() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
//
//        org.testng.asserts.SoftAssert soft = new org.testng.asserts.SoftAssert();
//        soft.assertTrue(driver.getTitle().contains("Selenium Grid Online"), "❌ Title mismatch!");
//        soft.assertTrue(driver.getCurrentUrl().contains("lambdatest"), "❌ URL not valid!");
//        soft.assertTrue(header.getText().contains("Selenium Playground"), "❌ Header text incorrect!");
//        soft.assertAll();
//    }
//}


package com.demoqa.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class AssertionsTests extends BaseTest {

    // ✅ 1️⃣ Working PASS version
    @Test
    public void verifyTitleAndURL_Pass() {
        driver.get("https://www.lambdatest.com/selenium-playground/");

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

        String actualTitle = driver.getTitle().trim();
        System.out.println("🔹 Actual title: " + actualTitle);

        // ✅ Real check – expected substring exists
        Assert.assertTrue(actualTitle.contains("Selenium Grid Online"),
                "❌ Page title mismatch! Expected to contain 'Selenium Grid Online'");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("selenium-playground"),
                "❌ URL does not contain selenium-playground!");
    }


    // ❌ 2️⃣ Forced FAIL version (for screenshot verification)
//    @Test
//    public void verifyTitleAndURL_Fail() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
//
//        String actualTitle = driver.getTitle().trim();
//        System.out.println("🔹 Actual title: " + actualTitle);
//
//        // ❌ Intentionally failing check
//        Assert.assertTrue(actualTitle.contains("NonExistingKeyword"),
//                "❌ Forced Failure: Expected keyword not found in title!");
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("selenium-playground"),
//                "❌ URL mismatch!");
//    }

//    @Test(retryAnalyzer = com.demoqa.utils.RetryAnalyzer.class)
//    public void verifyTitleAndURL() {
//        driver.get("https://www.lambdatest.com/selenium-playground/");
//
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
//
//        String actualTitle = driver.getTitle().trim();
//        System.out.println("🔹 Actual title: " + actualTitle);
//
//        // ❌ Force failing to trigger RetryAnalyzer (just for testing)
//        Assert.assertTrue(actualTitle.contains("NonExistingKeyword"),
//                "❌ Forced Failure: Expected keyword not found in title!");
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(currentUrl.contains("selenium-playground"), "❌ URL mismatch!");
//    }

    @Test
    public void verifyTitleAndURL() {
        driver.get("https://www.lambdatest.com/selenium-playground/");

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

        String actualTitle = driver.getTitle().trim();
        System.out.println("🔹 Actual title: " + actualTitle);

        // ✅ Corrected assertion for Jenkins build success
        Assert.assertTrue(actualTitle.contains("Selenium Grid Online"),
                "❌ Title mismatch: Expected 'Selenium Grid Online' keyword not found in title!");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("selenium-playground"), "❌ URL mismatch!");
    }


    @Test
    public void verifyElementText() {
        driver.get("https://www.lambdatest.com/selenium-playground/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        String headerText = header.getText().trim();
        System.out.println("🔹 Header text: " + headerText);
        Assert.assertTrue(headerText.contains("Selenium Playground"), "❌ Header text mismatch!");
    }


    @Test
    public void verifyMultipleConditions() {
        driver.get("https://www.lambdatest.com/selenium-playground/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        org.testng.asserts.SoftAssert soft = new org.testng.asserts.SoftAssert();
        soft.assertTrue(driver.getTitle().contains("Selenium Grid Online"), "❌ Title mismatch!");
        soft.assertTrue(driver.getCurrentUrl().contains("lambdatest"), "❌ URL not valid!");
        soft.assertTrue(header.getText().contains("Selenium Playground"), "❌ Header text incorrect!");
        soft.assertAll();
    }
}
