package org.dolphin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ToolTipsPage {
    WebDriver driver;
    @FindBy(xpath = "//button[text()='Hover me to see']")
    public WebElement buttonForHover;
    @FindBy(xpath = "//input[@placeholder='Hover me to see']")
    public WebElement textBoxHover;
    @FindBy(xpath = "//div[text()='You hovered over the Button']")
    public WebElement buttonHoveringText;
    @FindBy(xpath = "//div[text()='You hovered over the text field']")
    public WebElement inputFieldHoveringText;

    public ToolTipsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void toolTipHovering(){
        Actions actions=new Actions(driver);
        actions.moveToElement(buttonForHover).build().perform();
        Assert.assertTrue(buttonHoveringText.getText().equalsIgnoreCase("You hovered over the Button"));
        actions.moveToElement(textBoxHover).build().perform();
        Assert.assertTrue(inputFieldHoveringText.getText().equalsIgnoreCase("You hovered over the text field"));
    }

}
