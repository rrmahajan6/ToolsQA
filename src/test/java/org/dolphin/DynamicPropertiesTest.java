package org.dolphin;

import javafx.application.Application;
import javafx.stage.Stage;
import org.dolphin.pages.DynamicPropertiesPage;
import org.dolphin.pages.MainPage;
import org.dolphin.pages.UploadDownloadPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DynamicPropertiesTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public DynamicPropertiesPage dynamicPropertiesPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        dynamicPropertiesPage = new DynamicPropertiesPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyDynamicPropertiesTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.ELEMENTS.toString(), SectionFamily.SubSection.DYNAMIC_PROPERTIES.toString());
        dynamicPropertiesPage.dynamicButtons();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
