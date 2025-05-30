package interaction;


import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.driver.Driver;


public class ValidarInteraction extends LoginPage {
    public WebDriver driver;

    public ValidarInteraction() {
        this.driver = Driver.getDriver();
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }

    public void OpenUrl() {
        String url = "https://google.com.br";
        driver.navigate().to(url);
    }
}