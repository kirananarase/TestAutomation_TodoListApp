package base;

import enums.Browsers;
import enums.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverHandler {
    private WebDriver driver;
    public static DriverHandler _driverHandler;


    public DriverHandler(String browser) {
        if (System.getProperty("os.name").toLowerCase().contains(OS.WINDOW.name().toLowerCase())) {
            if (browser.equalsIgnoreCase(Browsers.CHROME.name())) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase(Browsers.FIREFOX.name())) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        } else if (System.getProperty("os.name").toLowerCase().contains(OS.MAC.name().toLowerCase())) {
            if (browser.equalsIgnoreCase(Browsers.CHROME.name())) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase(Browsers.FIREFOX.name())) {
                System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver");
                driver = new FirefoxDriver();
            }
        }

    }

    public static DriverHandler GetInstanceDriverHandler(){
        if (null == _driverHandler)
            _driverHandler = new DriverHandler(Browsers.CHROME.name());
        return _driverHandler;
    }

    // Get the driver
    public WebDriver getDriver() {
        return driver;
    }

    // Close the driver
    public void TearDown() {
        driver.close();
        driver.quit();
    }

}
