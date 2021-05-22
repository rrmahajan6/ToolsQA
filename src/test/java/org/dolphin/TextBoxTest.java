package org.dolphin;

import org.dolphin.pages.MainPage;
import org.dolphin.pages.TextBoxPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class TextBoxTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public TextBoxPage textBoxPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        textBoxPage = new TextBoxPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void verifyTextBoxTest(Hashtable<String, String> dataTable) {
        mainPage.platformToSections(true, SectionFamily.MainSection.ELEMENTS.toString(), SectionFamily.SubSection.TEXT_BOX.toString());
        textBoxPage.fillingData(dataTable.get("firstName"), dataTable.get("lastName"), dataTable.get("cAdd"), dataTable.get("pAdd"));
        Reporter.log("Test case is completed", true);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
