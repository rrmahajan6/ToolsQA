package org.dolphin;

import org.dolphin.pages.MainPage;
import org.dolphin.pages.ProgressBarPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ProgressBarTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public ProgressBarPage progressBarPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        progressBarPage = new ProgressBarPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyProgressBarTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.WIDGETS.toString(), SectionFamily.SubSection.PROGRESS_BAR.toString());
        progressBarPage.handlingProgressbar();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
