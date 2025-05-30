package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/feature"},
        glue = {"steps"},
        tags = "@loginFeature",
        plugin = {"pretty", "html:target/cucumber/html", "json:target/cucumber/report.json", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        dryRun = false,
        strict = true,
        monochrome = false)

public class RunnerTest {
}