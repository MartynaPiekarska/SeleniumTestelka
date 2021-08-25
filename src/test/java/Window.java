import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class Window {
    WebDriver driver;
    By cookie = By.cssSelector("#cn-accept-cookie");

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().setSize(new Dimension(1290, 730));
        driver.manage().window().setPosition(new Point(8, 30));


        driver.navigate().to("https://www.amazon.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(8, driver.manage().getCookies().size(), "1 Number of cookies is not what expected");
    }


    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void windowOperations() {
        Point position = driver.manage().window().getPosition();
        Assertions.assertEquals(new Point(8, 30), position, "Pos of the windows is not what expected");
        Dimension size = driver.manage().window().getSize();
        Assertions.assertEquals(new Dimension(1290, 730), size, "Size of the windows is not what expected");

        driver.manage().window().fullscreen();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.manage().window().maximize();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /*

    Nawigacja między oknami:

    getWindowHandle() - pobiera unikatowy identyfikator aktywnego okna
    getWindowHandles() - pobiera dla wszystkich okien
    switchTo().window(windowHandle) - przełącza się na nowe okno, które teraz będzie aktywne

    String parentWindow = driver.getWindowHandle();
    Set<String> windows = driver.getWindowHandles();
    driver.switchTo().window(parentWindow);
     */

    @Test
    public void windowHandlesTest() {
        driver.navigate().to("https://testelka.pl/blog/");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(cookie).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookie));

        driver.findElement(By.cssSelector(".dashicons-video-alt3")).click();
        Set<String> windows = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();
        windows.remove(parentWindow);
        String secondWindow = windows.iterator().next();
        driver.switchTo().window(secondWindow);
        String activeWindow = driver.getWindowHandle();
        //driver.findElement(By.cssSelector("a[title=\"testelka.pl\"]")).click();
        driver.switchTo().window(parentWindow);
    }


}
