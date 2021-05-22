package org.dolphin;

import org.dolphin.pages.ButtonsPage;
import org.dolphin.pages.MainPage;
import org.dolphin.pages.WebTablesPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ButtonTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public ButtonsPage buttonsPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        buttonsPage = new ButtonsPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyClickingButtonsTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.ELEMENTS.toString(), SectionFamily.SubSection.BUTTONS.toString());
        buttonsPage.clickingButtons();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
