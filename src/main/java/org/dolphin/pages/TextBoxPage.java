package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Hashtable;

public class TextBoxPage {
    public WebDriver driver;
    @FindBy(id = "userName")
    private WebElement userName;
    @FindBy(id = "userEmail")
    private WebElement userEmail;
    @FindBy(id = "currentAddress")
    private WebElement currentAddress;
    @FindBy(id = "permanentAddress")
    private WebElement permanentAddress;
    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submit;
    String submittedData = "//p[text()='%s']";

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillingData(String fName,String email,String pAdd,String cAdd) {
        userName.sendKeys(fName);
        userEmail.sendKeys(email);
        currentAddress.sendKeys(pAdd);
        permanentAddress.sendKeys(cAdd);
        SeleniumUtils.scrollElementIntoView(driver,submit);
        SeleniumUtils.performClick(driver,submit);
        SeleniumUtils.waitForPageLoaded(driver);
        driver.findElement(By.xpath(String.format(submittedData,fName)));
        driver.findElement(By.xpath(String.format(submittedData,email)));
        driver.findElement(By.xpath(String.format(submittedData,pAdd)));
        driver.findElement(By.xpath(String.format(submittedData,cAdd)));
    }

}
