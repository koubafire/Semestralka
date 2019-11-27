import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class ProjektoveTesty {
    private final static String adresa = "https://digitalnizena.cz/rukovoditel/";
    private ChromeDriver driver;

    @Before
    public void init() {
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        //driver = new ChromeDriver();
        ChromeOptions cho = new ChromeOptions();
        cho.addArguments("--headless");
        cho.addArguments("start-maximized");
        cho.addArguments("window-size=1200,1100");
        cho.addArguments("--disable-gpu");
        cho.addArguments("--disable-extensions");
        driver = new ChromeDriver(cho);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
       driver.close();
    }


    /**
     * Metoda similuje čekání
     */
    public void cekejClassName(int delka,String predmet)
    {
        WebDriverWait wait = new WebDriverWait(driver, delka);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(predmet)));
    }

    public void cekejCssSelector(int delka,String predmet)
    {
        WebDriverWait wait = new WebDriverWait(driver, delka);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(predmet)));
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
        cekejClassName(2,"btn-primary-modal-action");
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
        driver.findElement(By.name("username")).sendKeys("rukovoditel");
        driver.findElement(By.name("password")).sendKeys("vse456ru");
        driver.findElement(By.cssSelector(".btn")).click();

        //When
        driver.findElement(By.cssSelector("li:nth-child(4) .title:nth-child(2)")).click();
        driver.findElement(By.className("btn-primary")).click();
        cekejClassName(2,"btn-primary-modal-action");
        driver.findElement(By.id("fields_158")).sendKeys("kouba");
        Select select = new Select(driver.findElement(By.id("fields_156")));
        select.selectByIndex(1);
        driver.findElement(By.cssSelector(".fa-calendar")).click();
        driver.findElement(By.cssSelector("tr > .active")).click();
        driver.findElement(By.className("btn-primary-modal-action")).click();

        //Then
        driver.findElement(By.cssSelector("li:nth-child(4) .title:nth-child(2)")).click();
        cekejCssSelector(3,"[class='table table-striped table-bordered table-hover'] tr");
        List<WebElement> tabulka = driver.findElements(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr"));
        tabulka.remove(0);
        WebElement radek = null;
        for (WebElement row : tabulka)
        {
            List<WebElement> bunka = row.findElements(By.tagName("td"));
            if (bunka.get(4).getText().equals("kouba"))
            {
                radek = row;
                List<WebElement> buttony = row.findElements(By.tagName("a"));
                buttony.get(0).click();
                break;
            }
        }
        Assert.assertTrue(radek != null);
        cekejClassName(2,"btn-primary-modal-action");
        driver.findElement(By.className("btn-primary-modal-action")).click();
        tabulka = driver.findElements(By.cssSelector("[class='table table-striped table-bordered table-hover'] tr"));
        Assert.assertTrue(!tabulka.contains(radek));
    }
}
