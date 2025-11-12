//package com.demoqa.base;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.*;
//import java.time.Duration;
//
//public class BasePage {
//    protected WebDriver driver;
//    protected WebDriverWait wait;
//
//    public BasePage(WebDriver driver, long timeoutSec) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
//    }
//
//    protected WebElement $(By locator) {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    /** Safe click that retries, scrolls & removes ad iframes */
//    protected void click(By locator) {
//        try {
//            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
//            scrollIntoViewCenter(el);
//            el.click();
//        } catch (Exception e1) {
//            try {
//                WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//                scrollIntoViewCenter(el);
//                new Actions(driver).moveToElement(el).click().perform();
//            } catch (Exception e2) {
//                // Remove possible ad overlays / iframes and JS-click
//                ((JavascriptExecutor) driver).executeScript(
//                        "document.querySelectorAll(\"iframe[id^='google_ads_iframe'],iframe[src*='googlesyndication'],div[id*='Ad.Plus-Anchor']\").forEach(e=>e.remove());");
//                WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//                scrollIntoViewCenter(el);
//                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//            }
//        }
//    }
//
//    protected void type(By locator, String text) {
//        WebElement el = $(locator);
//        el.clear();
//        el.sendKeys(text);
//    }
//
//    protected void scrollIntoViewCenter(WebElement el) {
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
//    }
//}

//package com.demoqa.base;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.*;
//
//import java.time.Duration;
//
//public class BasePage {
//    protected WebDriver driver;
//    protected WebDriverWait wait;
//
//    public BasePage(WebDriver driver, long timeoutSec) {
//        this.driver = driver;
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
//    }
//
//    /** Presence first (more tolerant), then visibility if possible */
//    protected WebElement $(By locator) {
//        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//        try {
//            return wait.until(ExpectedConditions.visibilityOf(el));
//        } catch (TimeoutException ignored) {
//            // return as-is if still not visible (caller may only need textContent via JS)
//            return el;
//        }
//    }
//
//    /** Safe click that retries, scrolls & removes ad iframes */
//    protected void click(By locator) {
//        removeOverlays();
//        try {
//            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
//            scrollIntoViewCenter(el);
//            el.click();
//        } catch (Exception e1) {
//            try {
//                WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//                scrollIntoViewCenter(el);
//                new Actions(driver).moveToElement(el).click().perform();
//            } catch (Exception e2) {
//                removeOverlays();
//                WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//                scrollIntoViewCenter(el);
//                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//            }
//        }
//    }
//
//    protected void type(By locator, String text) {
//        WebElement el = $(locator);
//        scrollIntoViewCenter(el);
//        try { el.clear(); } catch (Exception ignored) {}
//        el.sendKeys(text);
//    }
//
//    protected void scrollIntoViewCenter(WebElement el) {
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
//    }
//
//    /** Remove sticky ad iframes & fixed banners that intercept clicks/visibility */
//    protected void removeOverlays() {
//        try {
//            ((JavascriptExecutor) driver).executeScript(
//                    "try{document.querySelectorAll(\"iframe[id^='google_ads_iframe'],iframe[src*='googlesyndication'],div[id*='Ad.Plus-Anchor'],div[id='fixedban']\").forEach(e=>e.remove());}catch(e){}");
//        } catch (Exception ignored) {}
//    }
//
//    /** Wait for full DOM ready (document.readyState === 'complete') */
//    protected void waitDomReady() {
//        wait.until(d -> "complete".equals(
//                ((JavascriptExecutor) d).executeScript("return document.readyState")));
//    }
//}

