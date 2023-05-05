package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;

import java.util.LinkedList;
import java.util.Set;

public class BrowserHelper extends Utils {

    private WebDriver driver;
    private Logger oLog = LoggerHelper.getLogger(BrowserHelper.class);

    public BrowserHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        oLog.debug("BrowserHelper : " + this.driver.hashCode());
    }

    public void goBack() {
        driver.navigate().back();
        oLog.info("");
    }

    public void goForward() {
        driver.navigate().forward();
        oLog.info("");
    }

    public void refresh() {
        driver.navigate().refresh();
        oLog.info("");
    }

    public Set<String> getWindowHandles() {
        oLog.info("");
        return driver.getWindowHandles();
    }

    public void SwitchToWindow(int index) {

        LinkedList<String> windowsId = new LinkedList<String>(
                getWindowHandles());

        if (index < 0 || index > windowsId.size())
            throw new IllegalArgumentException("Invalid Index : " + index);

        driver.switchTo().window(windowsId.get(index));
        oLog.info(index);
    }

    public void switchToParentWindow() {
        LinkedList<String> windowsId = new LinkedList<String>(
                getWindowHandles());
        driver.switchTo().window(windowsId.get(0));
        oLog.info("");
    }

    public void switchToFrame(By locator) {
        driver.switchTo().frame(getElement(locator));
        oLog.info(locator);
    }

    public void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
        oLog.info(nameOrId);
    }
}
