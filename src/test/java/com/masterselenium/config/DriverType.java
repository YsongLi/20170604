package com.masterselenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lys-thk on 2017/6/3.
 */
public enum  DriverType implements DriverSetup {
    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            System.setProperty("webdriver.gecko.driver", "D:\\webdriver\\fixfox\\geckodriver.exe");
            FirefoxBinary firefoxBinary = new FirefoxBinary(new File("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
            FirefoxProfile profile = new FirefoxProfile();
            capabilities.setCapability("firefox_profile", profile);
            capabilities.setCapability("firefox_binary", firefoxBinary);
            return capabilities;
        }
        public WebDriver getWebDriverObject(DesiredCapabilities
                                                    capabilities) {
            return new FirefoxDriver(capabilities);
        }
    },
    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            System.setProperty("webdriver.chrome.driver", "D:\\webdriver\\chrome\\chromedriver.exe");
            capabilities.setCapability("chrome.switches",Arrays.asList("--no-default-browser-check"));
            HashMap<String, String> chromePreferences =
                    new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            capabilities.setCapability("chrome.prefs",
                    chromePreferences);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            capabilities.setCapability("chromeOptions", chromeOptions);
            return capabilities;
        }
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {

            return new ChromeDriver(capabilities);
        }
    },
    IE {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            System.setProperty("webdriver.ie.driver", "D:\\webdriver\\ie\\IEDriverServer.exe");
            capabilities.setCapability(CapabilityType.
                    ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.
                    ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            return capabilities;
        }
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new InternetExplorerDriver(capabilities);
        }
    },
    EDGE {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities =
                    DesiredCapabilities.edge();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            System.setProperty("webdriver.edge.driver", "D:\\webdriver\\edge\\MicrosoftWebDriver.exe");
            return capabilities;
        }
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new InternetExplorerDriver(capabilities);
        }
    },
    SAFARI {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            return capabilities;
        }
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new SafariDriver(capabilities);
        }
    },
    OPERA {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
            return capabilities;
        }
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            return new OperaDriver(capabilities);
        }
    },
    PHANTOMJS {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities =
                    DesiredCapabilities.phantomjs();
            System.setProperty("phantomjs.binary.path", "D:\\webdriver\\phantomjs\\bin\\phantomjs.exe");
            final List<String> cliArguments = new ArrayList<String>();
            cliArguments.add("--web-security=false");
            cliArguments.add("--ssl-protocol=any");
            cliArguments.add("--ignore-ssl-errors=true");
            capabilities.setCapability("phantomjs.cli.args", cliArguments);
            capabilities.setCapability("takesScreenshot", true);
            return capabilities;
        }
        public WebDriver getWebDriverObject(DesiredCapabilities
                                                    capabilities) {
            return new PhantomJSDriver(capabilities);
        }
    };
}
