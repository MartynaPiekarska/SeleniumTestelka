import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waits {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product/grecja-limnos/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();

        wait = new WebDriverWait(driver, 5);


    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void waitExample() {
        driver.findElement(By.cssSelector("button[name=\"add-to-cart\"]")).click();
        driver.findElement(By.cssSelector(".woocommerce-message a[class=\"button wc-forward\"]")).click();
        WebElement quantityField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[id^=\"quantity\"]")));

        quantityField.clear();

        quantityField.sendKeys("2");
        driver.findElement(By.cssSelector("button[value=\"Zaktualizuj koszyk\"]")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class=\"blockUI blockOverlay\"]"), 0));

        String amount = driver.findElement(By.cssSelector("strong .amount")).getText();
        String expected = "6 400,00 zł";
        Assertions.assertEquals(expected, amount, "Total price is not correct");

    }

    @Test
    public void infoOnElement() {
        WebElement element = driver.findElement(By.cssSelector("#masthead"));
        String text = element.getText();
        String attribute = element.getAttribute("role");
        String cssValue = element.getCssValue("background-color");
        String tag = element.getTagName();
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Rectangle locationAndSize = element.getRect();
        boolean isDisplayed = element.isDisplayed();
        boolean isSelected = element.isSelected();
        boolean isEnabled = element.isEnabled();

    }


}