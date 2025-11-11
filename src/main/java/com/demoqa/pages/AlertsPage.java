package com.demoqa.pages;

import com.demoqa.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertsPage extends BasePage {

    private final By alertBtn = By.id("alertButton");
    private final By confirmBtn = By.id("confirmButton");
    private final By promptBtn = By.id("promtButton");
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    public AlertsPage(WebDriver driver, long timeout) {
        super(driver, timeout);
    }

    public String handleSimpleAlert() {
        click(alertBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String msg = alert.getText();
        alert.accept();
        return msg;
    }

    public String handleConfirmAlert(boolean accept) {
        click(confirmBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if (accept) alert.accept(); else alert.dismiss();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmResult)).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public String handlePromptAlert(String name) {
        click(promptBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(name);
        alert.accept();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(promptResult)).getText();
        } catch (TimeoutException e) {
            return "";
        }
    }
}
