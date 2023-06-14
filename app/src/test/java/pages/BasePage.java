package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected static WebDriver driver;
    private static WebDriverWait wait;

    static {
    System.setProperty("webdriver.chrome.driver", "chromedriver");
    //* Aqui estamos creando el driver una sola vez para todas las instancias */
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--window-size=1920,1080");
    driver = new ChromeDriver(chromeOptions);
    wait = new WebDriverWait(driver, 10);
    // el bloque static se ejecuta solo una vez, cuando se carga la clase BasePage. Esto significa que la inicialización de las variables estáticas, como driver y wait, solo se realiza una vez, sin importar cuántas instancias de la clase BasePage se creen.
    }

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    //* Ir hacia al sitio web
    public static void navigateTo(String url) {
    System.out.println(url);
    //* Aqui donde entra en juego Selenium */
    driver.get(url);
    }

    //* Para cerrar el navegador
    public static void closeBrowser() {
    driver.quit();
    }

      //* Método que nos devuelve el web element */
  private WebElement find(String locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
  }
  
  //* Método que nos permite realizar un click en el elemento
  public void clickElement(String locator) {
    find(locator).click();
  }

  public void write(String locator, String textToWrite ) {
    find(locator).clear(); //* Esto significa que vamos limpiar el campo de texto
    find(locator).sendKeys(textToWrite); //* Que es lo que, queremos escribir 
  }

  //* Método para buscar un Drop Down o Select por valor (value) */
  public void selectFromDropDownByValue(String locator, String valueToSelect) {
    Select dropdown = new Select(find(locator));
    dropdown.selectByValue(valueToSelect);
  }

  //* Nos va a devolver un contenido, que estamos buscando dentro de la Grid (tabla)
  public String getValueFromTable(String locator, int row, int column) {
    String cellNeed = locator + "/table/tbody/tr[" + row + "]/td[" + column + "]";
    return find(cellNeed).getText();
  }

  public boolean elementIsDisplayed(String locator) {
    return find(locator).isDisplayed(); //* retorna un verdadero o falso
  }

  public void setValueFileButton(String locator, String stringToSend) {
    String buttonFile = locator;
    find(buttonFile).sendKeys(stringToSend);
  }
 
  public List<WebElement> bringMeAllElements(String locator) {
    return driver.findElements(By.className(locator));
    /* 
    * findElements
    * nos vuelve una lista de web elements
    */
  }

  //* Para buscar un link con un texto y darle click
  public void goToLinkText(String linkText) {
    driver.findElement(By.linkText(linkText)).click();
  }

  //* A CONTINUACION LO QUE CORRESPONDE A LAS VALIDACIONES */
  public String textFromElement(String locator) {
    return find(locator).getText();
    //* del web element vamos a capturar el texto que contenga */
  }
}
