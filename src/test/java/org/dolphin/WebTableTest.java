package org.dolphin;

import org.dolphin.pages.CheckBoxPage;
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
import java.util.Hashtable;

public class WebTableTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public WebTablesPage webTablesPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        webTablesPage = new WebTablesPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void verifyWebTableTest(Hashtable<String, String> dataTable) {
        mainPage.platformToSections(true, SectionFamily.MainSection.ELEMENTS.toString(), SectionFamily.SubSection.WEB_TABLES.toString());
        webTablesPage.createNewRecord(dataTable.get("firstName"), dataTable.get("lastName"), dataTable.get("email"), dataTable.get("age").split("\\.")[0], dataTable.get("salary").split("\\.")[0], dataTable.get("dept"));
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
