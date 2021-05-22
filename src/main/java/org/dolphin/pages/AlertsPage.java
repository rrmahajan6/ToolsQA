package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage {
    WebDriver driver;
    Alert alert;
    @FindBy(xpath = "//button[@id='alertButton']")
    public WebElement alertButton;
    @FindBy(xpath = "//button[@id='timerAlertButton']")
    public WebElement timeAlertButton;
    @FindBy(xpath = "//button[@id='confirmButton']")
    public WebElement confirmButton;
    @FindBy(xpath = "//button[@id='promtButton']")
    public WebElement promptButton;


    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void handlingAlerts(String alertInput) {
        alertButton.click();
        SeleniumUtils.waitForAlertToBeAvailable(driver,10).accept();
        timeAlertButton.click();
        SeleniumUtils.waitForAlertToBeAvailable(driver,10).accept();
        confirmButton.click();
        SeleniumUtils.waitForAlertToBeAvailable(driver,10).dismiss();
        promptButton.click();
        Alert alert=SeleniumUtils.waitForAlertToBeAvailable(driver,10);
        alert.sendKeys(alertInput);
        alert.accept();
    }

}
