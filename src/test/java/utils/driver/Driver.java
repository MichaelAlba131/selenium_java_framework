package utils.driver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
    public enum BrowserType {
        CHROME,
        FIREFOX,
        SAFARI,
        EDGE
    }

    public void setDriver(WebDriver driver) {
        Driver.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver driver;

    public void initializeWebDriver(BrowserType browser) throws Exception {
        DriverConfigurator configurator = new DriverConfigurator();
        String driverPath = null;
        switch (browser) {
            case CHROME:
                driverPath = configurator.getDriverPath(DriverConfigurator.WebDrivers.CHROME_DRIVER);
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions options = configurator.getChromeOptions();
                driver = new ChromeDriver(options);
                setDriver(driver);
                String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
                System.out.println("Sessão do ChromeDriver: " + sessionId);
                break;
            case FIREFOX:
                driverPath = configurator.getDriverPath(DriverConfigurator.WebDrivers.FIREFOX_DRIVER);
                System.setProperty("webdriver.gecko.driver", driverPath);
                FirefoxOptions firefoxOptions = configurator.getFirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                setDriver(driver);
                break;
            case SAFARI:
                break;
            case EDGE:
                break;
            default:
                throw new Exception("Browser not supported: " + browser);
        }
    }

    public boolean isDriverOpened() {
        return driver != null;
    }

    public void closeDriver() {
        if (isDriverOpened()) {
            driver.close();
            driver = null;
        }
    }

    public static byte[] takeScreenshot() {
        byte[] screenshot = null;
        try {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.out.println("Error while taking screenshot: " + e.getMessage());
        }
        return screenshot;
    }
}
