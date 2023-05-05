package runner;

import base.DriverHandler;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.*;
import resources.ConfigFileReader;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"steps"},
        //~ will skip the features with that specific tag
        tags = {"@regression"},
        monochrome = true,
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:report/report.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests{

    @BeforeClass
    public static void initExtentReport() {
    }

    //writes in the report
    @AfterClass
    public static void writeExtentReport() {
        try {
            Reporter.loadXMLConfig(ConfigFileReader.getInstance().getReportConfigPath());
            Reporter.setSystemInfo("user", System.getProperty("user.name"));
            Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
            Reporter.setSystemInfo("Machine", "Mac" + "64 Bit");
            Reporter.setSystemInfo("Selenium", "3.141.59");
            Reporter.setSystemInfo("Maven", "3.7.0");
            Reporter.setSystemInfo("Java Version", "8");
            Reporter.setTestRunnerOutput("Sample test runner output message");
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    //Added  TestNG annotation to allow closing the browser at the end
    @AfterSuite
    public static void tearDown(){
        DriverHandler.GetInstanceDriverHandler().TearDown();
    }
}
