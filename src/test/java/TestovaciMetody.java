import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestovaciMetody
{
    /**
     * Metoda similuje čekání
     */
    public static void cekejClassName(int delka, String predmet, ChromeDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, delka);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(predmet)));
    }
    /**
     * Metoda similuje čekání
     */
    public static  void cekejCssSelector(int delka,String predmet, ChromeDriver driver)
    {
        WebDriverWait wait = new WebDriverWait(driver, delka);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(predmet)));
    }
    /**
     * Metoda přihlašuje
     */
    public static void prihlaseni(String adresa, String user,String pass, ChromeDriver driver)
    {
        driver.get(adresa);
        driver.findElement(By.name("username")).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.cssSelector(".btn")).click();
    }
    /**
     * Metoda vytvoři novy projekt
     */
    public static void novyProjekt(String nazev,ChromeDriver driver)
    {
        driver.findElement(By.cssSelector("li:nth-child(4) .title:nth-child(2)")).click();
        driver.findElement(By.className("btn-primary")).click();
        cekejClassName(2,"btn-primary-modal-action",driver);
        driver.findElement(By.id("fields_158")).sendKeys(nazev);
        Select select = new Select(driver.findElement(By.id("fields_156")));
        select.selectByIndex(1);
        driver.findElement(By.cssSelector(".fa-calendar")).click();
        driver.findElement(By.cssSelector("tr > .active")).click();
        driver.findElement(By.className("btn-primary-modal-action")).click();
    }
}
