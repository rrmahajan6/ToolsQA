package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicPropertiesPage {
    WebDriver driver;
    @FindBy(xpath = "//button[text()='Will enable 5 seconds']")
    public WebElement waitFor5SecondsButton;
    @FindBy(xpath = "//button[text()='Color Change']")
    public WebElement colorChangeButton;
    @FindBy(xpath = "//button[text()='Visible After 5 Seconds']")
    public WebElement visibleAfter5SecondsButton;

    public DynamicPropertiesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dynamicButtons() {
        SeleniumUtils.waitForElementToClickable(driver, waitFor5SecondsButton, 10);
        waitFor5SecondsButton.click();
        colorChangeButton.click();
        SeleniumUtils.waitForElementToClickable(driver, visibleAfter5SecondsButton, 10);
        visibleAfter5SecondsButton.click();
    }
}
