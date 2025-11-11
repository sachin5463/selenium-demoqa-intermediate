package com.demoqa.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class WaitUtil {

    public static WebElement waitForVisibility(WebDriver driver, By locator, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForAlert(WebDriver driver, long timeoutSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void fluentWait(WebDriver driver, By locator, long timeoutSec) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
