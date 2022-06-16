package website;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SeleniumTest
@Slf4j
public class PopUpTest {

    @SneakyThrows
    //@Test
    @RepeatedTest(15)
    void testTraining(WebDriver driver){
        driver.get("https://training360.com");
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        var modal = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("NewsletterModal"))));
        wait.until(d -> {
                    log.debug("Check opacity");
                    return modal.getCssValue("opacity").equals("1");
                }

        );
        log.debug("Modal has appeared");
        assertTrue(modal.isDisplayed());
        //Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#NewsletterModalCloseButton > span"))).click();
        //modal.findElement(By.id("NewsletterModalCloseButton")).click();
        wait.until(ExpectedConditions.invisibilityOf(modal));
        assertFalse(modal.isDisplayed());


    }
}
