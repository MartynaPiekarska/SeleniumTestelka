import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CwCss {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to("https://fakestore.testelka.pl/wyszukiwanie-elementow-poprzez-relacje/");

    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void cwCss() {
        driver.findElement(By.cssSelector("dd#name-element>input#name[name=\"name\"]"));
        driver.findElement(By.cssSelector("div.second-div>#email"));
        driver.findElement(By.cssSelector("div:nth-of-type(2)>input:nth-of-type(1)"));
        driver.findElement(By.cssSelector("div.second-div input:last-child"));
        driver.findElement(By.cssSelector("div.second-div>div.div>input:last-child"));
        driver.findElement(By.cssSelector("div.second-div input#submit"));
        driver.findElement(By.cssSelector("div:not([class='second-div'])>button#submit:last-of-type"));
        driver.findElement(By.cssSelector("div:not([class='second-div'])>button"));

    }

}
