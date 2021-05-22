package org.dolphin;

import org.dolphin.pages.MainPage;
import org.dolphin.pages.MenuPage;
import org.dolphin.pages.ToolTipsPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class MenuTest extends Base {
    public WebDriver driver;
    MainPage mainPage;
    MenuPage menuPage;

    @BeforeClass
    public void initilization() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        menuPage = new MenuPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyElementsTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.WIDGETS.toString(), SectionFamily.SubSection.MENU.toString());
        menuPage.handlingMenus();
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
