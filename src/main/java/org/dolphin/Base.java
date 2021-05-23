package org.dolphin;

import com.aventstack.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dolphin.utilities.DataUtils.Constants;
import org.dolphin.utilities.DataUtils.ExcelReader;
import org.dolphin.utilities.Reporters.DolphinListeners;
import org.dolphin.utilities.Reporters.DolphinReports;
import org.dolphin.utilities.listeners.AnnotationTransformer;
import org.dolphin.utilities.listeners.PriorityInterceptor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Listeners({DolphinListeners.class, PriorityInterceptor.class, AnnotationTransformer.class})
public class Base {
    public static int retryCount = 0;
    public static ThreadLocal<WebDriver> webDriverThreadSafe = new ThreadLocal<>();
    public static Properties properties;
    public static ExcelReader excelReader = new ExcelReader(Constants.SUITE_XL_PATH);
    private static final Logger LOGGER = LogManager.getLogger(Base.class.getName());
    public static ExtentReports extent;

    @BeforeSuite
    public void beforeSuite() {
        loadPropertiesFiles();
        extent= DolphinReports.getExtentReportInstance();
    }

    public void loadPropertiesFiles() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/configuration.properties");
            try {
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriverThreadSafe.set(new ChromeDriver());
            webDriverThreadSafe.get().manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("implicitWait")), TimeUnit.MILLISECONDS);
            webDriverThreadSafe.get().manage().window().maximize();
        }
    }

    public WebDriver getDriver() {
        setDriver(properties.getProperty("browser"));
        return webDriverThreadSafe.get();
    }
}
