package website;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WebsiteTest {

    WebDriver driver;

    @BeforeAll
    static void initWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get("https://www.python.org");
    }
    @AfterEach
    void quitDriver() {
        driver.quit();
    }

    @Test
    void testSearch(){
        //Given


        //When
        driver.findElement(By.id("id-search-field")).click();
        driver.findElement(By.id("id-search-field")).sendKeys("tasting");
        driver.findElement(By.id("submit")).click();
        //Then
        String res = driver.findElement(By.cssSelector("h3:nth-child(2)")).getText();
        assertEquals("Results", res);
    }

    @Test
    void testPsf(){
        driver.findElement(By.linkText("PSF")).click();
        assertEquals("Python Software Foundation", driver.getTitle());
    }



}
