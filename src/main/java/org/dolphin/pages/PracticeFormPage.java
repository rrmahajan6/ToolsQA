package org.dolphin.pages;

import org.dolphin.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.nio.channels.SeekableByteChannel;

public class PracticeFormPage {
    WebDriver driver;
    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement firstName;
    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement lastName;
    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement userEmail;
    @FindBy(xpath = "//label[@for='gender-radio-1']")
    public WebElement maleRadioButton;
    @FindBy(xpath = "//label[@for='gender-radio-2']")
    public WebElement feMaleRadioButton;
    @FindBy(xpath = "//label[@for='gender-radio-3']")
    public WebElement otherRadioButton;
    @FindBy(xpath = "//input[@id='userNumber']")
    public WebElement mobileNumber;
    @FindBy(xpath = "//label[@for='hobbies-checkbox-1']")
    public WebElement sportsCheckbox1;
    @FindBy(xpath = "//label[@for='hobbies-checkbox-2']")
    public WebElement readingCheckbox2;
    @FindBy(xpath = "//label[@for='hobbies-checkbox-3']")
    public WebElement musicCheckbox3;
    @FindBy(xpath = "//input[@id='uploadPicture']")
    public WebElement uploadPictureIntoForm;
    @FindBy(xpath = "//textarea[@id='currentAddress']")
    public WebElement currentAddress;
    @FindBy(xpath = "//div[@class='subjects-auto-complete__control css-yk16xz-control']")
    public WebElement multiSelectDropDown;
    @FindBy(xpath = "//button[@id='submit']")
    public WebElement submitButton;
    @FindBy(xpath = "//input[@id='react-select-3-input']")
    public WebElement stateDropDown;
    @FindBy(xpath = "//input[@id='react-select-4-input']")
    public WebElement cityDropDown;
    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    public WebElement monthSelectorDropDown;
    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    public WebElement yearSelectorDropDown;
    @FindBy(xpath = "//input[@id='dateOfBirthInput']")
    public WebElement DOB;
    String dayOfMonth = "//div[@class='react-datepicker__week']/div[text()='%s']";
    @FindBy(xpath = "//input[@id='subjectsInput']")
    public WebElement subjects;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillTheForm(String first, String last, String email, String mobileNum, String month, String yearp, String day, String subject, String state, String city, String cAdd) {
        firstName.sendKeys(first);
        lastName.sendKeys(last);
        userEmail.sendKeys(email);
        mobileNumber.sendKeys(mobileNum);
        maleRadioButton.click();
        feMaleRadioButton.click();
        otherRadioButton.click();
        sportsCheckbox1.click();
        readingCheckbox2.click();
        musicCheckbox3.click();
        uploadPictureIntoForm.sendKeys("/Users/rahul.mahajan/Desktop/Dolphin/src/main/resources/Screenshot 2021-05-10 at 2.31.10 PM.png");
        currentAddress.sendKeys(cAdd);
        DOB.click();
        SeleniumUtils.selectFromDropDown(driver, monthSelectorDropDown, month);
        SeleniumUtils.selectFromDropDown(driver, yearSelectorDropDown, yearp);
        driver.findElement(By.xpath(String.format(dayOfMonth, day))).click();
        subjects.click();
        subjects.sendKeys(subject);
        subjects.sendKeys(Keys.RETURN);
        stateDropDown.sendKeys(state);
        stateDropDown.sendKeys(Keys.RETURN);
        cityDropDown.sendKeys(city);
        cityDropDown.sendKeys(Keys.RETURN);
    }
}
