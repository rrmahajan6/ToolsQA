package org.dolphin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebTablesPage {
    WebDriver driver;
    @FindBy(id = "addNewRecordButton")
    private WebElement addNewRecordButton;
    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement recordFirstName;
    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement recordLastName;
    @FindBy(xpath = "//input[@id='userEmail']")
    private WebElement recordEmail;
    @FindBy(xpath = "//input[@id='age']")
    private WebElement recordAge;
    @FindBy(xpath = "//input[@id='salary']")
    private WebElement recordSalary;
    @FindBy(xpath = "//input[@id='department']")
    private WebElement recordDept;
    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitButton;
    String cellValue = "//div[@class='rt-tbody']/div/div/div[text()='%s']";

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createNewRecord(String firstName, String lastName, String Email, String age, String salary, String dept) {
        addNewRecordButton.click();
        recordFirstName.sendKeys(firstName);
        recordLastName.sendKeys(lastName);
        recordEmail.sendKeys(Email);
        recordAge.sendKeys(age);
        recordSalary.sendKeys(salary);
        recordDept.sendKeys(dept);
        submitButton.click();
        driver.findElement(By.xpath(String.format(cellValue, firstName)));
        driver.findElement(By.xpath(String.format(cellValue, lastName)));
        driver.findElement(By.xpath(String.format(cellValue, Email)));
        driver.findElement(By.xpath(String.format(cellValue, age)));
        driver.findElement(By.xpath(String.format(cellValue, salary)));
        driver.findElement(By.xpath(String.format(cellValue, dept)));
    }
}
