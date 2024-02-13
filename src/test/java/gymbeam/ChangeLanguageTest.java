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

import javax.swing.*;

import static java.lang.Thread.sleep;

public class ChangeLanguageTest {
    WebDriver driver;
    String url = "https://gymbeam.com/";

    @BeforeTest
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        sleep(2000);
        try {
            WebElement allowButton = driver.findElement(By.id("btn-cookie-allow"));
            if (allowButton.isDisplayed()) {
                allowButton.click();
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Nu am gasit elementul");
        }
        sleep(2000);
    }

    @Test
    public void changeLanguage () throws InterruptedException {
        WebElement languageButton = driver.findElement(By.xpath("//*[@id=\"switcher-language-trigger\"]"));
        languageButton.click();
        sleep(2000);

        WebElement languageRO = driver.findElement(By.xpath("//*[@id=\"switcher-language\"]/div/ul/li[4]/a"));
        if (!languageRO.isSelected()) {
            languageRO.click();
            sleep(2000);
        }

        String expected= driver.getCurrentUrl();
        Assert.assertEquals("https://gymbeam.ro/", expected);
        sleep(2000);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}

