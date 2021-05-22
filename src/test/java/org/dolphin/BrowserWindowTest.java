package org.dolphin;

import org.dolphin.pages.BrowserWindowPage;
import org.dolphin.pages.MainPage;
import org.dolphin.pages.PracticeFormPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class BrowserWindowTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public BrowserWindowPage browserWindowPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        browserWindowPage = new BrowserWindowPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyBrowserWindowHandling() {
        mainPage.platformToSections(true, SectionFamily.MainSection.ALERTS_FRAME_WINDOWS.toString(), SectionFamily.SubSection.BROWSER_WINDOWS.toString());
        browserWindowPage.windowHandling();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
