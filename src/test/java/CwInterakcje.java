import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CwInterakcje {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        // timeout - domyślnie 0 - działa w połączeniu z metodami findElement
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // pageload - czekamy na załadowanie strony - default 300 sec
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @AfterEach
    public void closeAndQuit() {
        driver.quit();
    }


    @Test
    public void testZooniverse() {
        driver.navigate().to("https://www.zooniverse.org/");
        driver.findElement(By.cssSelector("button[value='sign-in']")).click();
        driver.findElement(By.cssSelector("input[name=\"login\"]")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("hasłotestowe");
    //    driver.findElement(By.cssSelector("button[type='submit']")).submit();
    // submit - na wszystkich elementach formularza można zrobić submit

     //   driver.findElement(By.cssSelector("input[name=\"login\"]")).clear();
     //   driver.findElement(By.cssSelector("input[name=\"login\"]")).sendKeys(Keys.chord(Keys.CONTROL, "a"));

        driver.findElement(By.cssSelector("form")).submit();
        Assertions.assertEquals("MALAMI", driver.findElement(By.cssSelector("span[class='account-bar'] strong")).getText(), "Username is not correct");

    }

    @Test
    public void uploadFiles() {
        // input type file - do wgrywania pliku

        driver.navigate().to("https://gofile.io/uploadFiles");

        WebElement uploadFileInput = driver.findElement(By.cssSelector("input#uploadFile-Input"));
        String expectedFileName = "przewodnik-selektory-css-i-xpath.pdf";
        String path = "C:\\Users\\wierz\\Downloads\\przewodnik-selektory-css-i-xpath.pdf";
        uploadFileInput.sendKeys(path);

      //  String actualFileName = driver.findElement(By.xpath(".//div[@id='titlebar']/div[@id='documentName']")).getText();

        Assertions.assertEquals("Your files have been successfully uploaded", driver.findElement(By.xpath(".//*[contains(text(), 'successfully')]")).getText());
     //   Assertions.assertEquals(expectedFileName, actualFileName, "Name of uploaded file is different than expected one");

    }


    String email = "wierzbicka95@gmail.com";
    String password = "Hapsia006!";


    @Test
    public void fakeStoreLogInPositiveTest() {
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        driver.findElement(By.cssSelector("input#username")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=\"login\"]")).submit();

        String userDisplayName = "wierzbicka95";
      //  String myAccountContent = driver.findElement(By.cssSelector("div[class=\"woocommerce-MyAccount-content\"]>p>strong")).getText();
      //  Assertions.assertTrue(myAccountContent.contains(userDisplayName), "My account page does not contain correct name");


     //   Assertions.assertEquals("wierzbicka95", driver.findElement(By.xpath(".//p/strong[text()=\"wierzbicka95\"]")).getText(), "User is not correct");
    }

    @Test
    public void fakeStoreLogInNegativeTest() {
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        driver.findElement(By.cssSelector("input#username")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys("fff");
        driver.findElement(By.cssSelector("button[name=\"login\"]")).submit();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    //    Assertions.assertEquals("BŁĄD", driver.findElement(By.xpath(".//li/strong[text()=\"BŁĄD\"]")).getText(), "Message is different than expected");
    }



}
