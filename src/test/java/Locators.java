import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Locators {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to("http://wikipedia.pl");

    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findingElementById() {
        driver.findElement(By.id("searchInput"));
        driver.findElement(By.name("search"));
        driver.findElement(By.className("searchButton"));

        List<WebElement> externalClassElements = driver.findElements(By.className("external"));

        WebElement elementWithTwoClasses = null;

        for (WebElement externalClassElement: externalClassElements) {
            String elementClass = externalClassElement.getAttribute("class");
            if (elementClass.equals("external text")) {
                elementWithTwoClasses = externalClassElement;
            }
        }

        Assertions.assertTrue(elementWithTwoClasses != null, "Element was not found");


        int numberOfImages = driver.findElements(By.tagName("img")).size();
    }


    /*

    <a href="https://pl.wiktionary.org/wiki/" class="extiw" title="wikt:">Wikisłownik</a>

     */

    @Test
    public void findingElementByLinkText() {
        driver.findElement(By.linkText("Wikisłownik")); // ta metoda sprawdza po całym tekście, po części nie znajdzie np. "każdy może redagować"
        driver.findElement(By.partialLinkText("redagować")); //szuka linku zawierającego słowo

    }



}
