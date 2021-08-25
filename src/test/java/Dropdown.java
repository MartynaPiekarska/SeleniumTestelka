import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dropdown {

    /*
    Klasa select

    WebElement dropdownElement = driver.findElement(selektor);
    Select dropown = new Select(dropdownElement);

    Zaznaczanie:
    dropdown.selectByIndex(0);
    selectByValue("opcja_1");, selectByVisibleText("Opcja pierwsza");

    Pobieranie opcji:
    List <WebElement> options = dropdown.getOptions();
    List<WebElement> selectedOptions - dropdown.getAllSelectedOptions();
    WebELement firstSelectedOption = dropdown.getFirstSelectedOption();

    Odznaczanie:
    dropdown.deselectAll()
    deselectByIndex, ByValue, ByVisibleText ...

    Wielokrotny wybór - sprawdzanie:
    Boolean isMultiple = dropdown.isMultiple();

     */


    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://allegro.pl/");
        driver.manage().addCookie(new Cookie("gdpr_permission_given", "1"));
        driver.navigate().refresh();
    }

    @AfterEach
    public void closeAndQuit() {
        driver.quit();
    }

    @Test
    public void selectElement() {
        WebElement productCategories = driver.findElement(By.cssSelector("[data-role='filters-dropdown-toggle']"));
        Select categoriesDropdown = new Select(productCategories);
        categoriesDropdown.selectByIndex(3);
        categoriesDropdown.selectByValue("/kategoria/kultura-i-rozrywka");
        categoriesDropdown.selectByVisibleText("Zdrowie");

        Boolean isMultiple = categoriesDropdown.isMultiple();
        List<WebElement> options = categoriesDropdown.getOptions();
        List<WebElement> selectedOptions = categoriesDropdown.getAllSelectedOptions();
        WebElement firstSelectedOption = categoriesDropdown.getFirstSelectedOption();
    }

}
