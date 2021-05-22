package org.dolphin.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutoCompletePage {
    WebDriver driver;
    @FindBy(xpath = "//input[@id='autoCompleteMultipleInput']")
    public WebElement multiColorDropDown;
    @FindBy(xpath = "//input[@id='autoCompleteSingleInput']")
    public WebElement singleColorDropDown;

    public AutoCompletePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void autoCompleteHandling(){
        multiColorDropDown.sendKeys("Red");
        multiColorDropDown.sendKeys(Keys.RETURN);
        multiColorDropDown.sendKeys("Yellow");
        multiColorDropDown.sendKeys(Keys.RETURN);
        multiColorDropDown.sendKeys("Blue");
        multiColorDropDown.sendKeys(Keys.RETURN);
        singleColorDropDown.sendKeys("Yellow");
        singleColorDropDown.sendKeys(Keys.RETURN);
    }
}
