package gymbeam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {
    WebDriver driver;
    String url = "https://www.gymbeam.ro";

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
        sleep(1000);
    }

    @Test
    public void loginTest () throws InterruptedException {
        WebElement authenticateButton= driver.findElement(By.xpath("//*[@id=\"html-body\"]/div[4]/header/div[2]/div[2]/ul/li[2]/label/a"));
        authenticateButton.click();
        sleep(2000);

        WebElement nameInput= driver.findElement(By.id("email"));
        nameInput.sendKeys("iulia_test@yahoo.com");
        sleep(2000);

        WebElement passwordInput= driver.findElement(By.id("pass"));
        passwordInput.sendKeys("QAtesting2024");
        sleep(2000);

        WebElement connectButton= driver.findElement(By.xpath("//*[@id=\"send2\"]"));
        new Actions(driver)
                .scrollToElement(connectButton)
                .perform();
        connectButton.click();
        sleep(2000);

        String actualUrl="https://gymbeam.ro/customer/account/login";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        sleep(2000);

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}
