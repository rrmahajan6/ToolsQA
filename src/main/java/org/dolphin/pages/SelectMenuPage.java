package org.dolphin.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.security.Key;

public class SelectMenuPage {
    WebDriver driver;
    @FindBy(xpath = "//input[@id='react-select-2-input']")
    WebElement selectOption;
    @FindBy(xpath = "//input[@id='react-select-3-input']")
    WebElement selectTitle;
    @FindBy(xpath = "//select[@id='oldSelectMenu']")
    WebElement oldSelectMenu;
    @FindBy(xpath = "//input[@id='react-select-4-input']")
    WebElement mutlipleOptions;
    @FindBy(xpath = "//select[@id='cars']")
    WebElement carsList;

    public SelectMenuPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void handlingSelectMenus(){
        selectOption.sendKeys("Group 1, option 1");
        selectOption.sendKeys(Keys.RETURN);
        selectTitle.sendKeys("Dr.");
        selectTitle.sendKeys(Keys.RETURN);
        Select select=new Select(oldSelectMenu);
        select.selectByVisibleText("White");
        mutlipleOptions.sendKeys("Green");
        mutlipleOptions.sendKeys(Keys.RETURN);
        mutlipleOptions.sendKeys("White");
        mutlipleOptions.sendKeys(Keys.RETURN);
        Select cars=new Select(carsList);
        cars.selectByVisibleText("Audi");
    }
}
