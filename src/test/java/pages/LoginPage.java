package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;
import utils.driver.Driver;

public class LoginPage extends Utils {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    public static WebElement email;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    public static WebElement senha;

    @FindBy(how = How.XPATH, using = "//button/div[contains(text(),'Entrar')]//ancestor::button")
    public static WebElement entrar;


}
