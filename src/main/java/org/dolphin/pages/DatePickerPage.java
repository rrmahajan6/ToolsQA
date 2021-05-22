package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import sun.jvm.hotspot.debugger.Page;

import java.util.Hashtable;

public class DatePickerPage {
    WebDriver driver;
    @FindBy(xpath = "//input[@id='datePickerMonthYearInput']")
    public WebElement selectDateField;
    @FindBy(xpath = "//input[@id='dateAndTimePickerInput']")
    public WebElement selectDateField2;
    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    public WebElement monthSelector;
    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    public WebElement yearSelector;
    String dateSelector="//div[@class='react-datepicker__month']/div/div[text()='%s']";
    @FindBy(xpath = "//input[@id='dateAndTimePickerInput']")
    public WebElement selectDateTimeField;
    String monthStringList="//div[@class='react-datepicker__month-dropdown']/div[text()='%s']";
    String yearStringList="//div[@class='react-datepicker__year-dropdown']/div[text()='%s']";
    String timeStringList="//div[@class='react-datepicker__time-box']/ul/li[text()='%s']";
    @FindBy(xpath = "//span[@class='react-datepicker__month-read-view--down-arrow']")
    public WebElement monthSelector2;
    @FindBy(xpath = "//span[@class='react-datepicker__year-read-view--down-arrow']")
    public WebElement yearSelector2;
    @FindBy(xpath = "//div[text()='Date And Time']")
    public WebElement dateAndTimeText;

    public DatePickerPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void handlingDates(String date,String month,String year,String time){
        selectDateField.click();
        SeleniumUtils.selectFromDropDown(driver,monthSelector,month);
        SeleniumUtils.selectFromDropDown(driver,yearSelector,year);
        driver.findElement(By.xpath(String.format(dateSelector,date))).click();
        selectDateField2.click();
        monthSelector2.click();
        driver.findElement(By.xpath(String.format(monthStringList,month))).click();
        yearSelector2.click();
        driver.findElement(By.xpath(String.format(yearStringList,year))).click();
        driver.findElement(By.xpath(String.format(timeStringList,time))).click();
    }
}
