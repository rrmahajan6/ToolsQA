package org.dolphin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProfilePage {
    WebDriver driver;
   String bookNameInTable="//a[text()='%s']";

   public ProfilePage(WebDriver driver){
       this.driver=driver;
       PageFactory.initElements(driver,this);
   }
   public void checkIfBookPresentInTable(String... books){
       for(int i=0;i<books.length;i++){
          if(driver.findElement(By.xpath(String.format(bookNameInTable,books[i]))).isDisplayed()){
              Assert.assertTrue(true);
          }
       }
   }
}
