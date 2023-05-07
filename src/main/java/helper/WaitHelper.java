package helper;

import com.google.common.base.Function;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.ConfigFileReader;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitHelper extends ConfigFileReader {

    Logger logger = LoggerHelper.getLogger(WaitHelper.class);
    private WebDriver driver;

    public WaitHelper(WebDriver driver){
        this.driver = driver;
    }

    public void setImplicitWait(long timeout,TimeUnit unit) {
        logger.info(timeout);
        driver
                .manage()
                .timeouts()
                .implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
    }

    private Function<WebDriver, Boolean> elementLocated(final WebElement element) {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                logger.debug("Waiting for Element : " + element);
                return element.isDisplayed();
            }
        };
    }
    public void waitForElementVisible(By locator,int timeOutInSeconds) {
        logger.info(locator);
        setImplicitWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = getWait(timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        setImplicitWait(getImplicitWait(), TimeUnit.SECONDS);
    }

    private WebDriverWait getWait(int timeOutInSeconds) {
        logger.debug("");
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.pollingEvery(Duration.ofSeconds(getExplicitWait()));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }
}
