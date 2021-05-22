package org.dolphin;

import org.dolphin.pages.BrowserWindowPage;
import org.dolphin.pages.FramesPage;
import org.dolphin.pages.MainPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class FramesTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public FramesPage framesPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        framesPage = new FramesPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyFrameHandling() {
        mainPage.platformToSections(true, SectionFamily.MainSection.ALERTS_FRAME_WINDOWS.toString(), SectionFamily.SubSection.FRAMES.toString());
        framesPage.frameHandling();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
