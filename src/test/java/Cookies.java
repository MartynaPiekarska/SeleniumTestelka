import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.GregorianCalendar;
import java.util.Set;

public class Cookies {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

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
    public void gettingAndDeletingCookies() {

//        Set<Cookie> cookies = driver.manage().getCookies();
//        Assertions.assertEquals(8, driver.manage().getCookies().size(), "1 Number of cookies is not what expected");
        Cookie cookieSessionId = driver.manage().getCookieNamed("session-id");
        driver.manage().deleteCookieNamed("session-id");
        Assertions.assertEquals(7, driver.manage().getCookies().size(), "2 Number of cookies is not what expected");
        Assertions.assertNull(driver.manage().getCookieNamed("session-id"), "Cookie is not deleted.");

        driver.manage().deleteAllCookies();
        Assertions.assertEquals(0, driver.manage().getCookies().size(), "3 Number of cookies is not what expected");
    }


    @Test
    public void addingAndDeletingCookies() {
        Cookie newCookie = new Cookie("test_cookie", "test_value", ".amazon.com", "/",
                new GregorianCalendar(2022, 11, 31).getTime(), true, true);
        driver.manage().addCookie(newCookie);
        Assertions.assertEquals(9, driver.manage().getCookies().size(), "1 Number of cookies is not what expected");

        Cookie cookie2 = new Cookie("test_cookie2", "test_value2");
        driver.manage().addCookie(cookie2);
        Assertions.assertEquals(10, driver.manage().getCookies().size(), "2 Number of cookies is not what expected");

        driver.manage().deleteCookie(newCookie);
        Assertions.assertEquals(9, driver.manage().getCookies().size(), "3 Number of cookies is not what expected");

    }




}
