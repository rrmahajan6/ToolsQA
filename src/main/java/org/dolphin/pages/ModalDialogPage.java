package org.dolphin.pages;

import org.dolphin.Base;
import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModalDialogPage extends Base {
    WebDriver driver;
    @FindBy(xpath = "//button[@id='closeSmallModal']")
    public WebElement closeSmallModal;
    @FindBy(xpath = "//button[@id='showSmallModal']")
    public WebElement showSmallModal;
    @FindBy(xpath = "//button[@id='showLargeModal']")
    public WebElement showLargeModal;
    @FindBy(xpath = "//button[@id='closeLargeModal']")
    public WebElement closeLargeModal;

    public ModalDialogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void modalDialogHandling() {
        showSmallModal.click();
        closeSmallModal.click();
        showLargeModal.click();
        SeleniumUtils.waitForPageLoaded(driver);
        closeLargeModal.click();
    }
}
