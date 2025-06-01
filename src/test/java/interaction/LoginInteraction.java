package interaction;

import pages.LoginPage;
import utils.driver.Driver;

public class LoginInteraction extends LoginPage {

    public LoginInteraction() {
        if (Driver.getDriver() == null) {
            throw new IllegalStateException("Driver is not initialized.");
        }
    }

    public LoginInteraction OpenUrl() {
        try {
            String url = "https://login.dafiti.com.br/";
            Driver.getDriver().navigate().to(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public LoginInteraction FillUser(String user) throws Exception {
        waitForElementsLambda(null, email, 10);
        performAction("Email", email, user, 10);
        return this;
    }

    public LoginInteraction FillPassword(String password) throws Exception {
        performAction("Password", senha, password, 10);
        return this;
    }

    public LoginInteraction ClickAcessar() throws InterruptedException {
        performAction("Acessar", entrar, null, 10);
        return this;
    }
}