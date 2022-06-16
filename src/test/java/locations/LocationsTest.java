package locations;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import website.LocationsPageObject;
import website.SeleniumTest;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

 @SeleniumTest
@Slf4j
public class LocationsTest {
    String location = "Óbuda";
    LocationsPageObject page;
    @BeforeEach
    void init(WebDriver driver){
        page = new LocationsPageObject(driver);
    }
    /*@SneakyThrows
    @RepeatedTest(5)
    void createNewLocation(WebDriver driver) {
        driver.get("http://localhost:8080");
        driver.findElement(By.cssSelector("#create-location-link")).click();
        driver.findElement(By.cssSelector("#location-name")).sendKeys(location);
        driver.findElement(By.cssSelector("#location-coords")).sendKeys("47.497962,19.040435");
        driver.findElement(By.className("btn-primary")).click();
        var wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> {
                    log.debug("Waiting for alert to appear");
                    String res = driver.findElement(By.className("alert-success")).getText();
                    return res.equals("Location has been created");
                }

        );
        driver.findElement(By.cssSelector("#refresh-button")).click();

        //törlések
        assertEquals("Óbuda", driver.findElements(By.cssSelector("tr > td:nth-child(2)")));

       // driver.findElements(By.cssSelector("tr > td:nth-child(2)")).findElements(By.cssSelector("td > button > .btn-danger")).stream().findFirst().get().click();


    }*/

    @Test
    void testCreateWithPageObject(){
        //ne hivatkozzunk WebDrivvewrre, mert a helye a page objectben van
        var name = "Test Location " + UUID.randomUUID();
        page
                .go()
                .clickOnCreateLocationLink()
                .fillForm(name)
                .clickOnCreateButton();


        assertEquals("Location has been created", page.waitForMessageAndGetText());
        Location created = page.waitForLocationAppears(name);

        assertEquals("1, 1", created.getCoords());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/MOCK_DATA.csv", numLinesToSkip = 1)
    void testCreateLocationDDT(String name, String lat, String lon){
        log.debug("Location {}({},{})", name, lat, lon);
        page
                .go()
                .clickOnCreateLocationLink()
                .fillForm(name, lat +","+lon)
                .clickOnCreateButton();
    }

    @Test
     void testEdit(){
        var fixture = new LocationDatabaseFixture();
        fixture.deleteLocations();
        fixture.createLocation("Test Location Name", 10, 10);

    }
}
