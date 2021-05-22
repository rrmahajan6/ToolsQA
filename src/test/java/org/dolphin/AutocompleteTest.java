package org.dolphin;

import org.dolphin.pages.AutoCompletePage;
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

public class AutocompleteTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public AutoCompletePage autoCompletePage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        autoCompletePage = new AutoCompletePage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyAutocompleteTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.WIDGETS.toString(), SectionFamily.SubSection.AUTO_COMPLETE.toString());
        autoCompletePage.autoCompleteHandling();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
