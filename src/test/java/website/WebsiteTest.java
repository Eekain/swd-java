package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@SeleniumTest
class WebsiteTest {

    WebsitePageObject page;
    @BeforeEach
    void init(WebDriver driver){
        page = new WebsitePageObject(driver);
    }

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
//from this on, this uses WebsitePageObject
    @Test
    void testSearchWithWPO(){
        page.go().clickOnCreateSeachLink().sendSearchterm("Java").justSubmit();
        log.debug("Click on GO button");
        //Then
        String res = page.didItWork();
        assertEquals("Results", res);
    }

    @Test
    void testPsfWithWPO(){
        page.go().clickByLinkText("PSF");

        log.debug("Click on PSF menu item");
        assertEquals("Python Software Foundation", page.getTitle());

    }



}
