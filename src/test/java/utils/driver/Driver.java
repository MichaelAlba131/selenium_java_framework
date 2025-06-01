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

    // 1. Use ThreadLocal para isolar o driver por thread
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // 2. Métodos de acesso ao driver são agora via ThreadLocal
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public void initializeWebDriver(BrowserType browser) throws Exception {
        DriverConfigurator configurator = new DriverConfigurator();
        String driverPath = null;
        WebDriver driverInstance = null;
        switch (browser) {
            case CHROME:
                driverPath = configurator.getDriverPath(DriverConfigurator.WebDrivers.CHROME_DRIVER);
                System.setProperty("webdriver.chrome.driver", driverPath);
                ChromeOptions options = configurator.getChromeOptions();
                driverInstance = new ChromeDriver(options);
                setDriver(driverInstance);
                String sessionId = ((RemoteWebDriver) driverInstance).getSessionId().toString();
                System.out.println("Sessão do ChromeDriver: " + sessionId);
                break;
            case FIREFOX:
                driverPath = configurator.getDriverPath(DriverConfigurator.WebDrivers.FIREFOX_DRIVER);
                System.setProperty("webdriver.gecko.driver", driverPath);
                FirefoxOptions firefoxOptions = configurator.getFirefoxOptions();
                driverInstance = new FirefoxDriver(firefoxOptions);
                setDriver(driverInstance);
                break;
            // SAFARI e EDGE: implementar conforme a necessidade
            default:
                throw new Exception("Browser not supported: " + browser);
        }
    }

    public boolean isDriverOpened() {
        return getDriver() != null;
    }

    public void closeDriver() {
        WebDriver driverInstance = getDriver();
        if (driverInstance != null) {
            try {
                driverInstance.quit(); // Use quit para fechar tudo, não close
            } catch (Exception e) {
                System.out.println("Error closing driver: " + e.getMessage());
            }
            driverThreadLocal.remove();
        }
    }

    public static byte[] takeScreenshot() {
        try {
            WebDriver driverInstance = getDriver();
            return ((TakesScreenshot) driverInstance).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.out.println("Error while taking screenshot: " + e.getMessage());
            return null;
        }
    }
}