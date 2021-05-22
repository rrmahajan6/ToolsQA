package org.dolphin.pages;

import org.apache.commons.math3.analysis.function.Add;
import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCollection {
    WebDriver driver;
    @FindBy(xpath = "//button[text()='Add To Your Collection']")
    WebElement addToCollection;
    @FindBy(xpath = "//button[text()='Back To Book Store']")
    WebElement backToBookStore;
    @FindBy(xpath = "//button[text()='Log out']")
    WebElement logOutButton;

    public AddToCollection(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void addBookToStore(){
        SeleniumUtils.scrollToElementAndClick(driver,addToCollection);
        SeleniumUtils.waitForAlertToBeAvailable(driver,10).accept();
    }
}
