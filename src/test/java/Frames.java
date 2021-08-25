import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Frames {

    /*

    <iframe src="URL"></iframe>

    Przełączenie się na kontekst ramki:
    driver.switchTo().frame(index_of_frame);
    ... .frame(name_of_frame), (id_of_frame), (frame_as_webElement)

    Powrót do głównej ramki:
    driver.switchTo().defaultContent();
    driver.switchTo().parentFrame();

     */

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-ramek/");

    }

    @AfterEach
    public void closeAndQuit() {
        driver.quit();
    }


    // nie działa, na stronie nasa brak ramki
//    @Test
//    public void frameExamples() {
//        driver.navigate().to("https://www.nasa.gov/");
//        driver.switchTo().frame("twitter-widget-0");
//        //driver.switchTo().frame(0); - gdy jest kilka ramek na stronie, a my chcemy wybrać pierwszą
//        driver.findElement(By.cssSelector("a[data-scribr*='twitter_url']"));
//        driver.switchTo().defaultContent();
//        //driver.switchTo().parentFrame(); - ramka wyżej
//        driver.findElement(By.cssSelector("div.navbar-header>a.logo"));
//    }

    @Test
    public void frameExercise1() {

        driver.switchTo().frame("main-frame");
        WebElement mainPage = driver.findElement(By.cssSelector("input[name=\"main-page\"]"));
        boolean isEnabled = mainPage.isEnabled();
        Assertions.assertFalse(isEnabled);
    }

    @Test
    public void frameExercise2() {
        driver.switchTo().frame("main-frame");
        driver.switchTo().frame(0);
        WebElement image = driver.findElement(By.xpath(".//img[@alt='Wakacje']/.."));
        Assertions.assertEquals("https://fakestore.testelka.pl/", image.getAttribute("href"));
    }


    @Test
    public void frameExercise3() {
        driver.switchTo().frame("main-frame");
        driver.switchTo().frame(0);
        driver.switchTo().frame(0);
        WebElement mainPage2 = driver.findElement(By.cssSelector("div[class=\"entry-content\"] a[href=\"https://fakestore.testelka.pl/\"]"));
        boolean isEnabled2 = mainPage2.isEnabled();
        Assertions.assertTrue(isEnabled2);

        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        WebElement climbing = driver.findElement(By.cssSelector("a[value=\"wspinaczka\"]"));
        climbing.click();
        WebElement image2 = driver.findElement(By.cssSelector("img[class=\"custom-logo\"]"));
        boolean isDisplayed = image2.isDisplayed();
        Assertions.assertTrue(isDisplayed);
    }

}
