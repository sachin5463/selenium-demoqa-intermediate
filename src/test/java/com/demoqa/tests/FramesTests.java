package com.demoqa.tests;

import com.demoqa.pages.FramesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesTests extends BaseTest {

    @Test(groups = {"frames","regression"})
    public void verifyTextInsideFrames() {
        driver.get("https://demoqa.com/frames");
        FramesPage frames = new FramesPage(driver, timeout);

        String frame1Text = frames.getTextFromFrame1();
        String frame2Text = frames.getTextFromFrame2();

        Assert.assertTrue(frame1Text.contains("This is a sample page"));
        Assert.assertTrue(frame2Text.contains("This is a sample page"));
    }
}
