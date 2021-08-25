import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Interakcje {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        // timeout - domyślnie 0 - działa w połączeniu z metodami findElement
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // pageload - czekamy na załadowanie strony - default 300 sec
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    }

    @AfterEach
    public void closeAndQuit() {
        driver.quit();
    }


    @Test
    public void timeoutTest() {
        driver.navigate().to("https://fakestore.testelka.pl/product/grecja-limnos/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
        driver.findElement(By.cssSelector("a.add_to_wishlist")).click();
        driver.findElement(By.cssSelector("span.feedback"));
    }


    /*
    Klikanie i wprowadzanie tekstu:

    click()
    driver.findElement(By.cssSelector("selector")).click();
    button, checkbox

    submit()
    driver.findElement(By.cssSelector("selector")).submit();
    Używa się, jeżeli element jest formularzem albo elementem formularza
    po takiej akcji zostanie zatwierdzony

    clear()
    zadziała, jeśli element jest polem tekstowym

    sendKeys()
    driver.findElement(By.cssSelector("selector")).sendKeys("jakiśtekst");
    driver.findElement(By.cssSelector("selector")).sendKeys(Keys.chord(Keys.CONTROL, "a")); - zaznaczanie wszystkiego na stronie ctrl+a
    symuluje wprowadzenie tekstu do elementu, któremu można przypisać jakąś wartość
    można też wysłać ścieżkę do pliku np. przy uploadzie - Selenium działa tylko w przeglądarce,
    nie może poruszać się w systemie


     */

}
