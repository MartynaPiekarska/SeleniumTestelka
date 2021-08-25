import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CwWindow {

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

    @Test
    public void windowSizeAndDimension() {
        driver.manage().window().setSize(new Dimension(854, 480));
        driver.manage().window().setPosition(new Point(445, 30));

        Dimension size = driver.manage().window().getSize();
        Assertions.assertEquals(new Dimension(854, 480), size, "Size incorrect");

        Point position = driver.manage().window().getPosition();
        Assertions.assertEquals(new Point(445, 30), position, "Wrong position");

        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
    }

}
