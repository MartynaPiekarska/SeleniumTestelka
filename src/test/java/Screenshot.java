import TestHelpers.TestStatus;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Screenshot {

    /*
    Zrzuty ekranu:
    do debugowania, raportowania failowanych asercji

    TakesScreenshot

    Zrzut strony:
    File screenshot = ((TakesScreenshot) driver.getScreenshotAs(OutputType.FILE);

    Zrzut elementu:
    File screenshot = element.getScreenshotAs(OutputType.FILE);

    OutputType: BASE64, BYTE, FILE

    Jak robić testy tylko przy failach?
    API do rozszerzania testów w JUnit

    Test lifecycle:
    BeforeAll, BeforeEach, Test, AfterEach, AfterAll
    Callback lifecycle:
    BeforeAllCallback, BeforeEachCallback, BeforeTestExecutionCallback,
    AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback

    AfterTestExecutionCallback
    void afterTestExecution(ExtensionCentext context) throws Exception
    context.getExecutionException().isPresent();

    klasa pomocnicza w głównym folderze: main/java

     */

    WebDriver driver;

    @RegisterExtension
    TestStatus status = new TestStatus();


    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @AfterEach
    public void driverQuit(TestInfo info) throws IOException {
        if (status.isFailed) {
            System.out.println("Test screenshot is available at: " + takeScreenshot(info));
        }
        driver.quit();
    }


    @Test
    public void screenshotExample() throws IOException {
        driver.navigate().to("https://www.zooniverse.org/");
        driver.findElement(By.cssSelector("button[value='sign-in']")).click();
        driver.findElement(By.cssSelector("input[name=\"login\"]")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys("hasłotestowe");
        driver.findElement(By.cssSelector("form")).submit();

        WebElement userName = driver.findElement(By.cssSelector("span[class='account-bar'] strong"));
        //File userNameScreenshot = userName.getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(userNameScreenshot, new File ("C:\\Users\\wierz\\Desktop\\userNameScreenshot.jpg"));


        // dodawanie screena - zależność w pom do FileUtils
        //File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(screenshot, new File ("C:\\Users\\wierz\\Desktop\\screenshot.jpg"));

        Assertions.assertEquals("MALAMI", driver.findElement(By.cssSelector("span[class='account-bar'] strong")).getText(), "Username is not correct");
    }


    // można wyrzucić metodę robienia screenów poza test żeby się nie powtarzać
    private String takeScreenshot(TestInfo info) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //nazwa pliku - data wykonania
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String path = "C:\\Users\\wierz\\Desktop\\" + info.getDisplayName() + " " + formatter.format(timeNow) + ".jpg";
        FileUtils.copyFile(screenshot, new File (path));
        return path;
    }


}
