//package com.demoqa.pages;
//
//import com.demoqa.base.BasePage;
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//public class WaitsPage extends BasePage {
//
//    // Dynamic Properties
//    private By enableAfterBtn = By.id("enableAfter");
//    private By visibleAfterBtn = By.id("visibleAfter");
//    private By colorChangeBtn = By.id("colorChange");
//    private By buttonsHeader = By.cssSelector("div.main-header");
//
//    public WaitsPage(WebDriver driver, long timeout) {
//        super(driver, timeout);
//    }
//
//    /** Wait until button becomes clickable */
//    public boolean waitUntilButtonEnabled() {
//        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(enableAfterBtn));
//        return btn.isEnabled();
//    }
//
//    /** Wait until button becomes visible */
//    public boolean waitUntilButtonVisible() {
//        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(visibleAfterBtn));
//        return btn.isDisplayed();
//    }
//
//    /** Fetch CSS value after change */
//    public String getButtonColor() {
//        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(colorChangeBtn));
//        return btn.getCssValue("color");
//    }
//
//    /** Scroll and click using JavaScript Executor */
//    public void jsClick(By locator) {
//        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//    }
//
//    /** Verify page header text */
//    public String getHeaderText() {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonsHeader)).getText();
//    }
//}

//package com.demoqa.pages;
//
//import com.demoqa.base.BasePage;
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Wait;
//
//import java.time.Duration;
//
//public class WaitsPage extends BasePage {
//
//    // Dynamic Properties
//    private By enableAfterBtn = By.id("enableAfter");
//    private By visibleAfterBtn = By.id("visibleAfter");
//    private By colorChangeBtn = By.id("colorChange");
//    private By buttonsHeader = By.cssSelector("div.main-header");
//
//    public WaitsPage(WebDriver driver, long timeout) {
//        super(driver, timeout);
//    }
//
//    /** ✅ Wait until button becomes clickable */
//    public boolean waitUntilButtonEnabled() {
//        try {
//            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(enableAfterBtn));
//            return btn.isEnabled();
//        } catch (TimeoutException e) {
//            System.out.println("⚠️ Button never became clickable in expected time, rechecking via JS...");
//            Boolean exists = (Boolean) ((JavascriptExecutor) driver)
//                    .executeScript("return document.getElementById('enableAfter') !== null;");
//            return exists != null && exists;
//        }
//    }
//
//    /** ✅ Wait until button becomes visible (with JS fallback) */
//    public boolean waitUntilButtonVisible() {
//        try {
//            WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(visibleAfterBtn));
//            return btn.isDisplayed();
//        } catch (TimeoutException e) {
//            System.out.println("⚠️ Could not locate visibleAfter normally, checking via JS retry...");
//            try {
//                // Retry once more with JS fallback
//                WebDriverWait retryWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//                WebElement btn = retryWait.until(ExpectedConditions.presenceOfElementLocated(visibleAfterBtn));
//                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
//                return btn.isDisplayed();
//            } catch (Exception ex) {
//                Boolean exists = (Boolean) ((JavascriptExecutor) driver)
//                        .executeScript("return document.getElementById('visibleAfter') !== null;");
//                if (exists != null && exists) {
//                    WebElement el = driver.findElement(visibleAfterBtn);
//                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
//                    return el.isDisplayed();
//                }
//                System.out.println("❌ Element not visible even after retries!");
//                return false;
//            }
//        }
//    }
//
//    /** ✅ Fetch CSS value after color change */
//    public String getButtonColor() {
//        try {
//            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(colorChangeBtn));
//            return btn.getCssValue("color");
//        } catch (TimeoutException e) {
//            System.out.println("⚠️ Button color element delayed — retrying via JS...");
//            WebElement btn = driver.findElement(colorChangeBtn);
//            return btn.getCssValue("color");
//        }
//    }
//
//    /** ✅ Scroll and click using JavaScript Executor (safe click) */
//    public void jsClick(By locator) {
//        try {
//            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//        } catch (TimeoutException e) {
//            System.out.println("⚠️ JS click fallback triggered!");
//            ((JavascriptExecutor) driver).executeScript(
//                    "document.querySelectorAll(\"iframe[id^='google_ads_iframe'],iframe[src*='googlesyndication'],div[id*='Ad.Plus-Anchor']\").forEach(e=>e.remove());");
//            WebElement el = driver.findElement(locator);
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
//        }
//    }
//
////    /** ✅ Verify page header text (with soft fallback) */
////    public String getHeaderText() {
////        try {
////            return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonsHeader)).getText().trim();
////        } catch (TimeoutException e) {
////            System.out.println("⚠️ Header not visible initially — trying fallback locator read.");
////            try {
////                return driver.findElement(buttonsHeader).getText().trim();
////            } catch (NoSuchElementException ex) {
////                return "";
////            }
////        }
////    }
//
////    /** ✅ Verify page header text (robust version for DemoQA Buttons page) */
////    public String getHeaderText() {
////        try {
////            WebElement headerEl = wait.until(ExpectedConditions.presenceOfElementLocated(buttonsHeader));
////
////            // Wait until text is non-empty (sometimes DOM takes a bit to populate)
////            new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> {
////                String txt = headerEl.getText().trim();
////                return !txt.isEmpty();
////            });
////
////            String text = headerEl.getText().trim();
////            if (text.isEmpty()) {
////                // one last retry
////                Thread.sleep(1000);
////                text = driver.findElement(buttonsHeader).getText().trim();
////            }
////
////            return text;
////        } catch (Exception e) {
////            System.out.println("⚠️ Header not visible even after retries, returning empty.");
////            return "";
////        }
////    }
//
//    /** ✅ Verify page header text (force-scroll repaint version for DemoQA) */
//    public String getHeaderText() {
//        try {
//            WebElement headerEl = wait.until(ExpectedConditions.presenceOfElementLocated(buttonsHeader));
//
//            // Scroll multiple times to trigger viewport paint
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", headerEl);
//            Thread.sleep(700);
//            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");
//            Thread.sleep(700);
//            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150);");
//            Thread.sleep(700);
//
//            // Wait until text is non-empty
//            new WebDriverWait(driver, Duration.ofSeconds(5)).until(d -> {
//                String txt = headerEl.getText().trim();
//                return !txt.isEmpty();
//            });
//
//            String text = headerEl.getText().trim();
//
//            // Last fallback via JS DOM query
//            if (text.isEmpty()) {
//                System.out.println("⚠️ Header still empty after repaint, trying JS DOM read...");
//                Object txt = ((JavascriptExecutor) driver)
//                        .executeScript("return document.querySelector('div.main-header')?.innerText || '';");
//                text = txt != null ? txt.toString().trim() : "";
//            }
//
//            System.out.println("🔹 Header text after repaint: " + text);
//            return text;
//        } catch (Exception e) {
//            System.out.println("⚠️ Header not visible even after repaint retries!");
//            return "";
//        }
//    }
//    /** FluentWait demo: wait for color to change (Dynamic Properties page) */
//    public boolean waitForColorChange() {
//        WebElement btn = driver.findElement(colorChangeBtn);
//        String initialColor = btn.getCssValue("color");
//
//        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(15))
//                .pollingEvery(Duration.ofMillis(500))
//                .ignoring(StaleElementReferenceException.class)
//                .ignoring(NoSuchElementException.class);
//
//        return fluentWait.until(d -> {
//            String newColor = btn.getCssValue("color");
//            return !newColor.equals(initialColor);
//        });
//    }
//}

