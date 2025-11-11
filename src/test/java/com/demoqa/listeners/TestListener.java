/*package com.demoqa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demoqa.utils.ExtentManager;
import org.testng.*;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ Test Failed: " + result.getName());
        test.get().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getName());
    }

    //@Override
    //public void onFinish(ITestContext context) {
      //  extent.flush();
    //}
    @Override
    public void onFinish(ITestContext context) {
        try {
            System.out.println("🧾 Forcing Extent Report flush...");
            if (extent != null) {
                extent.flush();
                extent = null; // 🔥 ye line important hai — handle release karega
                System.gc();   // suggest GC to free the file lock
                System.out.println("✅ Extent Report flushed successfully!");
            } else {
                System.out.println("❌ Extent object was null, nothing to flush.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}*/

//package com.demoqa.listeners;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.demoqa.utils.ExtentManager;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//public class TestListener implements ITestListener {
//
//    private static ExtentReports extent = ExtentManager.createInstance();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//        test.set(extentTest);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        test.get().log(Status.PASS, "✅ Test passed: " + result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        test.get().log(Status.FAIL, "❌ Test failed: " + result.getName());
//        test.get().log(Status.FAIL, result.getThrowable());
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("🧾 Forcing Extent Report flush...");
//        if (extent != null) extent.flush();
//        else System.out.println("❌ Extent object was null, nothing to flush.");
//    }
//}

//package com.demoqa.listeners;
//
//import com.aventstack.extentreports.*;
//import com.demoqa.base.BasePage;
//import com.demoqa.tests.BaseTest;
//import com.demoqa.utils.ExtentManager;
//import org.openqa.selenium.WebDriver;
//import org.testng.*;
//
//public class TestListener implements ITestListener {
//
//    private static ExtentReports extent = ExtentManager.createInstance();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//        test.set(extentTest);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        test.get().log(Status.PASS, "✅ Test passed: " + result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        test.get().log(Status.FAIL, "❌ Test failed: " + result.getName());
//        test.get().log(Status.FAIL, result.getThrowable());
//
//        try {
//            Object currentClass = result.getInstance();
//            WebDriver driver = ((BaseTest) currentClass).getDriver();
//            String screenshotPath = new BasePage(driver, 10).captureScreenshot(result.getName());
//            if (screenshotPath != null) {
//                test.get().addScreenCaptureFromPath(screenshotPath);
//                System.out.println("📸 Screenshot captured at: " + screenshotPath);
//            }
//        } catch (Exception e) {
//            System.out.println("⚠️ Could not capture screenshot: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        test.get().log(Status.SKIP, "⚠️ Test skipped: " + result.getName());
//        try {
//            Object currentClass = result.getInstance();
//            //WebDriver driver = ((BaseTest) currentClass).driver;
//            WebDriver driver = ((BaseTest) currentClass).getDriver();
//            String screenshotPath = new BasePage(driver, 10).captureScreenshot(result.getName() + "_skipped");
//            if (screenshotPath != null)
//                test.get().addScreenCaptureFromPath(screenshotPath);
//        } catch (Exception e) {
//            System.out.println("⚠️ Screenshot skip-capture failed: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("🧾 Forcing Extent Report flush...");
//        if (extent != null) extent.flush();
//        else System.out.println("❌ Extent object was null, nothing to flush.");
//    }
//}

