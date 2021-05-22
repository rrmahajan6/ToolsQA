package org.dolphin;

import org.dolphin.pages.AccordianPage;
import org.dolphin.pages.MainPage;
import org.dolphin.pages.ModalDialogPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class AccordianTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public AccordianPage accordianPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        accordianPage = new AccordianPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyHandlingAccordian() {
        mainPage.platformToSections(true, SectionFamily.MainSection.WIDGETS.toString(), SectionFamily.SubSection.ACCORDIAN.toString());
        accordianPage.handlingAccordian();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
