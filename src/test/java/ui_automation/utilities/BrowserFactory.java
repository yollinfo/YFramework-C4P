package ui_automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {
        public static WebDriver createInstance() {

            WebDriver driver = null;

            try {
                if (driver == null) {
                    if(System.getProperty("browser")==null){
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                    }
                    else {
                        switch (System.getProperty("browser")) {
                            case "chrome-headless":
                                WebDriverManager.chromedriver().setup();
                                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                                break;
                            case "chromeRemote":
                                ChromeOptions chrOptions = new ChromeOptions();
                                try {
                                    driver = new RemoteWebDriver(new URL("http://54.88.214.189:4444/wd/hub"), chrOptions);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "firefox":
                                WebDriverManager.firefoxdriver().setup();
                                driver = new FirefoxDriver();
                                break;
                            case "firefox-headless":
                                WebDriverManager.firefoxdriver().setup();
                                driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                                break;
                            case "firefoxRemote":
                                FirefoxOptions firOptions = new FirefoxOptions();
                                try {
                                    driver = new RemoteWebDriver(new URL("http://54.88.214.189:4444/wd/hub"), firOptions);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "ie":
                                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                                    throw new WebDriverException("Your operating system does not support the requested browser");
                                }
                                WebDriverManager.iedriver().setup();
                                driver = new InternetExplorerDriver();
                                break;

                            case "edge":
                                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                                    throw new WebDriverException("Your operating system does not support the requested browser");
                                }
                                WebDriverManager.edgedriver().setup();
                                driver = new EdgeDriver();
                                break;

                            case "safari":
                                if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                                    throw new WebDriverException("Your operating system does not support the requested browser");
                                }
                                WebDriverManager.getInstance(SafariDriver.class).setup();
                                driver = new SafariDriver();
                                break;
                            default:
                                WebDriverManager.chromedriver().setup();
                                driver = new ChromeDriver();
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return driver;
        }
    }