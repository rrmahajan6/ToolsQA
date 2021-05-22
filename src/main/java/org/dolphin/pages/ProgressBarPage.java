package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProgressBarPage {
    WebDriver driver;
    @FindBy(xpath = "//button[text()='Start']")
    public WebElement startButton;
    @FindBy(xpath = "//button[text()='Stop']")
    public WebElement stopButton;
    @FindBy(xpath = "//button[@id='resetButton']")
    public WebElement resetButton;

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void handlingProgressbar(){
        startButton.click();
        SeleniumUtils.waitForElementToClickable(driver,resetButton,20);
        resetButton.click();
        startButton.click();
    }
}
