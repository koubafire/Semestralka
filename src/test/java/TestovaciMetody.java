import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
}
