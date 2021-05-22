package org.dolphin;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dolphin.utilities.DataUtils.Constants;
import org.dolphin.utilities.DataUtils.ExcelReader;
import org.dolphin.utilities.Reporters.DolphinListeners;
import org.dolphin.utilities.listeners.AnnotationTransformer;
import org.dolphin.utilities.listeners.PriorityInterceptor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Listeners({DolphinListeners.class, PriorityInterceptor.class, AnnotationTransformer.class})
public class Base {
    public static int retryCount =0;
    public static ThreadLocal<WebDriver> webDriverThreadSafe = new ThreadLocal<>();
    Properties properties;
    public static ExtentSparkReporter sparkReporter = new ExtentSparkReporter("/Users/rahul.mahajan/Desktop/Dolphin/output/report.html");
    public static ExtentReports extent = new ExtentReports();
    protected static Map<String, ExtentTest> extentTestMap = new HashMap<>();
    protected static Map<String, ExtentTest> extentNodeMap = new HashMap<>();
    public static ExcelReader excelReader=new ExcelReader(Constants.SUITE_XL_PATH);
    private static final Logger LOGGER = LogManager.getLogger(Base.class.getName());

    @BeforeSuite
    public void beforeSuite() {
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", "environment");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        sparkReporter.config().setReportName("Dolphin Automation Report");
        sparkReporter.config().setDocumentTitle("Dolphin Automation Report");
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
            webDriverThreadSafe.get().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
            webDriverThreadSafe.get().manage().window().maximize();
        }
    }
    public WebDriver getDriver() {
        setDriver("chrome");
        return webDriverThreadSafe.get();
    }
}
