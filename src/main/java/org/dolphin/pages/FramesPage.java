package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FramesPage {
    WebDriver driver;
    @FindBy(xpath = "//iframe[@id='frame1']")
    public WebElement frame1;
    @FindBy(xpath = "//iframe[@id='frame2']")
    public WebElement frame2;
    @FindBy(xpath = "//h1[@id='sampleHeading']")
    public WebElement heading;

    public FramesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void frameHandling() {
        driver.switchTo().frame(frame1);
        System.out.println(heading.getText());
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frame2);
        System.out.println(heading.getText());

    }
}
