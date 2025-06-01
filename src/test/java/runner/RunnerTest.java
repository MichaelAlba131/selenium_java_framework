package runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

// Constantes Cucumber JUnit Platform
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
// Onde estão seus arquivos .feature
@SelectClasspathResource("features")
// Onde estão suas classes de step definitions
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "steps"
)
// Plugins de relatório: pretty, HTML, JSON e Allure
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber/html, json:target/cucumber/report.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
// (Opcional) Executa apenas os cenários marcados com @loginFeature

public class RunnerTest {
    // Nenhum código necessário, só as anotações
}