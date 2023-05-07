package runner;

import base.DriverHandler;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.testng.annotations.*;
import resources.ConfigFileReader;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"steps"},
        tags = {"@todoitems"},
        monochrome = true,
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:report/report.html"}
)

public class TestRunner{

    @BeforeClass
    public static void initExtentReport() {
    }

    @AfterClass
    public static void writeExtentReport() {
        try {
            Reporter.loadXMLConfig(ConfigFileReader.getInstance().getReportConfigPath());
            Reporter.setSystemInfo("user", System.getProperty("user.name"));
            Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
            Reporter.setSystemInfo("Machine", "Mac");
            Reporter.setSystemInfo("Selenium", "3.141.59");
            Reporter.setSystemInfo("Maven", "3.9.1");
            Reporter.setSystemInfo("Java Version", "8");
            Reporter.setTestRunnerOutput("Sample test runner output message");
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    //Added  TestNG annotation to allow closing the browser at the end
    @AfterSuite
    public static void tearDown() {
        DriverHandler.GetInstanceDriverHandler().TearDown();
    }
}
