package org.dolphin;

import org.dolphin.pages.FramesPage;
import org.dolphin.pages.MainPage;
import org.dolphin.pages.SliderPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class SliderTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public SliderPage sliderPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        sliderPage = new SliderPage(driver);
        mainPage.platformToSections(true, SectionFamily.MainSection.WIDGETS.toString(), SectionFamily.SubSection.SLIDER.toString());
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifySliderTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.WIDGETS.toString(), SectionFamily.SubSection.SLIDER.toString());
        sliderPage.handlingSlider();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
