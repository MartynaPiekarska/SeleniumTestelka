import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MouseActions {


    /*

    Klikanie myszą:

    Click:
    actions.click();
    actions.click(element);
    actions.doubleClick();
    actions.doubleClick(element);
    actions.contextClick();
    actions.contextClick(element);

    moveByOffset - ruszanie myszką
    x - w poziomie ->
    y - w pionie

    actions.moveByOffset(x,y);

     */


    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        actions = new Actions(driver);
    }

    @AfterEach
    public void closeAndQuit() {
        driver.quit();
    }

    @Test
    public void clickExample() {
        driver.navigate().to("http://jqueryui.com/selectable/#default");

        //actions.moveByOffset(390, 420).click().build().perform();
        driver.switchTo().frame(0);
        List<WebElement> listElements = driver.findElements(By.cssSelector("#selectable>li"));
        WebElement firstElement = listElements.get(0);
        actions.click(firstElement).build().perform();
    }

    @Test
    public void doubleClickExample() {
        driver.navigate().to("https://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
        //actions.moveByOffset(300,230).doubleClick().build().perform();

        WebElement box = driver.findElement(By.cssSelector("div#box"));
        actions.doubleClick(box).build().perform();
    }

    @Test
    public void contextClickExample() {
        driver.navigate().to("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement editOption = driver.findElement(By.cssSelector(".context-menu-icon-edit"));
        //actions.moveByOffset(460, 195).contextClick().click(editOption).build().perform();

        WebElement button = driver.findElement(By.cssSelector(".context-menu-one"));
        actions.contextClick(button).click(editOption).build().perform();

    }


    /*

    Akcje na klawiaturze:

    sendKeys(wysłanie tekstu)
    actions.sendKeys("jakiś tekst albo klawisz modyfikujący");
    actions.sendKeys(element, "jakiś tekst albo klawisz");

    keyDown(przytrzymanie klawisza), keyUp(zwolnienie klawisza)
    actions.keyDown("jakieś klawisze");
    actions.keyDown(element, "jakieś klawisze");

     */

    @Test
    public void sendKeysExample() {
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        WebElement login = driver.findElement(By.cssSelector("#username"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);

        //actions.sendKeys(login, Keys.SHIFT, "testowy user").build().perform();
        // albo
        actions.click(login).sendKeys(Keys.SHIFT, "testowy user").build().perform();
    }

    @Test
    public void pressingKeysExample() {
        driver.navigate().to("http://jqueryui.com/selectable/#default");

        driver.switchTo().frame(0);
        List <WebElement> listItems = driver.findElements(By.cssSelector("li.ui-selectee"));
        actions.keyDown(Keys.CONTROL).click(listItems.get(0)).click(listItems.get(1)).keyUp(Keys.CONTROL)
                .click(listItems.get(3)).build().perform();
    }

    @Test
    public void mouseActionsExercise() {
        driver.navigate().to("https://fakestore.testelka.pl/actions");
        List <WebElement> items = driver.findElements(By.cssSelector(".ui-selectee"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", items.get(0));
        actions.keyDown(Keys.CONTROL).click(items.get(3)).click(items.get(7)).click(items.get(10)).keyUp(Keys.CONTROL).build().perform();
        Assertions.assertAll(
                () -> Assertions.assertTrue(items.get(3).getAttribute("class").contains("ui-selected"), "Item was not selected"),
                () -> Assertions.assertTrue(items.get(7).getAttribute("class").contains("ui-selected"), "Item was not selected"),
        () -> Assertions.assertTrue(items.get(10).getAttribute("class").contains("ui-selected"), "Item was not selected")
        );
    }

    /*

    Jak przesuwać obiekty?

    dragAndDrop:
    dragAndDrop(pierwszyWebElement, drugiWebElement)
    dragAndDropBy(webElement, x, y)

    Kombinacja kilku metod:
    clickAndHold()
    clickAndHold(webElement)
    moveToElement(webElement) - przesuń do elementu webElement
    moveToElement(webElement, x, y)
    release() - puść
    release(webElement)

     */

    @Test
    public void offsetExample() {
        driver.navigate().to("https://marcojakob.github.io/dart-dnd/detection_only/");
        WebElement draggableElement = driver.findElement(By.cssSelector(".draggable"));

        //actions.dragAndDropBy(draggableElement, 20, 20).build().perform();

        // albo: kliknij i trzymaj, przesuń i upuść
        actions.clickAndHold(draggableElement).moveByOffset(20, 20).release().build().perform();
        // release(draggableElement) - można przesunąć w dany element

        // albo: przejdź do elementu(postaw tam mysz), kliknij i trzymaj, przesuń, puść
        //actions.moveToElement(draggableElement).clickAndHold().moveByOffset(20, 20).release().build().perform();
    }

    @Test
    public void toElementExample() {
        driver.navigate().to("https://marcojakob.github.io/dart-dnd/nested_dropzones/");
        WebElement draggableElement = driver.findElement(By.cssSelector(".draggable"));
        WebElement dropElement = driver.findElement(By.cssSelector(".dropzone-inner"));

        //actions.dragAndDrop(draggableElement, dropElement).build().perform(); // z i do

        // albo: złap element, przesuń do innego i upuść
        //actions.clickAndHold(draggableElement).moveToElement(dropElement).release().build().perform();

        // albo: złap element i upuść w miejsce drugiego
        actions.clickAndHold(draggableElement).release(dropElement).build().perform();
    }

    @Test
    public void dragAndDropExercise1() {
        driver.navigate().to("https://fakestore.testelka.pl/actions");
        WebElement draggableElement = driver.findElement(By.cssSelector("div#draggable"));
        WebElement droppableElement = driver.findElement(By.cssSelector("div#droppable"));
        String expectedMessage = "Dropped!";

        actions.dragAndDrop(draggableElement, droppableElement).build().perform();
        Assertions.assertTrue(droppableElement.getAttribute("class").contains("ui-state-highlight"), "Place is not correct");
        Assertions.assertEquals(expectedMessage, droppableElement.getText(), "Message was not changed");

        actions.clickAndHold(draggableElement).moveToElement(droppableElement, 20, 20).build().perform();
        Assertions.assertTrue(droppableElement.getAttribute("class").contains("ui-state-highlight"), "Place is not correct");
    }

    @Test
    public void dragAndDropExercise2() {
        driver.navigate().to("https://fakestore.testelka.pl/actions");
        WebElement draggableElement = driver.findElement(By.cssSelector("div#draggable"));
        WebElement droppableElement = driver.findElement(By.cssSelector("div#droppable"));
        String expectedMessage = "Dropped!";

       // actions.clickAndHold(draggableElement).moveToElement(droppableElement, 20, 20).build().perform();
        actions.dragAndDropBy(draggableElement, 160, 40).build().perform();
        Assertions.assertTrue(droppableElement.getAttribute("class").contains("ui-state-highlight"), "Place is not correct");
        Assertions.assertEquals(expectedMessage, droppableElement.getText(), "Message was not changed");
    }

}
