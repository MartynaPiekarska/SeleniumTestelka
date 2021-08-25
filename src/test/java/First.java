import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class First {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void driverQuit() {
        driver.close(); // zamknij okno przeglądarki
        driver.quit(); // zamknij sesję
    }


    @Test
    public void getMethod() {

        driver.navigate().to("http://wikipedia.pl");
        driver.navigate().to("https://nasa.gov");
        driver.navigate().back();
        String wikiTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(wikiTitle, driver.getTitle(), "wrong name: " + wikiTitle);
        driver.navigate().forward();
        String nasaTitle = "NASA";
        Assertions.assertEquals(nasaTitle, driver.getTitle(), "wrong name: " + nasaTitle);

    }


    // przekierowanie adresu

    @Test
    public void getCurrentURL() {
        String googleURL = "https://www.google.pl/";
        driver.navigate().to("https://google.pl");
        Assertions.assertEquals(googleURL, driver.getCurrentUrl(), "wrong url");
    }

    @Test
    public void getTitle() {
        String googleTitle = "Google";
        driver.navigate().to("https://google.pl");
        Assertions.assertEquals(googleTitle, driver.getTitle(), "wrong title");
    }




    @Test
    public void getPageSource() {
        String googleImg = "/images/branding/googleg/1x/googleg_standard_color_128dp.png";
        driver.navigate().to("https://google.pl");
        Assertions.assertTrue(driver.getPageSource().contains(googleImg), "Page source does not contain: " + googleImg);
    }



    @Test
    public void zadanie1() {
        driver.navigate().to("http://wikipedia.pl");
        String wikiTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(wikiTitle, driver.getTitle(), "wrong name: " + wikiTitle);

        String wikiURL = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        Assertions.assertEquals(wikiURL, driver.getCurrentUrl(), "wrong url");

        String wikiSource = "lang=\"pl\"";
        Assertions.assertTrue(driver.getPageSource().contains(wikiSource), "Page source does not contain: " + wikiSource);

        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();

        String wikiESTitle = "Wikipedia, la enciclopedia libre";
        Assertions.assertEquals(wikiESTitle, driver.getTitle(), "wrong name: " + wikiTitle);

        String wikiESURL = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        Assertions.assertEquals(wikiESURL, driver.getCurrentUrl(), "wrong url");

        String wikiESSource = "lang=\"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(wikiESSource), "Page source does not contain: " + wikiSource);
    }



}
