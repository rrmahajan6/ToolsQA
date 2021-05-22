package org.dolphin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TabsPage {
    WebDriver driver;
    @FindBy(xpath = "//a[@id='demo-tab-what']")
    public WebElement whatTab;
    @FindBy(xpath = "//a[@id='demo-tab-origin']")
    public WebElement originTab;
    @FindBy(xpath = "//a[@id='demo-tab-use']")
    public WebElement useTab;

    public TabsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void tabHandling(){
        whatTab.click();
        originTab.click();
        useTab.click();
    }

}
