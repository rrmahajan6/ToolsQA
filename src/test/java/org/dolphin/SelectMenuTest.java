package org.dolphin;

import org.dolphin.pages.MainPage;
import org.dolphin.pages.MenuPage;
import org.dolphin.pages.SelectMenuPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class SelectMenuTest extends Base {
    public WebDriver driver;
    MainPage mainPage;
    SelectMenuPage selectMenuPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        selectMenuPage = new SelectMenuPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifySelectMenuTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.WIDGETS.toString(), SectionFamily.SubSection.SELECT_MENU.toString());
        selectMenuPage.handlingSelectMenus();
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
