import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProjektoveTesty {
    private final static String adresa = "https://digitalnizena.cz/rukovoditel/";
    private ChromeDriver driver;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        ChromeOptions cho = new ChromeOptions();
        cho.addArguments("--headless");
        cho.addArguments("start-maximized");
        cho.addArguments("window-size=1200,1100");
        cho.addArguments("--disable-gpu");
        cho.addArguments("--disable-extensions");
        //driver = new ChromeDriver(cho);
        //driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
       //driver.close();
    }


    /**
     * Metoda similuje čekání
     */
    public void cekej(int delka,String predmet)
    {
        WebDriverWait wait = new WebDriverWait(driver, delka);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(predmet)));
    }

    /**
     * Testování vytvoření nového projektu beze jména
     * Nesmí se vytvořit
     */
    @Test
    public void projektBezJmena() {
        //Given
        driver.get(adresa);
        driver.findElement(By.name("username")).sendKeys("rukovoditel");
        driver.findElement(By.name("password")).sendKeys("vse456ru");
        driver.findElement(By.cssSelector(".btn")).click();

        //When
        driver.findElement(By.cssSelector("li:nth-child(4) .title:nth-child(2)")).click();
        driver.findElement(By.className("btn-primary")).click();
        cekej(2,"btn-primary-modal-action");
        driver.findElement(By.className("btn-primary-modal-action")).click();

        //Then
        Assert.assertTrue(driver.findElement(By.id("fields_158-error")).isDisplayed());
    }

    /**
     * Testování úspěšného scénáře vytvoření projektu
     */
    @Test
    public void uspesneVytvoreniProjektu() {
        //Given
        driver.get(adresa);
        //When
        driver.findElement(By.name("username")).sendKeys("kouba");
        driver.findElement(By.name("password")).sendKeys("heslo");
        driver.findElement(By.cssSelector(".btn")).click();
        //Then
        Assert.assertTrue(!driver.getTitle().contains("Dashboard"));
        Assert.assertTrue(driver.findElement(By.cssSelector(".alert")).isDisplayed());
    }
}
