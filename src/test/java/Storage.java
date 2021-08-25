import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Storage {


    /*

    WebStorage: dla chrome, firefox i opery

    chromeDriver.getLocalStorage(); - permanentnie
    chromeDriver.getSessionStorage(); - tylko podczas trwania sesji np. otwarte okno przeglądarki

    getItem(), keySet(), setItem(), removeItem(), clear(), size()


    WebStorage przez JavaScript
    ((JavascriptExecutor) driver).executeScript("return localStorage.getItem(arguments[0]);", key);

     */

    // Jeśli chcę używać LocalStorage i Session storage,
    // to muszę od razu zainicjować ChromeDriver zamiast WebDriver
    ChromeDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

    }

    @AfterEach
    public void closeAndQuit() {
        driver.quit();
    }

    @Test
    public void storageTest() {

        driver.navigate().to("https://airly.org/map/pl/#52.2781560842,21.0530164616");
        String value = driver.getLocalStorage().getItem("persist:map");

        // żeby nie używać cały czas driver.getLocal..., można skrócić do:
        LocalStorage local = driver.getLocalStorage();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(a->local.size()==7);

        int size = local.size();
        Set<String> keys = local.keySet();
        String removedValue = local.removeItem("persist:map");
        local.setItem("spell", "Alohomora");
        local.clear(); // czyści cały lokal storage
    }

    @Test
    public void storageJs() {
        driver.navigate().to("https://airly.org/map/pl/#52.2781560842,21.0530164616");
        String key = "persist:map";
        String value = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.getItem(arguments[0]);", key);
       // ((JavascriptExecutor) driver).executeScript("return localStorage.setItem(arguments[1]);", "spell", "Alohomora");
        ((JavascriptExecutor) driver).executeScript("return localStorage.removeItem(arguments[0])", key);
        String indexValue = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.key(arguments[0])", 2);
        long size = (long) ((JavascriptExecutor) driver).executeScript("return localStorage.length");
        ((JavascriptExecutor) driver).executeScript("return localStorage.clear()");
    }

    @Test
    public void storageExercise() {
        driver.navigate().to("https://fakestore.testelka.pl/product/fuerteventura-sotavento/");
        By demoStore = By.cssSelector("a[class*=\"dismiss-link\"]");
        driver.findElement(demoStore).click();

        int localSize = driver.getLocalStorage().size();
        Assertions.assertEquals(1, localSize, "Different number of local keys is presented");

        int sessionSize = driver.getSessionStorage().size();
        Assertions.assertEquals(2, sessionSize, "Different number of session keys is presented");

        driver.findElement(By.cssSelector("button[name=\"add-to-cart\"]")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-message>a[class=\"button wc-forward\"]")));

        //albo
        //wait.until(d->driver.getSessionStorage().size()==3);

        Assertions.assertTrue(driver.getSessionStorage().keySet().contains("wc_cart_created"), "wc_cart_created was mot added to Session Storage");

        Set<String> keys = driver.getSessionStorage().keySet();
        String wcFragmentsKey = "";
        for(String key : keys) {
            if(key.contains("\"wc_fragments\"")) {
                wcFragmentsKey = key;
            }
        }
        String removed = driver.getSessionStorage().removeItem(wcFragmentsKey);
        Assertions.assertTrue(removed!=null, "wc_fragment was not removed");
    }
}
