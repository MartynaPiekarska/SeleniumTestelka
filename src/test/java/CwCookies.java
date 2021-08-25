import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.GregorianCalendar;

public class CwCookies {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(7, driver.manage().getCookies().size(), "1 Number of cookies is not what expected");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void cookieOperations() {
        Cookie cookie = new Cookie("test_cookie", "test_value");
        driver.manage().addCookie(cookie);
        Assertions.assertEquals(8, driver.manage().getCookies().size(), "Number of cookies is different than expected");

        driver.manage().getCookieNamed("cookie");
        Assertions.assertEquals("test_cookie", cookie.getName());

        driver.manage().deleteCookie(cookie);
        Assertions.assertEquals(7, driver.manage().getCookies().size(), "Different number");

        driver.manage().deleteCookieNamed("WMF-Last-Access");
        Assertions.assertEquals(6, driver.manage().getCookies().size(), "Diff number");

        Cookie cookieNamed = driver.manage().getCookieNamed("plwikiel-sessionId");
        Cookie expectedCookie = new Cookie("plwikiel-sessionId", "8c2ff213fd635f6023ec", "pl.wikipedia.org", "/",
                new GregorianCalendar(2021, 06, 26).getTime(), false, false);

        Assertions.assertEquals(expectedCookie.getDomain(), cookieNamed.getDomain());
        Assertions.assertEquals(expectedCookie.getPath(), cookieNamed.getPath());
        Assertions.assertEquals(expectedCookie.isHttpOnly(), cookieNamed.isHttpOnly());

    }
}
