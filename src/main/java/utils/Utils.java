package utils;


import helper.LoggerHelper;
import interfaces.IwebComponent;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Utils implements IwebComponent {
    private WebDriver driver;
    private Logger oLog = LoggerHelper.getLogger(Utils.class);

    public Utils(WebDriver driver){
        this.driver = driver;
        oLog.debug("Utils : " + this.driver.hashCode());
    }

    public String generateRandomString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder s = new StringBuilder(n);
        int y;
        for ( y = 0; y < n; y++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            s.append(AlphaNumericString
                    .charAt(index));
        }
        return s.toString();
    }

    public boolean checkForTitle(String title){
        oLog.info(title);
        if(title == null || title.isEmpty())
            throw new IllegalArgumentException(title);
        return driver.getTitle().trim().contains(title);
    }

    public String getTextOfWebElement(By locator){
        oLog.info(locator);
        if(!existsElement(locator))
            throw new IllegalArgumentException(String.valueOf(locator));
        return driver.findElement(locator).getText();
    }

    public String getAttributeOfWebElement(By locator, String attributeValue){
        oLog.info(locator);
        if(!existsElement(locator))
            throw new IllegalArgumentException(String.valueOf(locator));
        return driver.findElement(locator).getAttribute(attributeValue);
    }

    public WebElement getElement(By locator) {
        oLog.info(locator);
        if (IsElementPresentQuick(locator))
            return driver.findElement(locator);

        try {
            throw new NoSuchElementException("Element Not Found : " + locator);
        } catch (RuntimeException re) {
            oLog.error(re);
            throw re;
        }
    }
    public List<WebElement> getElements(By locator) {
        oLog.info(locator);
        if (IsElementPresentQuick(locator))
            return (List<WebElement>) driver.findElements(locator);

        try {
            throw new NoSuchElementException("Elements Not Found : " + locator);
        } catch (RuntimeException re) {
            oLog.error(re);
            throw re;
        }
    }
    public boolean IsElementPresentQuick(By locator) {
        boolean flag = driver.findElements(locator).size() >= 1;
        oLog.info(flag);
        return flag;
    }

    /*************************************************************
     Description: Checks if an element exists using locator
     Parameters: webdriver, locator
     Return: boolean
     ***********************************************************/
    public boolean existsElement (By locator){
        try {
            driver.findElement(locator);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /*************************************************************
     Description: Checks if an element is visible or not, so for instance an element that is in the DOM
     but may be or not present depending on conditions
     Parameters: webdriver, time in seconds to wait, locator
     Return: element if found, if not returns null
     ***********************************************************/
    public WebElement visibleOrNot(int timeToWait, By locator) {
        WebElement element = null;
        try {
            element = new FluentWait<>(driver).
                    withTimeout(Duration.ofSeconds(timeToWait)).
                    pollingEvery(Duration.ofSeconds(1)).
                    ignoring(NotFoundException.class).ignoring(NoSuchElementException.class).
                    until(visibilityOfElementLocated(locator));
        } catch (TimeoutException ex) {}
        return element;
    }

    /*************************************************************
     Description: Clicks an element using Javascript
     Parameters: driver, webelement to click
     Return: NA
     ***********************************************************/
    public void clickElementThroughJavaScript(WebElement element){
        //To Click on elements which may not be visible on screen
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

}
