package org.dolphin;

import org.dolphin.pages.MainPage;
import org.dolphin.pages.RadioButtonPage;
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

public class RadioButtonTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public RadioButtonPage radioButtonPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        radioButtonPage = new RadioButtonPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyRadioButtonSelectionTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.ELEMENTS.toString(), SectionFamily.SubSection.RADIO_BUTTONS.toString());
        radioButtonPage.selectButton();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
