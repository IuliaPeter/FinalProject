package gymbeam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class SortByTest {
    WebDriver driver;
    String url = "https://gymbeam.ro/catalogsearch/result/?q=whey";

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

    @Test (priority = 4)
    public void sortbyTest () throws InterruptedException {
      WebElement sortButton= driver.findElement(By.id("sorter"));
      sortButton.click();
      sleep(2000);

      Select dropdown= new Select(driver.findElement(By.xpath("//*[@id=\"sorter\"]")));
      dropdown.selectByVisibleText("Număr review-uri");
      sleep(3000);
      if (dropdown.isMultiple()) {
          Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"sorter\"]")).getText(), "Număr review-uri");
      }
      sleep(2000);
    }

        @AfterTest (alwaysRun = true)
        public void tearDown() {
            driver.close();
        }
}
