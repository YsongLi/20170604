package com.masterselenium;

import com.masterselenium.config.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;

import static com.masterselenium.config.DriverType.FIREFOX;
import static java.lang.Enum.valueOf;

/**
 * Created by lys-thk on 2017/6/3.
 */
public class WebDriverThread {
    private DriverType selectedDriverType;
    private final DriverType defaultDriverType = FIREFOX;
    private final String browser = System.getProperty("browser").toUpperCase();
    private WebDriver webdriver;
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    public WebDriver getDriver() throws Exception {
        /*if (null == webdriver) {
            System.out.println(" ");
            System.out.println("Current Operating System: " + operatingSystem);
            System.out.println("Current Architecture: " + systemArchitecture);
            System.out.println("Current Browser Selection:Firefox");
            System.out.println(" ");
            System.setProperty("webdriver.gecko.driver", "D:\\webdriver\\fixfox\\geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            FirefoxBinary firefoxBinary = new FirefoxBinary(new File("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
            FirefoxProfile profile = new FirefoxProfile();
            firefoxOptions.setProfile(profile);
            firefoxOptions.setBinary(firefoxBinary);
            webdriver = new FirefoxDriver(firefoxOptions);
        }
        return webdriver;*/
        if (null == webdriver) {
            selectedDriverType = determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities =
                    selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(desiredCapabilities);
        }
        return  webdriver;
    }

    private DriverType determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = valueOf(DriverType.class, browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        return driverType;
    }
    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        System.out.println(" ");
        webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
    }
    public void quitDriver() {
        if (null != webdriver) {
            webdriver.quit();
            webdriver = null;
        }
    }
}