//package com.demoqa.listeners;
//
//import com.aventstack.extentreports.*;
//import com.demoqa.base.BasePage;
//import com.demoqa.tests.BaseTest;
//import com.demoqa.utils.ExtentManager;
//import org.openqa.selenium.*;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import java.io.File;
//
//public class TestListener implements ITestListener {
//
//    private static ExtentReports extent = ExtentManager.createInstance();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    // 📁 Ensure screenshots folder exists
//    private void ensureScreenshotFolderExists() {
//        File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
//        if (!screenshotDir.exists()) {
//            screenshotDir.mkdirs();
//            System.out.println("📁 Created missing screenshots directory: " + screenshotDir.getAbsolutePath());
//        }
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//        test.set(extentTest);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        test.get().log(Status.PASS, "✅ Test passed: " + result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        test.get().log(Status.FAIL, "❌ Test failed: " + result.getName());
//        test.get().log(Status.FAIL, result.getThrowable());
//
//        try {
//            ensureScreenshotFolderExists();
//            Object currentClass = result.getInstance();
//            WebDriver driver = ((BaseTest) currentClass).getDriver();
//            String screenshotPath = new BasePage(driver, 10).captureScreenshot(result.getName());
//
//            if (screenshotPath != null) {
//                test.get().addScreenCaptureFromPath(screenshotPath);
//                System.out.println("📸 Screenshot captured at: " + screenshotPath);
//            }
//        } catch (Exception e) {
//            System.out.println("⚠️ Could not capture screenshot: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        test.get().log(Status.SKIP, "⚠️ Test skipped: " + result.getName());
//        try {
//            ensureScreenshotFolderExists();
//            Object currentClass = result.getInstance();
//            WebDriver driver = ((BaseTest) currentClass).getDriver();
//            String screenshotPath = new BasePage(driver, 10).captureScreenshot(result.getName() + "_skipped");
//            if (screenshotPath != null)
//                test.get().addScreenCaptureFromPath(screenshotPath);
//        } catch (Exception e) {
//            System.out.println("⚠️ Screenshot skip-capture failed: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("🧾 Forcing Extent Report flush...");
//        if (extent != null) {
//            extent.flush();
//            System.out.println("✅ Extent Report flushed successfully!");
//        } else {
//            System.out.println("❌ Extent object was null, nothing to flush.");
//        }
//    }
//}

//package com.demoqa.listeners;
//
//import com.aventstack.extentreports.*;
//import com.demoqa.base.BasePage;
//import com.demoqa.tests.BaseTest;
//import com.demoqa.utils.ExtentManager;
//import org.openqa.selenium.*;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import java.io.File;
//
//public class TestListener implements ITestListener {
//
//    private static ExtentReports extent = ExtentManager.createInstance();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    // 📁 Ensure screenshots folder inside test-output
//    private void ensureScreenshotFolderExists() {
//        File screenshotDir = new File(System.getProperty("user.dir") + "/test-output/screenshots");
//        if (!screenshotDir.exists()) {
//            screenshotDir.mkdirs();
//            System.out.println("📁 Created missing screenshots directory: " + screenshotDir.getAbsolutePath());
//        }
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//        test.set(extentTest);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        test.get().log(Status.PASS, "✅ Test passed: " + result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        test.get().log(Status.FAIL, "❌ Test failed: " + result.getName());
//        test.get().log(Status.FAIL, result.getThrowable());
//
//        try {
//            ensureScreenshotFolderExists();
//
//            Object currentClass = result.getInstance();
//            WebDriver driver = ((BaseTest) currentClass).getDriver();
//            String screenshotPath = new BasePage(driver, 10)
//                    .captureScreenshot("test-output/screenshots/" + result.getName());
//
//            if (screenshotPath != null) {
//                test.get().addScreenCaptureFromPath(screenshotPath);
//                System.out.println("📸 Screenshot saved: " + screenshotPath);
//            }
//        } catch (Exception e) {
//            System.out.println("⚠️ Could not capture screenshot: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        test.get().log(Status.SKIP, "⚠️ Test skipped: " + result.getName());
//        try {
//            ensureScreenshotFolderExists();
//
//            Object currentClass = result.getInstance();
//            WebDriver driver = ((BaseTest) currentClass).getDriver();
//            String screenshotPath = new BasePage(driver, 10)
//                    .captureScreenshot("test-output/screenshots/" + result.getName() + "_skipped");
//
//            if (screenshotPath != null)
//                test.get().addScreenCaptureFromPath(screenshotPath);
//        } catch (Exception e) {
//            System.out.println("⚠️ Screenshot skip-capture failed: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("🧾 Flushing Extent Report...");
//        if (extent != null) {
//            extent.flush();
//            System.out.println("✅ Extent Report flushed successfully!");
//        } else {
//            System.out.println("❌ Extent object was null, nothing to flush.");
//        }
//    }
//}

