package steps;

import interaction.ValidarInteraction;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

public class ValidarSteps {

    private final ValidarInteraction validarInteraction = new ValidarInteraction();

    @Then("I validate the home view")
    @Step("Validate the home view")
    public void iValidateTheHomeView() {
        validarInteraction.OpenUrl();
    }
}