package org.dolphin.utilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SeleniumUtils {

    private final static Logger logger = Logger.getLogger("SeleniumUtils");

    public static void moveToElementAndClick(WebDriver currentBrowserDriver, WebElement element) {
        Actions actions = new Actions(currentBrowserDriver);
        actions.moveToElement(element).build().perform();
        SeleniumUtils.pause(2);
        element.click();
    }

    public static void moveToElement(WebDriver currentBrowserDriver, WebElement element) {
        Actions actions = new Actions(currentBrowserDriver);
        actions.moveToElement(element).build().perform();
    }

    public static void doubleClickElement(WebDriver currentBrowserDriver, WebElement element) {
        Actions actions = new Actions(currentBrowserDriver);
        actions.doubleClick(element).build().perform();
    }

    public static void rightClickElement(WebDriver currentBrowserDriver, WebElement element) {
        Actions actions = new Actions(currentBrowserDriver);
        actions.contextClick(element).build().perform();
    }

    public static void scrollUpAndClickElement(WebDriver currentBrowserDriver, WebElement elemnt) {
        JavascriptExecutor js = (JavascriptExecutor) currentBrowserDriver;
        js.executeScript("window.scrollBy(0,-250)");
        elemnt.click();
    }

    public static void scrollUp(WebDriver currentBrowserDriver) {
        JavascriptExecutor js = (JavascriptExecutor) currentBrowserDriver;
        js.executeScript("window.scrollTo(0,0)");


    }

    public static void scrollUpByPixels(WebDriver currentBrowserDriver, String scrollUpByPixel) {
        JavascriptExecutor js = (JavascriptExecutor) currentBrowserDriver;
        js.executeScript("window.scrollBy(0,-" + scrollUpByPixel + ")");
    }

    public static void scrollDown(WebDriver currentBrowserDriver) {
        JavascriptExecutor js = (JavascriptExecutor) currentBrowserDriver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    public static void scrollDown(WebDriver currentBrowserDriver, String scrollByWhatPixel) {
        JavascriptExecutor js = (JavascriptExecutor) currentBrowserDriver;
        js.executeScript("window.scrollBy(0," + scrollByWhatPixel + ")");
    }

    public static void scrollToBottom(WebDriver currentBrowserDriver) {
        JavascriptExecutor js = (JavascriptExecutor) currentBrowserDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void scrollDownLittle(WebDriver currentBrowserDriver) {
        JavascriptExecutor js = (JavascriptExecutor) currentBrowserDriver;
        js.executeScript("window.scrollBy(0,400)");
    }

    public static void scrollElementIntoView(WebDriver currentBrowserDriver, WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) currentBrowserDriver;
        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }


    /*This method will delete files older than n days
     * *this method is capable to take multiple folders at a time**/

    public static void deleteOlderFiles(int days, String... folderPath) {
        for (String folder : folderPath) {
            String dirPath = System.getProperty("user.dir") + folder;
            File directotry = new File(dirPath);
            if (directotry.exists()) {
                File[] files = directotry.listFiles();

                final long purgeTime =
                        System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000);

                for (File file : files) {
                    if ((file.lastModified() < purgeTime) && (file.getName().endsWith(".html")) || (file.getName().endsWith(".png"))) {
                        file.delete();
                    }
                }

                if (folder.equals("/reports/")) {
                    files = directotry.listFiles();
                    if (files.length > 10) {
                        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

                        for (int i = 10; i < files.length; i++) {
                            files[i].delete();
                        }
                    }
                }
            }
        }
    }

    public static String getScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public static void elementHighlighter(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    public static boolean isClickable(WebElement el, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitForElementToLoad(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Reporter.log("Inside waitForElementToLoad() catch -Element was not visibile within given time", true);
        }
    }

    /*Below method returns element one by one even after page refresh without any stale element exception*/

    public static WebElement getElementsOnPageRefresh(WebDriver driver, String locator) throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        WebElement elementToReturn = null;
        for (WebElement element : elements) {
            try {
                SeleniumUtils.elementHighlighter(driver, element);
                elementToReturn = element;
                break;
            } catch (StaleElementReferenceException ex) {
                elements = driver.findElements(By.xpath(locator));
                elementToReturn = elements.get(0);
                SeleniumUtils.elementHighlighter(driver, elementToReturn);
                break;
            }
        }
        return elementToReturn;
    }

    public static void performClick(WebDriver driver, WebElement elementToBeClicked) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", elementToBeClicked);
    }

    public static String getElementText(WebDriver driver, WebElement elementToBeRead) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        SeleniumUtils.elementHighlighter(driver, elementToBeRead);
        wait.until(ExpectedConditions.visibilityOf(elementToBeRead));
        return elementToBeRead.getText();
    }

    public static void enterInputText(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        SeleniumUtils.elementHighlighter(driver, element);
        boolean textBoxEmpty = false;
        element.clear();
        while (!textBoxEmpty) {
            element.clear();
            textBoxEmpty = true;
        }
        element.sendKeys(text);
    }

    public static WebElement getVisibility(By locator, int timeout, WebDriver driver) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public static void openElementInNewTab(WebDriver driver, WebElement ele) {
        String openLinkInNewTab = Keys.chord(Keys.COMMAND, Keys.RETURN);
        ele.sendKeys(openLinkInNewTab);
    }


    public static void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition expectation = new ExpectedCondition() {
            @Override
            public Object apply(Object o) {
                return null;
            }

            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript(
                        "return document.readyState").equals("complete");
            }
        };
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        try {
            wait.until(expectation);
        } catch (Exception error) {
            //logger.fatal("something went wrong", error);
        }
    }

    /*Below method will hold the execution for specified time*/
    public static void pause(long timeOut) {
        try {
            TimeUnit.SECONDS.sleep(timeOut);
        } catch (InterruptedException ex) {
            //
        }
    }


    public static void clickElementWithoutStaleException(WebDriver driver, WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement localElement = element;
        int counter = 0;
        while (counter < 5) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(localElement));
                localElement.click();
            } catch (StaleElementReferenceException se) {
                counter++;
                localElement = element;
            }
        }

    }

    public static void switchToLastWindowHandle(WebDriver driver) {
        String lastWinHandle = "";
        Set<String> winHandles = driver.getWindowHandles();
        for (String winHandle : winHandles) {
            lastWinHandle = winHandle;
        }
        driver.switchTo().window(lastWinHandle);
    }

    public static String getCurrentLocalDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(localDateTime);
    }


    public static boolean isNullOrEmpty(Object obj) {
        if (!(obj instanceof String) && obj == null) {
            return true;
        } else {
            try {
                if (obj == null || obj.toString().isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            } catch (NullPointerException e) {
                //  logger.error("isNullOrEmpty: Null string while checking the the string is null or Empty.");
                return true;
            }
        }
    }


    public static void waitForElementToLoad(WebDriver driver, WebElement element, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Reporter.log("Inside waitForElementToLoad() catch -Element was not visibile within given time", true);
        }
    }

    public static void waitForElementToClickable(WebDriver driver, WebElement element, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Reporter.log("Inside waitForElementToClickable() catch -Element was not clickable within given time", true);
        }
    }

    public static void waitAndClick(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /***
     * This method checks whether an alert is present, if present switches the driver to alert
     * NOTE : This method only switches to alert and does not handle/dismiss it, caller needs to
     * take care of alert depending upon the requirement
     * @param driver
     * @return Object of #Alert which if present else returns null
     */
    public static Alert switchToAlertIfPresent(WebDriver driver) {

        Alert alert;
        try {
            alert = driver.switchTo().alert();
            Reporter.log("Switching to alert", true);
        } catch (NoAlertPresentException n) {
            alert = null;
        }
        return alert;
    }

    /***
     * This method clears the text for Input field having type as text
     * Use this method only if {@link WebElement#clear()} does not work
     * @param driver - current Webdriver
     * @param element - Element to be cleared
     */
    public static void clearTextForInputTypeTextField(WebDriver driver, WebElement element) {

        while (element.getAttribute("value").length() != 0) {
            element.sendKeys(Keys.BACK_SPACE);
        }

    }

    public static void clearChromeCache(WebDriver driver) {

        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("chrome://settings/clearBrowserData");
        driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
        SeleniumUtils.waitForPageLoaded(driver);
        driver.close();
        driver.switchTo().window(tabs.get(0));
        Reporter.log("cleared cached sucessfully");
    }

    public static void seleniumClick(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void scrollToElementAndClick(WebDriver driver, WebElement element) {
        SeleniumUtils.scrollElementIntoView(driver, element);
        SeleniumUtils.performClick(driver, element);
    }

    public static Alert waitForAlertToBeAvailable(WebDriver driver, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert;
    }

    public static void selectFromDropDown(WebDriver driver, WebElement dropdownElement, String valueToSelect) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(valueToSelect);
    }
}
