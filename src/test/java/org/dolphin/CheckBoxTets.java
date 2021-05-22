package org.dolphin;

import org.dolphin.pages.CheckBoxPage;
import org.dolphin.pages.MainPage;
import org.dolphin.pages.TextBoxPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class CheckBoxTets extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public CheckBoxPage checkBoxPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        checkBoxPage = new CheckBoxPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyCheckBoxes() {
        mainPage.platformToSections(true, SectionFamily.MainSection.ELEMENTS.toString(), SectionFamily.SubSection.CHECK_BOX.toString());
        checkBoxPage.expandAllCheckBoxes();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
