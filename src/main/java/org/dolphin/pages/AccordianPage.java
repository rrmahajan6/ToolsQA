package org.dolphin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AccordianPage {
    WebDriver driver;
    @FindBy(xpath = "//div[@id='section1Heading']")
    public WebElement firstHeading;
    @FindBy(xpath = "//div[@id='section2Heading']")
    public WebElement secondHeading;
    @FindBy(xpath = "//div[@id='section3Heading']")
    public WebElement thirdHeading;
    @FindBy(xpath = "(//div[@id='section1Heading']//parent::div//following-sibling::div)[1]")
    public WebElement firstTabCollapsed;
    @FindBy(xpath = "(//div[@id='section1Heading']//parent::div//following-sibling::div)[2]")
    public WebElement secondTabCollapsed;
    @FindBy(xpath = "(//div[@id='section1Heading']//parent::div//following-sibling::div)[3]")
    public WebElement thirdTabCollapsed;


    public AccordianPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void handlingAccordian() {
        if (firstTabCollapsed.getAttribute("class").equalsIgnoreCase("collapse show")) {
            Assert.assertTrue(true, "something went wrong");
        }
        firstHeading.click();
        if (firstTabCollapsed.getAttribute("class").equalsIgnoreCase("collapse")) {
            Assert.assertTrue(true);
        }
        if (secondTabCollapsed.getAttribute("class").equalsIgnoreCase("collapse")) {
            Assert.assertTrue(true);
        }
        secondHeading.click();
        if (secondTabCollapsed.getAttribute("class").equalsIgnoreCase("collapse show")) {
            Assert.assertTrue(true);
        }
        if (thirdTabCollapsed.getAttribute("class").equalsIgnoreCase("collapse")) {
            Assert.assertTrue(true);
        }
        thirdHeading.click();
        if (thirdTabCollapsed.getAttribute("class").equalsIgnoreCase("collapse show")) {
            Assert.assertTrue(true);
        }
    }
}
