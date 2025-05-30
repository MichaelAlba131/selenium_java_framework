package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.AllureFunctions;
import utils.driver.Driver;

public class Hook extends Driver {
    @Before
    public void InitializeTest(Scenario scenario) throws Exception {
        initializeWebDriver(BrowserType.CHROME);
    }

    @After
    public void finalizeTest(Scenario scenario) {
        if (isDriverOpened()){
            System.out.println("About to get screenshot");
            AllureFunctions.saveScreenshot();
            System.out.println("Screenshot taken");
        }
        closeDriver();
    }

}