//package com.demoqa.listeners;
//
//import com.aventstack.extentreports.*;
//import com.demoqa.base.BasePage;
//import com.demoqa.tests.BaseTest;
//import com.demoqa.utils.ExtentManager;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import java.io.File;
//
//public class TestListener implements ITestListener {
//
//    private static ExtentReports extent = ExtentManager.createInstance();
//    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//    private void ensureScreenshotFolderExists() {
//        File screenshotDir = new File(System.getProperty("user.dir") + "/test-output/screenshots");
//        if (!screenshotDir.exists()) {
//            screenshotDir.mkdirs();
//            System.out.println("📁 Created missing screenshots directory: " + screenshotDir.getAbsolutePath());
//        }
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
//        test.set(extentTest);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        test.get().log(Status.PASS, "✅ Test passed: " + result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        test.get().log(Status.FAIL, "❌ Test failed: " + result.getName());
//        test.get().log(Status.FAIL, result.getThrowable());
//
//        try {
//            ensureScreenshotFolderExists();
//            Object currentClass = result.getInstance();
//            WebDriver driver = ((BaseTest) currentClass).getDriver();
//            String screenshotPath = new BasePage(driver, 10)
//                    .captureScreenshot("test-output/screenshots/" + result.getName());
//            if (screenshotPath != null) {
//                test.get().addScreenCaptureFromPath(screenshotPath);
//                System.out.println("📸 Screenshot saved: " + screenshotPath);
//            }
//        } catch (Exception e) {
//            System.out.println("⚠️ Could not capture screenshot: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        test.get().log(Status.SKIP, "⚠️ Test skipped: " + result.getName());
//        try {
//            ensureScreenshotFolderExists();
//            Object currentClass = result.getInstance();
//            WebDriver driver = ((BaseTest) currentClass).getDriver();
//            String screenshotPath = new BasePage(driver, 10)
//                    .captureScreenshot("test-output/screenshots/" + result.getName() + "_skipped");
//            if (screenshotPath != null)
//                test.get().addScreenCaptureFromPath(screenshotPath);
//        } catch (Exception e) {
//            System.out.println("⚠️ Screenshot skip-capture failed: " + e.getMessage());
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("🧾 Flushing Extent Report...");
//        if (extent != null) {
//            extent.flush();
//            System.out.println("✅ Extent Report flushed successfully!");
//        }
//    }
//}

package com.demoqa.listeners;

import com.aventstack.extentreports.*;
import com.demoqa.base.BasePage;
import com.demoqa.tests.BaseTest;
import com.demoqa.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private void ensureScreenshotFolderExists() {
        File screenshotDir = new File(System.getProperty("user.dir") + "/test-output/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
            System.out.println("📁 Created missing screenshots directory: " + screenshotDir.getAbsolutePath());
        }
    }

    private String capture(ITestResult result, WebDriver driver, String status) {
        try {
            ensureScreenshotFolderExists();
            String fileName = result.getName() + "_" + status;
            String screenshotPath = new BasePage(driver, 10)
                    .captureScreenshot("test-output/screenshots/" + fileName);
            if (screenshotPath != null) {
                test.get().addScreenCaptureFromPath(screenshotPath);
                System.out.println("📸 Screenshot captured for " + status + " test: " + screenshotPath);
            }
            return screenshotPath;
        } catch (Exception e) {
            System.out.println("⚠️ Could not capture " + status + " screenshot: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ Test passed: " + result.getName());
        try {
            Object currentClass = result.getInstance();
            WebDriver driver = ((BaseTest) currentClass).getDriver();
            capture(result, driver, "PASS");
        } catch (Exception e) {
            System.out.println("⚠️ Unable to capture pass screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ Test failed: " + result.getName());
        test.get().log(Status.FAIL, result.getThrowable());
        try {
            Object currentClass = result.getInstance();
            WebDriver driver = ((BaseTest) currentClass).getDriver();
            capture(result, driver, "FAIL");
        } catch (Exception e) {
            System.out.println("⚠️ Unable to capture fail screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⚠️ Test skipped: " + result.getName());
        try {
            Object currentClass = result.getInstance();
            WebDriver driver = ((BaseTest) currentClass).getDriver();
            capture(result, driver, "SKIPPED");
        } catch (Exception e) {
            System.out.println("⚠️ Unable to capture skipped screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🧾 Flushing Extent Report...");
        if (extent != null) {
            extent.flush();
            System.out.println("✅ Extent Report flushed successfully!");
        } else {
            System.out.println("❌ Extent object was null, nothing to flush.");
        }
    }
}
