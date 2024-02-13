package gymbeam;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class SearchTextboxTest {

    WebDriver driver;
    String url = "https://gymbeam.ro/";

    @BeforeTest
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        sleep(2000);
        try {
            WebElement alertRole = driver.findElement(By.id("btn-cookie-allow"));
            if (alertRole.isDisplayed()) {
                alertRole.click();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Nu am gasit elementul");
        }
        sleep(2000);
    }

    @Test

    public void searchTest () throws InterruptedException {
        WebElement searchTextBoxInput= driver.findElement(By.xpath("//*[@id=\"search\"]"));
        searchTextBoxInput.sendKeys("whey");
        searchTextBoxInput.sendKeys(Keys.RETURN);
        sleep(2000);

        Assert.assertTrue(driver.getTitle().equals("Rezultatul căutării pentru: 'whey'"));
        sleep(2000);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}

