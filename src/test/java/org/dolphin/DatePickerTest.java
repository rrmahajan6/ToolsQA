package org.dolphin;

import org.dolphin.pages.AutoCompletePage;
import org.dolphin.pages.DatePickerPage;
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
import java.util.Hashtable;

public class DatePickerTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public DatePickerPage datePickerPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        datePickerPage = new DatePickerPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void verifyDatePicker(Hashtable<String, String> dataTable) {
        mainPage.platformToSections(true, SectionFamily.MainSection.WIDGETS.toString(), SectionFamily.SubSection.DATE_PICKER.toString());
        datePickerPage.handlingDates(dataTable.get("date").split("\\.")[0], dataTable.get("month"), dataTable.get("year").split("\\.")[0], "16:30");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
