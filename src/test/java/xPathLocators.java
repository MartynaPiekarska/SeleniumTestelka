import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class xPathLocators {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to("https://www.amazon.com");

    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }


    /*

    Scieżka bezwzględna:
    /div/span/input

    Scieżka względna:
    //input
    //span/input
    //div/span/input

    .//input[@id='name']
    .//*[@id='name']

      //  driver.findElement(By.xpath(jakiś-xpath)).findElement(By.xpath(jakiś-inny-xpath));
      // .//div[@id='gw-card-layout']/* - po gwiazdce wyszukuje wszystkie takie elementy


     */

    @Test
    public void xPathLocators() {
        driver.findElement(By.xpath(".//div[@id='desktop-banner']"));
        driver.findElement(By.xpath(
                ".//div[@id='desktop-banner' and @cel_widget_id=\"desktop-hero-order\"]")); //łączenie warunków (and)

    }

    /*

    Atrybuty:

    Atrybut zawiera:
    .//button[contains(@class, 'primary')]

    Atrybut zaczyna się ciągiem znaków:
    .//button[starts-with(@class, 'btn')]

    Atrybut kończy się ciągiem znaków - brak xpatha na to

    Coś, co css nie umie:
    Szukanie po tekście między tagami - tekst jest równy ciągi znaków:
    .//button[text()='Submit button']

    Tekst elementu zawiera ciąg znaków:
    ./button[contains(text(), 'Submit')]

    Funkcja not:
    not(@id='button')

     */


    @Test
    public void xPathAttributes() {
        driver.findElement(By.xpath(".//div[@class=\"a-cardui fluid-quad-card fluid-card fluid-quad-image-label fluid-quad-image-label\"]"));
        driver.findElement(By.xpath(".//div[contains(@class, \"fluid-quad-image-label\")]"));
        driver.findElement(By.xpath(".//div[starts-with(@class, 'a-cardui')]"));

    //    <h2 class="a-color-base headline truncate-1line">Shop by Category</h2>

        driver.findElement(By.xpath(".//h2[@class='a-color-base headline truncate-1line']"));
        driver.findElement(By.xpath(".//h2[text()='Shop by Category']"));
        driver.findElement(By.xpath(".//h2[contains(text(), 'Category')]"));

    }

    @Test
    public void cwxPathAttr() {
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-atrybuty-w-xpath/");
        driver.findElement(By.xpath(".//*[text()='Button']"));
        driver.findElement(By.xpath(".//*[contains(@title, 'Button')]"));
        driver.findElement(By.xpath(".//*[@style=\"margin: 5px; background-color: #db456f\"]"));
        driver.findElement(By.xpath(".//*[contains(@style, 'background-color: #db456f')]"));
        driver.findElement(By.xpath(".//*[contains(text(), 'Btn')]"));
        driver.findElement(By.xpath(".//*[contains(@id, 'button-')]"));
        driver.findElement(By.xpath(".//*[@class='button primary test']"));
        driver.findElement(By.xpath(".//*[contains(@class, 'accent')]"));
        driver.findElement(By.xpath(".//*[starts-with(@class, 'button accent') and not(@id='btn-4')]"));
        driver.findElement(By.xpath(".//*[starts-with(@id, 'button-') and not(@id='button-2')]"));

    }

    /*
    Relacje:

    Dzieci:
    .//span[@class='email']/input
    .//div/span[1]/input - szukamy pierwszego spana
    .//div/*[1]/input - szukamy pierwszego dowolnego elementu

    Potomkowie:
    .//div//input[1]
    .//div//input[@id='email']

    Rodzeństwo:
    .//label[@class='required']/following-sibling::input
    .//label[@class='required']/following-sibling::*
    .//label[@class='required']/following-sibling::*[1]

    Coś, czego css nie potrafi:

    Rodzice:
    .//label[@class='required']/.. (nawigacja do rodzica)
    .//label[@class='required']/parent::span
    .//label[@class='required'/parent::*

    Przodkowie:
    .//label[@class='required']/ancestor::*
    .label[@class='required']/ancestor::div


     */


    @Test
    public void xPathRelations() {
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-xpath/");
        driver.findElement(By.xpath(".//div[@class='entry-content']//td[2]/strong/following-sibling::*"));
        driver.findElement(By.xpath(".//strong[text()='Nabywca:']/parent::td"));
        driver.findElement(By.xpath(".//tbody/tr[2]/td[2]"));
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/following-sibling::td[2]"));
    }



}