package com.demoqa.pages;

import com.demoqa.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitsPage extends BasePage {

    // Dynamic Properties page
    private final By enableAfterBtn  = By.id("enableAfter");
    private final By visibleAfterBtn = By.id("visibleAfter");
    private final By colorChangeBtn  = By.id("colorChange");

    // Common header on DemoQA pages
    private final By mainHeader      = By.cssSelector("div.main-header");

    // Buttons page
    private final By doubleClickBtn  = By.id("doubleClickBtn");
    private final By rightClickBtn   = By.id("rightClickBtn");
    private final By clickMeBtn      = By.xpath("//button[text()='Click Me']");

    public WaitsPage(WebDriver driver, long timeoutSec) {
        super(driver, timeoutSec);
    }

    /* ------------ Nav helpers (no assertions here) ------------ */
    public void openDynamicProperties() {
        driver.get("https://demoqa.com/dynamic-properties");
    }

    public void openButtonsPage() {
        driver.get("https://demoqa.com/buttons");
    }

    /* ------------ Dynamic waits (explicit & fluent) ------------ */
    /** Wait until button becomes clickable (enabled after ~5s) */
    public boolean waitUntilButtonEnabled() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(enableAfterBtn));
        return btn != null && btn.isEnabled();
    }

    /** Wait until button becomes visible (appears after ~5s) */
    public boolean waitUntilButtonVisible() {
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(visibleAfterBtn));
        return btn != null && btn.isDisplayed();
    }

    /**
     * FluentWait for color change.
     * DemoQA turns the text color to Bootstrap 'danger' (#dc3545) ≈ rgba(220, 53, 69, 1)
     * We simply wait until computed CSS 'color' stops equalling the initial one.
     */
    public String waitUntilColorChanges() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(colorChangeBtn));
        final String initial = btn.getCssValue("color");

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(StaleElementReferenceException.class)
                .until((Function<WebDriver, Boolean>) d -> {
                    try {
                        String now = driver.findElement(colorChangeBtn).getCssValue("color");
                        return (now != null && !now.equals(initial));
                    } catch (StaleElementReferenceException ignored) {
                        return false;
                    }
                });

        // return the *new* color
        return driver.findElement(colorChangeBtn).getCssValue("color");
    }

    /* ------------ JS helpers ------------ */
    /** Generic JS scroll-into-view and click */
    public void jsClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    /** Small visual debug helper (no-op if JS disabled) */
    public void highlight(By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.boxShadow='0 0 0 2px #00bcd4 inset';", el);
        } catch (Exception ignored) {}
    }

    /* ------------ Header text with robust fallback ------------ */
    /**
     * Tries normal header first, then JS textContent, then returns empty string.
     * We *don't* assert here to avoid test regressions – tests decide strictness.
     */
    public String getHeaderTextSafe() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(mainHeader)).getText().trim();
        } catch (TimeoutException te) {
            // Try reading via JS (sometimes header renders late)
            try {
                Object txt = ((JavascriptExecutor) driver)
                        .executeScript("var h=document.querySelector('div.main-header');return h?h.textContent:'';");
                return txt == null ? "" : String.valueOf(txt).trim();
            } catch (Exception ignored) {
                return "";
            }
        }
    }

    /* ------------ Expose locators for tests that need them ------------ */
    public By getDoubleClickBtn() { return doubleClickBtn; }
    public By getRightClickBtn()  { return rightClickBtn;  }
    public By getClickMeBtn()     { return clickMeBtn;     }
}
