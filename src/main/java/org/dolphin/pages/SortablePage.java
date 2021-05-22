package org.dolphin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SortablePage {
    WebDriver driver;
    @FindBy(xpath = "//a[@id='demo-tab-list']")
    WebElement itemList;
    @FindBy(xpath = "//a[@id='demo-tab-grid']")
    WebElement itemGrid;
    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[text()='One']")
    WebElement firstListItem;
    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[text()='Two']")
    WebElement secondListItem;
    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[text()='Three']")
    WebElement thirdListItem;
    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[text()='Four']")
    WebElement fourthListItem;
    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[text()='Five']")
    WebElement fifthListItem;
    @FindBy(xpath = "//div[@class='vertical-list-container mt-4']//div[text()='Six']")
    WebElement sixthListItem;


    public SortablePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void handlingListSorting(){
    }
}
