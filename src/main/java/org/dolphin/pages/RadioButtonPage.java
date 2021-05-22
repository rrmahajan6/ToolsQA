package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RadioButtonPage {
    WebDriver driver;
    @FindBy(xpath = "//label[@for='yesRadio']")
    private WebElement yesRadioButton;
    @FindBy(xpath = "//label[@for='noRadio']")
    private WebElement noRadioButton;
    @FindBy(xpath = "//label[@for='impressiveRadio']")
    private WebElement impressiveRadioButton;
    @FindBy(xpath = "//p[text()='You have selected ']")
    private WebElement youHaveSelectedText;
    String yesOrImpressive = "//span[text()='%s']";

    public RadioButtonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectButton() {
        yesRadioButton.click();
        noRadioButton.click();
        impressiveRadioButton.click();
        if (driver.findElement(By.xpath(String.format(yesOrImpressive, "Impressive"))).isDisplayed()) {
            Assert.assertTrue(true);
        }

    }

}
