package org.dolphin;

import org.dolphin.pages.BookStorePage;
import org.dolphin.pages.LoginPage;
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

public class BookStoreApplicationTest extends Base {
    WebDriver driver;
    MainPage mainPage;
    BookStorePage bookStorePage;

    @BeforeClass
    public void initilization() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        bookStorePage = new BookStorePage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    public void verifyBookStore() {
        mainPage.platformToSections(true, SectionFamily.MainSection.BOOK_STORE_APPLICATION.toString(), SectionFamily.SubSection.BOOKSTORE.toString());
        bookStorePage.openPurchaseLink("Git Pocket Guide");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
