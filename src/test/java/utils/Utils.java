package utils;

import io.qameta.allure.Attachment;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.Driver;

import java.time.Duration;

/**
 * This class contains utility methods for performing actions on web elements.
 */
public class Utils extends Driver {

    /**
     * Try performing actions on web elements with retries.
     *
     * @param field The field name.
     * @param elementObj The web element object.
     * @param valor The input value.
     * @param maxTries The maximum number of retries.
     * @throws InterruptedException if any thread has interrupted the current thread.
     */
    public void performAction(String field, Object elementObj, String valor, int maxTries) throws InterruptedException {
        int tries = 0;
        while (true) {
            try {
                performActionMethod(field, elementObj, valor);
                return;
            } catch (Exception e) {
                if (++tries >= maxTries)
                    Assert.fail("Erro ao realizar a ação. Tentativa " + tries + " de " + maxTries);
                logMessage("Erro ao realizar a ação. Tentativa " + tries + " de " + maxTries);
                Thread.sleep(1000);
            }
        }
    }

    /**
     * Perform actions on web elements.
     *
     * @param field The field name.
     * @param elementObj The web element object.
     * @param valor The input value.
     */
    public void performActionMethod(String field, Object elementObj, String valor) {
        WebElement element;
        try {
            if (elementObj instanceof WebElement)
                element = (WebElement) elementObj;
            else if (elementObj instanceof By)
                element = driver.findElement((By) elementObj);
            else
                throw new Exception("Unsupported element object: " + elementObj.getClass().getSimpleName());

            String tagName = element.getTagName().toUpperCase();
            switch (tagName) {
                case "INPUT":
                    String type = element.getAttribute("type");
                    if (type.equalsIgnoreCase("submit") || type.equalsIgnoreCase("button")) {
                        element.click();
                        logMessage(field + "clicked ");
                    } else if (type.equalsIgnoreCase("checkbox")) {
                        if (!element.isSelected()) {
                            element.click();
                            logMessage(field + "clicked ");
                        }
                    } else {
                        element.sendKeys(valor);
                    }
                    break;
                case "BUTTON":
                    element.click();
                    break;
                case "SELECT":
                    new Select(element).selectByVisibleText(valor);
                    break;
                default:
                    throw new Exception("Unsupported HTML tag: " + tagName);
            }
        } catch (Exception e) {
            logMessage(field + " no actionable steps were performed ");
        }
    }

    /**
     * Log a message to the console.
     *
     * @param message The message to be logged.
     */
    public void logMessage(String message) {
        System.out.println(message);
    }

    /**
     * Wait for an element to be visible on the webpage.
     *
     * @param byElement The By object for locating the element.
     * @param element The web element.
     * @param timeToWait The maximum wait time in seconds.
     */
    public static void waitForElementsLambda(By byElement, WebElement element, int timeToWait) {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        if (byElement != null)
            new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(ExpectedConditions.visibilityOfElementLocated(byElement));
        else
            new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(ExpectedConditions.visibilityOf(element));
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        return takeScreenshot();
    }
}