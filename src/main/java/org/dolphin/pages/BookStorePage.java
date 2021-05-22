package org.dolphin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BookStorePage {
    WebDriver driver;
    String bookNameElement="//a[text()='%s']";
    public BookStorePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public AddToCollection openPurchaseLink(String bookName){
        driver.findElement(By.xpath(String.format(bookNameElement,bookName))).click();
        return new AddToCollection(driver);
    }

}
