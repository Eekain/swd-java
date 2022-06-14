package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@ExtendWith(SeleniumExtension.class)
@SeleniumTest
class WebsiteTest {



    @Test
    void testSearch(WebDriver driver){
        driver.get("https://www.python.org");
        driver.findElement(By.id("id-search-field")).click();
        driver.findElement(By.id("id-search-field")).sendKeys("tasting");
        driver.findElement(By.id("submit")).click();
        log.debug("Click on GO button");
        //Then
        String res = driver.findElement(By.cssSelector("h3:nth-child(2)")).getText();
        assertEquals("Results", res);
    }

    @Test
    void testPsf(WebDriver driver){
        driver.get("https://www.python.org");
        driver.findElement(By.linkText("PSF")).click();
        log.debug("Click on PSF menu item");
        assertEquals("Python Software Foundation", driver.getTitle());

    }



}
