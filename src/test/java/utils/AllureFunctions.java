package utils;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utils.driver.Driver;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AllureFunctions extends Driver {
    public static void saveScreenshot() {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public static void saveFileTXT(String nameFile, String document) {
        Allure.addAttachment(nameFile, "text/plain", document);
    }

    public static void allureEnvironmentWriter(ImmutableMap<String, String> environmentValuesSet,
                                               String customResultsPath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element environment = doc.createElement("environment");
            doc.appendChild(environment);
            environmentValuesSet.forEach((k, v) -> {
                Element parameter = doc.createElement("parameter");
                Element key = doc.createElement("key");
                Element value = doc.createElement("value");
                key.appendChild(doc.createTextNode(k));
                value.appendChild(doc.createTextNode(v));
                parameter.appendChild(key);
                parameter.appendChild(value);
                environment.appendChild(parameter);
            });
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File allureResultsDir = new File(customResultsPath);
            if (!allureResultsDir.exists()) allureResultsDir.mkdirs();
            StreamResult result = new StreamResult(
                    new File(customResultsPath + "/environment.xml"));
            transformer.transform(source, result);
            System.out.println("Allure environment data saved.");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    @Deprecated
    public static void addDescription(String description) {
        AllureFunctions.addDescription(description);
    }

    public static void createCategoriesReport() {
        String json = "[{ 'name': 'Testes Ignorados','matchedStatuses': ['skipped']} ," +
                "{ 'name': 'Problemas de Infraestrutura','matchedStatuses': ['broken', 'failed'], 'messageRegex':'.*bye-bye.*'}," +
                "{ 'name': 'Teste Desatualizado','matchedStatuses': ['broken'], 'traceRegex':'.*FileNotFoundException.*'} ," +
                "{ 'name': 'Defeito de Produto','matchedStatuses': ['failed']} ," +
                "{ 'name': 'Defeito de Teste','matchedStatuses': ['broken']}]";
        try {
            FileWriter file = new FileWriter("target/allure-results/categories.json");
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}