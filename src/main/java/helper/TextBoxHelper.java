package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Utils;


public class TextBoxHelper extends Utils {
    private WebDriver driver;
    private Logger oLog = LoggerHelper.getLogger(TextBoxHelper.class);

    public TextBoxHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
        oLog.debug("TextBoxHelper : " + this.driver.hashCode());
    }
    public void sendKeys(By locator, String value) {
        oLog.info("Locator : " + locator + " Value : " + value);
        getElement(locator).sendKeys(value);
    }

    public void clear(WebDriver driver, By locator) {
        oLog.info("Locator : " + locator);
        getElement(locator).clear();
    }

    public String getText(WebDriver driver,By locator) {
        oLog.info("Locator : " + locator);
        return getElement(locator).getText();
    }

    public void clearAndSendKeys(WebDriver driver,By locator,String value) {
        WebElement element =  getElement(locator);
        element.clear();
        element.sendKeys(value);
        oLog.info("Locator : " + locator + " Value : " + value);
    }
}