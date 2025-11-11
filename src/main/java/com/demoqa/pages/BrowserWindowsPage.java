package com.demoqa.pages;

import com.demoqa.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class BrowserWindowsPage extends BasePage {

    private final By newTabBtn = By.id("tabButton");
    private final By heading = By.id("sampleHeading");

    public BrowserWindowsPage(WebDriver driver, long timeout) {
        super(driver, timeout);
    }

    public String openNewTabAndGetHeading() {
        String parent = driver.getWindowHandle();

        // ✅ Clean ads/iframes before click (DemoQA often injects)
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll(\"iframe[id^='google_ads_iframe'],iframe[src*='googlesyndication'],div[id*='Ad.Plus-Anchor']\").forEach(e=>e.remove());"
        );

        click(newTabBtn);

        // ✅ Wait for new tab
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.getWindowHandles().size() > 1);

        // ✅ Switch to new tab
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // ✅ Wait for heading visibility
        WebElement headingEl = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(heading));

        String text = headingEl.getText().trim();

        // ✅ Close new tab & switch back
        driver.close();
        driver.switchTo().window(parent);

        return text;
    }
}
