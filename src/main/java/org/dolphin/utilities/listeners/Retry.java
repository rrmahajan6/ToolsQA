package org.dolphin.utilities.listeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dolphin.Base;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer {

    private int retryCount = 0;
    private int maxRetryCount = 3;  //no of time test will be retried once failed
    private static final Logger LOGGER = LogManager.getLogger(Retry.class.getName());

    // Below method returns 'true' if the test method has to be retried else 'false'
    //and it takes the 'Result' as parameter of the test method that just ran
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            LOGGER.info("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
            Reporter.log("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).",true);
            retryCount++;
            result.setStatus(ITestResult.FAILURE);  //Mark test as failed
            return true;
        }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if(status==1)
            resultName = "SUCCESS";
        if(status==2)
            resultName = "FAILURE";
        if(status==3)
            resultName = "SKIP";
        return resultName;
    }
}
