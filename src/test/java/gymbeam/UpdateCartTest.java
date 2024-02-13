package gymbeam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class UpdateCartTest {
    WebDriver driver;
    String url = "https://gymbeam.ro/checkout/cart/";

    @BeforeTest
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        sleep(2000);
        try {
            WebElement alertRole = driver.findElement(By.id("notice-cookie-block"));
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
    public void updateTest() throws InterruptedException {
        WebElement productitem= driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[1]/a"));
        productitem.click();
        sleep(2000);

        Select se= new Select(driver.findElement(By.xpath("attribute307")));
        se.selectByVisibleText("ciocolată albă");
        sleep(2000);

        WebElement addtoCartButton= driver.findElement(By.id("product-addtocart-button"));
        addtoCartButton.click();
        sleep(2000);

    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
