import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CwLocators {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");

    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }


    @Test
    public void locatorsOperations() {

    driver.findElement(By.className("search-field"));
    driver.findElement(By.name("username"));
    driver.findElement(By.id("password"));
    driver.findElement(By.name("login"));
    driver.findElement(By.name("rememberme"));

        driver.findElement(By.partialLinkText("Nie pamiętasz hasła?"));
    driver.findElement(By.partialLinkText("pamiętasz"));
    driver.findElement(By.linkText("Żeglarstwo"));






    }

}
