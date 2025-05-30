package utils.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DriverConfigurator {
    public enum WebDrivers {
        CHROME_DRIVER("chromedriver"), FIREFOX_DRIVER("geckodriver");

        private final String driverName;

        WebDrivers(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverName() {
            return this.driverName;
        }
    }

    private static final String WINDOWS_FOLDER = "/driver/windows/";
    private static final String LINUX_FOLDER = "/driver/linux/";
    private static final String MAC_FOLDER = "/driver/mac/";
    private static final String POPUPS_SETTINGS = "profile.default_content_settings.popups";
    private static final String DOWNLOAD_DIR = "download.default_directory";
    private static final String DOWNLOAD_PATH = "/src/test/java/downloads";

    protected String getDriverPath(WebDrivers webdrivers) throws Exception {
        String osName = System.getProperty("os.name").toUpperCase();
        String driverFolder = switch (osName) {
            case "MAC" -> MAC_FOLDER;
            case "MAC OS X" -> MAC_FOLDER;
            case "LINUX" -> LINUX_FOLDER;
            case "WINDOWS" -> WINDOWS_FOLDER;
            default -> throw new Exception("Unsupported OS for webdriver: " + osName);
        };
        return System.getProperty("user.dir") + driverFolder + webdrivers.getDriverName();
    }

    protected ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = getChromePrefs();
        List<String> chromeArguments = Arrays.asList(
                "--verbose",
                "--whitelisted-ips=''",
                "--disable-popup-blocking",
                "--disable-default-apps",
                "test-type=browser",
                "--ignore-certificate-errors",
                "--remote-allow-origins=*",
                "--start-maximized",
                "--disable-dev-shm-usage",
                "--disable-gpu");
        chromeArguments.forEach(options::addArguments);
        options.setHeadless(false);
        setInputExperimentalOptions(options, chromePrefs);
        return options;
    }

    private HashMap<String, Object> getChromePrefs() {
        String downloadFilepath = System.getProperty("user.dir") + DOWNLOAD_PATH;
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put(POPUPS_SETTINGS, 0);
        chromePrefs.put(DOWNLOAD_DIR, downloadFilepath);
        return chromePrefs;
    }

    private void setInputExperimentalOptions(ChromeOptions options, HashMap<String, Object> chromePrefs) {
        options.setExperimentalOption("prefs", chromePrefs);
        options.setExperimentalOption("w3c", true);
    }

    protected FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = getFirefoxProfile();
        List<String> firefoxArguments = Arrays.asList(
                "--verbose",
                "--disable-popup-blocking");
        options.setProfile(profile);
        firefoxArguments.forEach(options::addArguments);
        return options;
    }

    private FirefoxProfile getFirefoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", System.getProperty("user.dir") + DOWNLOAD_PATH);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        return profile;
    }
}
