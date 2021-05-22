package org.dolphin.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadDownloadPage {
    WebDriver driver;
    @FindBy(xpath = "//input[@id='uploadFile']")
    public WebElement uploadFileElement;
    @FindBy(xpath = "//a[text()='Download']")
    public WebElement downloadFileElement;

    public UploadDownloadPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void uploadFile(){
        downloadFileElement.click();
        uploadFileElement.sendKeys("/Users/rahul.mahajan/Desktop/Dolphin/src/main/resources/Screenshot 2021-05-10 at 2.31.10 PM.png");
    }
}
