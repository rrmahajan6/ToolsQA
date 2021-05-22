package org.dolphin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CheckBoxPage {
    WebDriver driver;
    @FindBy(xpath = "//button[@title='Toggle']")
    private WebElement toggleButton;
    @FindBy(xpath = "//span[text()='Home']")
    private WebElement homeCheckBox;
    @FindBy(xpath = "//span[text()='Desktop']")
    private WebElement desktopCheckbox;
    @FindBy(xpath = "//span[text()='Documents']")
    private WebElement documentsCheckbox;
    @FindBy(xpath = "//span[text()='Downloads']")
    private WebElement downloadsCheckbox;

    public CheckBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void expandAllCheckBoxes() {
        toggleButton.click();
        homeCheckBox.click();
        desktopCheckbox.click();
        documentsCheckbox.click();
        desktopCheckbox.click();
    }
}

