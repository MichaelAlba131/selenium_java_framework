package interaction;

import pages.LoginPage;
import utils.driver.Driver;

public class ValidarInteraction extends LoginPage {

    public ValidarInteraction() {
        if (Driver.getDriver() == null) {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }

    public void OpenUrl() {
        String url = "https://google.com.br";
        Driver.getDriver().navigate().to(url);
    }
}