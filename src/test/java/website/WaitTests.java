package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SeleniumTest
public class WaitTests {

    @Test
    void timedLiveAlertClick(WebDriver driver){
        driver.get("http://127.0.0.1:5500/messages/index.html");
        driver.findElement(By.id("liveAlertTimeoutBtn")).click();
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        var alert = wait.until(ExpectedConditions.elementToBeClickable(By.className("alert-dismissible")));
        log.debug("Warning is present and seen");
        assertThat(alert.getText()).startsWith("Nice");


    }
}
