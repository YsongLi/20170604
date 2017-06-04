package com.masterselenium;

import org.junit.After;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lys-thk on 2017/6/3.
 */
public class BasicTestWD extends DriverFactory {

    //private WebDriver driver = null;


    /*@BeforeTest
    public void beforeTest() throws MalformedURLException {
        driver = getFirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30 * 1000, TimeUnit.MILLISECONDS);
    }*/

    private WebDriver getFirefoxDriver(){
       System.setProperty("webdriver.gecko.driver", "D:\\webdriver\\fixfox\\geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxBinary firefoxBinary = new FirefoxBinary(new File("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile();
        firefoxOptions.setProfile(profile);
        firefoxOptions.setBinary(firefoxBinary);
        return new FirefoxDriver(firefoxOptions);
    }

    private WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chrome\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        return new ChromeDriver(chromeOptions);
    }

    private WebDriver getIEDriver(){
        System.setProperty("webdriver.ie.driver", "D:\\webdriver\\ie\\IEDriverServer.exe");
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setJavascriptEnabled(true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
        caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        return new InternetExplorerDriver();
    }

    private WebDriver getEdgeDriver(){
        System.setProperty("webdriver.edge.driver", "D:\\webdriver\\edge\\MicrosoftWebDriver.exe");
        return new EdgeDriver();
    }

    private void googleExampleThatSearchesFor(final String searchString) throws Exception{
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.google.com");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.clear();
        searchField.sendKeys(searchString);
        System.out.println("Page title is: " + driver.getTitle());
        searchField.submit();
        (new WebDriverWait(driver, 10)).until(
                new ExpectedCondition<Boolean>() {
                  public Boolean apply(WebDriver driverObject) {
                      return driverObject.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
                  }});
        System.out.println("Page title is: " + driver.getTitle());

    }

    @Test
    public void googleCheeseExample() throws Exception{
        googleExampleThatSearchesFor("Cheese!");
    }

    @Test
    public void googleMilkExample() throws Exception{
        googleExampleThatSearchesFor("Milk!");
    }

    /*@AfterClass
    public void afterClass(){
        driver.quit();
    }*/
}

