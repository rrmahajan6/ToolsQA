package org.dolphin.utilities.Reporters;

import org.dolphin.Base;
import org.dolphin.utilities.SeleniumUtils;
import org.testng.*;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import java.util.*;

public class DolphinListeners extends DolphinReports implements ITestListener, ISuiteListener {
    DolphinReports report = new DolphinReports();

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getThrowable() instanceof Exception) {
            Reporter.log("Test is skipped due to failed configuration : " + result.getName(), true);
            report.startTest(result);
            report.getResult(result);
            String testClass = result.getTestClass().getName();
            testClass = testClass.substring(testClass.lastIndexOf(".") + 1, testClass.length());
            String testCase = result.getName();

            if (Objects.nonNull(Base.webDriverThreadSafe.get())) {
                String base64 = SeleniumUtils.getScreenshot(Base.webDriverThreadSafe.get());
                DolphinReports.extentNodeMap.get(testClass + testCase).addScreenCaptureFromBase64String(base64);
            }
        } else if (result.getThrowable().getMessage().contains("depends on not successfully finished methods")) {
            Reporter.log("Test is skipped : " + result.getName(), true);
            report.startTest(result);
            report.getResult(result);
        } else {
            Reporter.log("Test is skipped with exception : " + result.getName(), true);
            report.startTest(result);
            report.getResult(result);
        }
    }

    @Override
    public void onTestFailure(ITestResult tr) {

        SeleniumUtils.deleteOlderFiles(5, "/output/reports/", "/output/FailedTestsScreenshots/");
        report.getResult(tr);
        Reporter.log("Test Case Ended " + tr.getName(), true);
        String testClass = tr.getTestClass().getName();
        testClass = testClass.substring(testClass.lastIndexOf(".") + 1, testClass.length());
        String testCase = tr.getName();

        if (Objects.nonNull(Base.webDriverThreadSafe.get())) {
            String base64 = SeleniumUtils.getScreenshot(Base.webDriverThreadSafe.get());
            DolphinReports.extentNodeMap.get(testClass + testCase).addScreenCaptureFromBase64String(base64);
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        report.getResult(tr);
        Reporter.log("All Steps of Test are completed", true);
        Reporter.log("Test Case Ended " + tr.getName(), true);
    }

    @Override()
    public void onTestStart(ITestResult tr) {
        report.startTest(tr);
        report.getResult(tr);
        Reporter.log("Test Case started " + tr.getName(), true);
    }

    @Override
    public void onFinish(ITestContext context) {
        report.saveReport();
    }

    @Override
    public void onStart(ISuite iSuite) {
    }

    @Override
    public void onFinish(ISuite iSuite) {
        Map<String, List<String>> classMethodMap = new HashMap<String, List<String>>();
        if (retryCount > 0) {
            final Map<String, ISuiteResult> res = iSuite.getResults();
            for (ISuiteResult r : res.values()) {
                ITestContext context = r.getTestContext();
                Iterator<ITestResult> failedTestCases = context.getFailedTests().getAllResults().iterator();
                while (failedTestCases.hasNext()) {
                    ITestResult failedTestCase = failedTestCases.next();
                    ITestNGMethod method = failedTestCase.getMethod();
                    String testName = failedTestCase.getName();
                    String className = failedTestCase.getTestClass().getName();

                    if (!classMethodMap.containsKey(className)) {
                        classMethodMap.put(failedTestCase.getTestClass().getName(), new ArrayList<>(Arrays.asList(testName)));
                    } else {
                        List<String> methods = classMethodMap.get(className);
                        methods.add(testName);
                        classMethodMap.put(className, methods);
                    }
                }
                Iterator<ITestResult> skipedTestCases = context.getSkippedTests().getAllResults().iterator();

                while (skipedTestCases.hasNext()) {
                    ITestResult skipedTestCase = skipedTestCases.next();
                    ITestNGMethod skipedMethod = skipedTestCase.getMethod();
                    String testName = skipedTestCase.getName();
                    String className = skipedTestCase.getTestClass().getName();

                    if (!classMethodMap.containsKey(className)) {
                        classMethodMap.put(skipedTestCase.getTestClass().getName(), new ArrayList<>(Arrays.asList(testName)));
                    } else {
                        List<String> methods = classMethodMap.get(className);
                        methods.add(testName);
                        classMethodMap.put(className, methods);
                    }
                }
            }

            if (classMethodMap.size() > 0) {
                XmlSuite suite = new XmlSuite();
                suite.setName("TmpSuite");
                List<XmlClass> classes = new ArrayList<XmlClass>();

                for (String className : classMethodMap.keySet()) {
                    List<XmlInclude> includedTests = new ArrayList<XmlInclude>();
                    XmlClass xmlClass = new XmlClass(className);
                    for (String testName : classMethodMap.get(className)) {
                        XmlInclude include = new XmlInclude(testName);
                        includedTests.add(include);
                    }
                    classes.add(xmlClass);
                    xmlClass.setIncludedMethods(includedTests);
                }
                XmlTest xmlTest = new XmlTest(suite);
                xmlTest.setXmlClasses(classes);
                xmlTest.setParallel(XmlSuite.ParallelMode.CLASSES);
                xmlTest.setThreadCount(3);
                List<XmlSuite> suites = new ArrayList<XmlSuite>();
                suites.add(suite);
                TestNG tng = new TestNG();
                tng.setXmlSuites(suites);
                retryCount--;
                tng.run();
            }
        }
    }

}
