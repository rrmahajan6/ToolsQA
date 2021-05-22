package org.dolphin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderPage {
    WebDriver driver;
    @FindBy(xpath = "//input[@class='range-slider range-slider--primary']")
    public WebElement slider;

    public SliderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void handlingSlider(){
        Actions actions=new Actions(driver);
        actions.moveToElement(slider).clickAndHold().moveByOffset(100,0).release().perform();
    }
}
