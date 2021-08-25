import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JavaScript {

    /*

    Scrollowanie:
    js.executeScript("window.scrollBy(0,600)");

    Pobieranie tekstu z ukrytego elementu:
    String text = js.executeScript("return arguments[0].text", element);

    Alerty:
    String js = "alert('Jestem alertem!')";
    js.executeScript(js);

    String js2 = "confirm('Wciśnij button')";
    String js3 = "prompt('Możesz tutaj coś wpisać')";

    getText(), sendKeys("Jakiś tekst"), accept(), dismiss()

     */



    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");

        // wait dla javascriptów
        driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.MILLISECONDS);
        // ile czasu potrzebuję aż skrypty się wykonają? po 1000 TimeoutEx


    }

    @AfterEach
    public void closeAndQuit() {
        driver.quit();
    }

    @Test
    public void exampleTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('Właśnie coś wpisałam w konsolę');");
        String domainName = (String) js.executeScript("return document.domain"); // zwraca domenę, zrzutowana na stringa
    }

    // wykonuje po kolei - czas 564
    @Test
    public void asyncTest() {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Elapsed time: " + elapsedTime);
    }

    // wykonuje wszystko jednocześnie - czas 8
    @Test
    public void syncTest() {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Elapsed time: " + elapsedTime);
    }


    @Test
    public void alertTest() {
        String javaScript = "prompt('Możesz tutaj coś wpisać')";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(javaScript);
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Teeest");
        driver.switchTo().alert().accept();
        js.executeScript(javaScript);
        driver.switchTo().alert().dismiss();
    }





}
