package org.dolphin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.jvm.hotspot.debugger.Page;

public class LinksPage {
    WebDriver driver;
    @FindBy(xpath = "//a[text()='Home' and @id='simpleLink']")
    public WebElement homePageLink;
    @FindBy(xpath = "//a[text()='h1Y8I' and @id='dynamicLink']")
    public WebElement dynamicLinkText;
    @FindBy(xpath = "//a[text()='Created' and @id='created']")
    public WebElement createLink;
    @FindBy(xpath = "//a[text()='No Content' and @id='no-content']")
    public WebElement noContent;

    public LinksPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void linkOpennings(){
        homePageLink.click();
        createLink.click();
        noContent.click();
    }
}
