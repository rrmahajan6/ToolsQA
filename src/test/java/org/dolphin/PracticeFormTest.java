package org.dolphin;

import org.dolphin.pages.CheckBoxPage;
import org.dolphin.pages.MainPage;
import org.dolphin.pages.PracticeFormPage;
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

public class PracticeFormTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public PracticeFormPage practiceFormPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        practiceFormPage = new PracticeFormPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void verifyFillTheForm(Hashtable<String, String> dataTable) {
        mainPage.platformToSections(true, SectionFamily.MainSection.FORMS.toString(), SectionFamily.SubSection.PRACTICE_FORM.toString());
        practiceFormPage.fillTheForm(dataTable.get("firstName"), dataTable.get("lastName"), dataTable.get("email"), dataTable.get("mobileNum"), dataTable.get("month"), dataTable.get("Years"), dataTable.get("date"), dataTable.get("subject"), dataTable.get("state"), dataTable.get("city"), dataTable.get("cAdd"));
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
