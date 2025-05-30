package interaction;


import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.driver.Driver;


public class LoginInteraction extends LoginPage {
    public WebDriver driver;

    public LoginInteraction() {
        this.driver = Driver.getDriver();
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }

    public LoginInteraction OpenUrl() {
        try {
            driver = Driver.getDriver();
            String url = "https://login.dafiti.com.br/";
            driver.navigate().to(url);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new LoginInteraction();
    }

    public LoginInteraction FillUser(String user) throws Exception {
        waitForElementsLambda(null, email, 10);
        performAction("Email", email, user, 10);
        return new LoginInteraction();
    }

    public LoginInteraction FillPassword(String password) throws Exception {
        performAction("Password", senha, password, 10);
        return new LoginInteraction();
    }

    public LoginInteraction ClickAcessar() throws InterruptedException {
        performAction("Acessar", entrar, null, 10);
        return new LoginInteraction();
    }
}