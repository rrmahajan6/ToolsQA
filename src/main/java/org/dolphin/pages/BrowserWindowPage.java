package org.dolphin.pages;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

public class BrowserWindowPage {
    WebDriver driver;
    @FindBy(xpath = "//button[text()='New Tab']")
    public WebElement newTab;
    @FindBy(xpath = "//button[text()='New Window']")
    public WebElement newWindow;
    @FindBy(xpath = "//button[text()='New Window Message']")
    public WebElement newWindowMessage;

    public BrowserWindowPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void windowHandling() {
        newTab.click();
        newWindow.click();
        newWindowMessage.click();
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> itr = allWindows.iterator();
        String child;
        int i = 0;
        while (itr.hasNext()) {
            child = itr.next();
            if (!parentWindow.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
}