package com.demoqa.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, long timeoutSec) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
    }

    protected WebElement $(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
            scrollIntoViewCenter(el);
            el.click();
        } catch (Exception e1) {
            try {
                WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                scrollIntoViewCenter(el);
                new Actions(driver).moveToElement(el).click().perform();
            } catch (Exception e2) {
                ((JavascriptExecutor) driver).executeScript(
                        "document.querySelectorAll(\"iframe[id^='google_ads_iframe'],iframe[src*='googlesyndication'],div[id*='Ad.Plus-Anchor']\").forEach(e=>e.remove());");
                WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                scrollIntoViewCenter(el);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            }
        }
    }

    protected void type(By locator, String text) {
        WebElement el = $(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected void scrollIntoViewCenter(WebElement el) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
    }

    /** Waits for full page load (document.readyState = complete) */
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                wd -> ((JavascriptExecutor) wd)
                        .executeScript("return document.readyState").toString().equals("complete"));
    }

    /** Waits for JS + jQuery to be idle */
    public void waitForJSReady() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
            JavascriptExecutor js = (JavascriptExecutor) d;
            String ready = js.executeScript("return document.readyState").toString();
            Long jq = (Long) js.executeScript("return window.jQuery ? jQuery.active : 0;");
            return ready.equals("complete") && jq == 0;
        });
    }

    /** Highlights element visually (yellow border) */
    public void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.border='3px solid yellow'; arguments[0].style.background='lightyellow';",
                element);
    }

//    // ------------- 📸 SCREENSHOT UTILITY -------------
//    public String captureScreenshot(String testName) {
//        try {
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            File src = ts.getScreenshotAs(OutputType.FILE);
//            String path = System.getProperty("user.dir") + "/screenshots/"
//                    + testName + "_" + System.currentTimeMillis() + ".png";
//            File dest = new File(path);
//            dest.getParentFile().mkdirs(); // make folder if missing
//            org.openqa.selenium.io.FileHandler.copy(src, dest);
//            return path;
//        } catch (Exception e) {
//            System.out.println("❌ Screenshot capture failed: " + e.getMessage());
//            return null;
//        }
//    }

//    public String captureScreenshot(String testName) {
//        try {
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//            // ✅ Correct fixed folder path under test-output/screenshots
//            String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
//            File dir = new File(screenshotDir);
//            if (!dir.exists()) {
//                dir.mkdirs();
//                System.out.println("📁 Created missing screenshots directory: " + dir.getAbsolutePath());
//            }
//
//            String filePath = screenshotDir + testName + "_" + System.currentTimeMillis() + ".png";
//            File dest = new File(filePath);
//            FileUtils.copyFile(src, dest);
//
//            System.out.println("📸 Screenshot saved at: " + filePath);
//            return filePath;
//
//        } catch (Exception e) {
//            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
//            return null;
//        }
//    }

//    public String captureScreenshot(String testName) {
//        try {
//            // Take screenshot
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//            // ✅ Common folder for all screenshots
//            String folderPath = System.getProperty("user.dir") + "/test-output/screenshots/";
//            File dir = new File(folderPath);
//            if (!dir.exists()) {
//                dir.mkdirs();
//                System.out.println("📁 Created screenshots directory: " + folderPath);
//            }
//
//            // ✅ Screenshot file path (overwrite same test screenshot every run)
//            String filePath = folderPath + testName + ".png";
//
//            // ✅ Overwrite previous image (no timestamp)
//            File dest = new File(filePath);
//            FileUtils.copyFile(src, dest);
//
//            System.out.println("📸 Screenshot captured and saved at: " + filePath);
//            return filePath;
//        } catch (Exception e) {
//            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
//            return null;
//        }
//    }

    public String captureScreenshot(String testName) {
        try {
            // ✅ Take screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // ✅ Always store inside test-output/screenshots (no extra prefix)
            String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
            File dir = new File(screenshotDir);
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("📁 Created screenshots directory: " + dir.getAbsolutePath());
            }

            // ✅ Final clean path (no duplication, no timestamp for overwrite)
            String filePath = screenshotDir + testName + ".png";
            File dest = new File(filePath);

            // ✅ Overwrite any existing file each run
            FileUtils.copyFile(src, dest);

            System.out.println("📸 Screenshot saved successfully: " + filePath);
            return filePath;

        } catch (Exception e) {
            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }
}
