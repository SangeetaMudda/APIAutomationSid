package core;

import com.relevantcodes.extentreports.LogStatus;
import helper.Helper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReport;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void config() {
        //create the folder path where we will have the html reports
        String subfolderpath = System.getProperty("user.dir") + "/reports/" + Helper.TimeStamp();
        //create subfolder
        Helper.CreateFolder(subfolderpath);
        ExtentReport.initialize(subfolderpath + "/" + "API_Execution_Automation.html");
    }



    @AfterMethod(alwaysRun = true)

        public void getResult(ITestResult result){

        if(result.getStatus() == ITestResult.SUCCESS){

            ExtentReport.extentlog.log(LogStatus.PASS, "Test Case: " + result.getName()+ "is passed");

        } else if(result.getStatus() == ITestResult.FAILURE){
            ExtentReport.extentlog.log(LogStatus.FAIL, "Test Case: " + result.getName()+ "is failed");
            ExtentReport.extentlog.log(LogStatus.FAIL, "Test Case is failed due to:  " + result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            ExtentReport.extentlog.log(LogStatus.SKIP, "Test Case is skipped : " + result.getName());
        }
        ExtentReport.extentreport.endTest(ExtentReport.extentlog);

    }

    @AfterSuite(alwaysRun = true)
    public void endReport(){
        ExtentReport.extentreport.close();
    }



}