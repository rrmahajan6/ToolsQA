package org.dolphin;

import org.dolphin.pages.AlertsPage;
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
import java.security.SecureRandom;

public class AlertTest extends Base {
    public WebDriver driver;
    AlertsPage alertsPage;
    MainPage mainPage;

    @BeforeClass
    public void initilization() {
        driver = getDriver();
        alertsPage = new AlertsPage(driver);
        mainPage = new MainPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyAlertHandling() {
        mainPage.platformToSections(true, SectionFamily.MainSection.ALERTS_FRAME_WINDOWS.toString(), SectionFamily.SubSection.ALERTS.toString());
        alertsPage.handlingAlerts("rahul");
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

}
