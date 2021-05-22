package org.dolphin;

import org.dolphin.pages.*;
import org.dolphin.utilities.DataUtils.DataSource;
import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class LoginTest extends Base {
    WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    AddToCollection add;
    ProfilePage profilePage;
    BookStorePage bookStorePage;

    @BeforeClass
    public void initilization() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void verifyBookPurchaseFlowTest(Hashtable<String, String> dataTable) {
        bookStorePage = new BookStorePage(driver);
        add = bookStorePage.openPurchaseLink(dataTable.get("bookName"));
        add.addBookToStore();
        mainPage.platformToSections(false, SectionFamily.MainSection.BOOK_STORE_APPLICATION.toString(), SectionFamily.SubSection.PROFILE.toString());
        profilePage = new ProfilePage(driver);
        profilePage.checkIfBookPresentInTable(dataTable.get("bookName"));
    }

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void loginToApp(Hashtable<String, String> dataTable) {
        mainPage.platformToSections(false, SectionFamily.MainSection.BOOK_STORE_APPLICATION.toString(), SectionFamily.SubSection.LOGIN.toString());
        loginPage.loginOperations(dataTable.get("user"), dataTable.get("pass"));
        SeleniumUtils.waitForPageLoaded(driver);
        Assert.assertTrue(driver.getCurrentUrl().contains("profile"));
    }

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void invalidCredentialsTest(Hashtable<String, String> dataTable) {
        mainPage.platformToSections(false, SectionFamily.MainSection.BOOK_STORE_APPLICATION.toString(), SectionFamily.SubSection.LOGIN.toString());
        loginPage.loginOperations(dataTable.get("user"), dataTable.get("pass"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        Assert.assertTrue(loginPage.invalidLoginMessage.isDisplayed(), "something went wrong");
    }

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void verifyBlankUserNameTest(Hashtable<String, String> dataTable) {
        mainPage.platformToSections(false, SectionFamily.MainSection.BOOK_STORE_APPLICATION.toString(), SectionFamily.SubSection.LOGIN.toString());
        loginPage.loginOperations("", dataTable.get("pass"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        Assert.assertTrue(loginPage.userName.getAttribute("class").contains("mr-sm-2 is-invalid form-control"), "something went wrong");
    }


    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void verifyBlankPasswordTest(Hashtable<String, String> dataTable) {
        mainPage.platformToSections(false, SectionFamily.MainSection.BOOK_STORE_APPLICATION.toString(), SectionFamily.SubSection.LOGIN.toString());
        loginPage.loginOperations("", dataTable.get("pass"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        Assert.assertTrue(loginPage.password.getAttribute("class").contains("mr-sm-2 is-invalid form-control"), "something went wrong");
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
