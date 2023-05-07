package steps;

import base.DriverHandler;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.api.Scenario;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Hook{

    private WebDriver driver;
    public Scenario scenario;

    public WebDriver getDriver(){
        return DriverHandler.GetInstanceDriverHandler().getDriver();
    }

    @Before
    public void InitializeTest(Scenario scenario){
        System.out.println("Running Scenario: " + scenario.getName());
        this.scenario = scenario;
        driver = DriverHandler.GetInstanceDriverHandler().getDriver();
    }

    @After(order = 1)
    public void TearDownTest(Scenario scenario){
        //Add screenshot
        if(scenario.isFailed()){
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                File sourcePath = ((TakesScreenshot) DriverHandler
                        .GetInstanceDriverHandler().getDriver()).getScreenshotAs(OutputType.FILE);

                File destinationPath = new File(System.getProperty("user.dir") + "/report/screenshots/" + screenshotName + ".png");
                Files.copy(sourcePath, destinationPath);
                Reporter.addScreenCaptureFromPath(destinationPath.toString());

            } catch (IOException e) {
            }
        }
    }
}
