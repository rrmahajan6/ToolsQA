package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class LoginPage {
    WebDriver driver;
    @FindBy(xpath = "//input[@id='userName']")
    public WebElement userName;
    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;
    @FindBy(xpath = "//button[@id='login']")
    public WebElement loginButton;
    @FindBy(xpath = "//p[text()='Invalid username or password!']")
    public WebElement invalidLoginMessage;
    @FindBy(xpath = "//button[text()='Log out']")
    public List<WebElement> logOutButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginOperations(String uName, String pWord) {
        if(logOutButton.size()>0){
            logOutButton.get(0).click();
        }
        userName.sendKeys(uName);
        password.sendKeys(pWord);
        loginButton.click();
    }
}
