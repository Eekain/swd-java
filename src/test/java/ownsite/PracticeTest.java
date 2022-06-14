package ownsite;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import website.SeleniumExtension;
import website.SeleniumTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SeleniumExtension.class)
@SeleniumTest
public class PracticeTest {

    @Test
    void testSubmit(WebDriver driver){
        driver.get("http://127.0.0.1:5555/index.html");
        driver.findElement(By.cssSelector("#name-input")).click();
        driver.findElement(By.cssSelector("#name-input")).sendKeys("Bobber");
        driver.findElement(By.cssSelector("#submit-button")).click();
        String res = driver.findElement(By.cssSelector("#message-div")).getText();
        assertEquals("Hello Bobber!", res);
    }
}
