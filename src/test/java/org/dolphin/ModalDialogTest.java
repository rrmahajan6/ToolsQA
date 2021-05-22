package org.dolphin;

import org.dolphin.pages.FramesPage;
import org.dolphin.pages.MainPage;
import org.dolphin.pages.ModalDialogPage;
import org.dolphin.utilities.DataUtils.DataSource;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ModalDialogTest extends Base {
    public WebDriver driver;
    public MainPage mainPage;
    public ModalDialogPage modalDialogPage;

    @BeforeClass
    public void init() {
        driver = getDriver();
        mainPage = new MainPage(driver);
        modalDialogPage = new ModalDialogPage(driver);
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        if (!DataSource.isTestRunnable(m.getName(), excelReader)) {
            throw new SkipException("RunMode is set to not executable");
        }
    }

    @Test
    public void verifyModalTest() {
        mainPage.platformToSections(true, SectionFamily.MainSection.ALERTS_FRAME_WINDOWS.toString(), SectionFamily.SubSection.MODAL_DIALOGS.toString());
        modalDialogPage.modalDialogHandling();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
