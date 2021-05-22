package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ButtonsPage {
    WebDriver driver;
    @FindBy(xpath = "//button[@id='doubleClickBtn']")
    private WebElement doubleClickMeButton;
    @FindBy(xpath = "//button[@id='rightClickBtn']")
    private WebElement rightClickMeButton;
    @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement singleClickMeButton;

    public ButtonsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickingButtons(){
        SeleniumUtils.doubleClickElement(driver,doubleClickMeButton);
        SeleniumUtils.rightClickElement(driver,rightClickMeButton);
        SeleniumUtils.seleniumClick(driver,singleClickMeButton);
    }
}
