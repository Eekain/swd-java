package website;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

@SeleniumTest
public class IndexTest {


    @Test
    @DisplayName("Long name, just give it unreadable")
    void testSetBorder(WebDriver driver){
        driver.get("http://127.0.0.1:5500/index.html");
        WebElement input = driver.findElement(By.id("name-input"));
        String value = input.getText();
        if(value.equals("")){
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style['border'] = '3px solid red';", input);

        }

        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("End");
    }

    @SneakyThrows
    @Test
    void gridManipulation(WebDriver driver){
        driver.get("http://127.0.0.1:5500/grid/index.html");

        var cell5 = driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"));
        System.out.println(cell5.getText());
        var cell2 = driver.findElement(with(By.tagName("td")).below(cell5));
        File file = ((TakesScreenshot) driver).getScreenshotAs((OutputType.FILE));
        Files.move(file.toPath(), Path.of("./scheenshot.png"), StandardCopyOption.REPLACE_EXISTING);

        file =(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2)"))).getScreenshotAs(OutputType.FILE);


        assertEquals("2", cell2.getText());
    }
}
