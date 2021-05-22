package org.dolphin.pages;

import org.dolphin.Base;
import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class MainPage extends Base {
    WebDriver driver;
    LoginPage loginPage;
    @FindBy(xpath = "//label[text()='You are already logged in. View your ']")
    public List<WebElement> userLoggedInMessage;
    String mainSections = "//h5[text()='%s']//parent::div//parent::div";
    String subSections = "//span[text()='%s']";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void platformToSections(boolean login, String mainSectionName, String subSectionName) {
        if (login) {
            driver.get("https://demoqa.com/login");
            if (userLoggedInMessage.size() > 0) {
                Reporter.log("user is already logged in ");
            } else {
                loginPage = new LoginPage(driver);
                loginPage.loginOperations("tester", "Welcome@123");
                SeleniumUtils.waitForPageLoaded(driver);
                Assert.assertTrue(driver.getCurrentUrl().contains("profile"));
            }
        }
        driver.get("https://demoqa.com");
        WebElement mainSect = driver.findElement(By.xpath(String.format(mainSections, mainSectionName)));
        SeleniumUtils.scrollToElementAndClick(driver, mainSect);
        WebElement subSect = driver.findElement(By.xpath(String.format(subSections, subSectionName)));
        SeleniumUtils.scrollToElementAndClick(driver, subSect);
    }
}
