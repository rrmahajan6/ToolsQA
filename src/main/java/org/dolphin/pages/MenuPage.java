package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {
    WebDriver driver;
    @FindBy(linkText = "Main Item 2")
    WebElement menuLinkItem2;
    @FindBy(linkText = "SUB SUB LIST »")
    WebElement superSubItemLink;
    @FindBy(linkText = "Sub Sub Item 1")
    WebElement superSubItem1;

    public MenuPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void handlingMenus(){
        SeleniumUtils.moveToElement(driver,menuLinkItem2);
        SeleniumUtils.moveToElement(driver,superSubItemLink);
        superSubItem1.click();
    }

}
