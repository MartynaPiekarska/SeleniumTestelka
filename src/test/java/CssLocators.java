import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CssLocators {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to("https://www.amazon.com");

    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }


    /*

    Nazwa taga:
    button

    Id:
    #jakieś-id
    [id='jakieś-id']
    button[id='jakieś-id']
    button#jakieś-id

    Klasa:
    .jakaś-klasa
    [class='jakaś-klasa']
    [class='pierwsza-klasa druga-klasa']
    button[class='jakaś-klasa']
    button.jakaś-klasa

     */

    @Test
    public void cssOperations() {

        driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        driver.findElement(By.cssSelector("[id='twotabsearchtextbox']"));

        driver.findElement(By.cssSelector(".landscape-image"));
        driver.findElement(By.cssSelector("[class='landscape-image']"));
        driver.findElement(By.cssSelector("img[class='landscape-image']"));
        driver.findElement(By.cssSelector("#a-autoid-0-announce"));
        driver.findElement(By.cssSelector("[class='a-cardui quad-label-card card-lite quad-image-label']"));
        driver.findElement(By.cssSelector("[class='nav-cart-icon nav-sprite']"));

    }

    /*

    Atrybut:
    [alt='wartość atrybutu']
    alt, height, width, title, href, style, role, type, name, aria-hidden

    łączenie atrybutów:
    [class='jakaś-klasa'][alt='wartość atrybutu']
    .jakaś-klasa[alt='wartość atrybutu']
    #jakieś-id[title='wartość atrybutu']
    #jakieś-id.jakaś-klasa

     */


    @Test
    public void cssAttribute() {

        driver.findElement(By.cssSelector("#nav-cart.nav-a"));
        driver.findElement(By.cssSelector("#nav-global-location-popover-link.nav-a"));
        driver.findElement(By.cssSelector("[class='a-link-normal see-more truncate-1line'][href=\"/b?node=16225014011\"]"));

     //   driver.findElement(By.cssSelector(".product-image[alt=\"Minecraft - Nintendo Switch\"]"));
        driver.findElement(By.cssSelector(".landscape-image[alt=\"Headsets\"]"));
        driver.findElement(By.cssSelector(".landscape-image[alt=\"Computer mice\"]"));
    }

    /*

    Szukanie ciągu znaków:

    Klasa zawiera podany ciąg znaków:
    [class*='btn']
    [class*='primary btn']

    Klasa zaczyna się podanym ciągiem znaków:
    [class^='btn']
    [class^='bt']

    Klasa kończy się podanym ciągiem znaków:
    [class$='btn-register']
    [class$='register']

    Rozdzielanie spacjami (kończy się spacją):
    [class~='btn']
    [class~='btn-primary']

    Rozdzielanie myślnikami (kończy się myślnikiem):
    [class|='btn btn']

     */


    /*

    Relacje między elementami:

    Dzieci:
    div>button
    div[class='button test']>button.btn
    div:nth-child(3)
    div:nth-last-child(2)
    :last-child()
    div:nth-of-type(2)
    div:nth-last-of-type(2)
    p:first-child
    button:last-child
    button:first-of-type
    button:last-of-type
    p:only-of-type

    Rodzeństwo:
    div+button
    div[class='button test']+button.btn
    div~button (wszystkie buttony)

    Potomkowie:
    div button
    div[class='button test'] button.btn

     */


    @Test
    public void cssRelations() {
        driver.findElement(By.cssSelector("div.a-cardui-body>a[class*='a-link-normal'][href=\"/b?node=16225007011\"]"));
        driver.findElement(By.cssSelector("div.nav-progressive-content>a[href=\"/b/?_encoding=UTF8&ld=AZUSSOA-sell&node=12766669011&ref_=nav_cs_sell_9321d964d1ab428b8d83e204c043fc01\"]"));
        driver.findElement(By.cssSelector("div[class*='a-section']>a[aria-label=\"Toys & Games\"]"));

    }

    /*

    Odwrócenie warunku - not:

    div:not([id='button'])
    div:not(#button)
    div:not([id])
    :not(h2)


     */



}
