package gymbeam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class AddToCartTest {
    WebDriver driver;
    String url = "https://gymbeam.ro/catalogsearch/result/index/?product_list_order=reviews_count&q=whey";
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
    @Test (priority = 5)
    public void addToCartTest () throws InterruptedException {

        WebElement cartButton= driver.findElement(By.xpath("//*[@id=\"product-item-info_5075\"]/div[1]/div[2]/div/form/button"));
        cartButton.click();
        sleep(2000);

        WebElement addToCartButton= driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]"));
        addToCartButton.click();
        sleep(2000);
        Assert.assertTrue(addToCartButton.isDisplayed(),"Ați adăugat True Whey - GymBeam în coșul de cumpărături");
        if (addToCartButton.isDisplayed()) {
        }
        sleep(3000);
}

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
