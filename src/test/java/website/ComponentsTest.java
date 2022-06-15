package website;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SeleniumTest
@Slf4j
public class ComponentsTest {
    @Test
    void testListSet(WebDriver driver) {
        driver.get("http://127.0.0.1:5555/components/index.html");
//        assertEquals(List.of("One", "Two", "Three", "Four"), driver
//                .findElements(By.tagName("li"))
//                .stream()
//                .map(i -> i.getText())
//                .toList());
        var texts = driver.findElements(By.tagName("li"));
        assertThat(texts)
                .extracting(WebElement::getText)
                .hasSize(4)
                .contains("One", "Three");


    }
    @Test
    void neveketAkarokHallani(WebDriver driver){
        driver.get("http://127.0.0.1:5555/components/index.html");
        var texts = driver.findElements(By.cssSelector("td:nth-child(2)"));

        assertThat(texts)
                .extracting(WebElement::getText)
                .contains("John Doe", "John Doer", "John Deer");
    }

    @Test
    void testConvertToObject(WebDriver driver){
        driver.get("http://127.0.0.1:5555/components/index.html");
        var employees = getEmployeesFromTable(driver);
        log.debug(employees.toString());

        assertThat(employees)
                .extracting(Employee::getName, Employee::getYearOfBirth)
                .contains(tuple("John Deer", 1920));
    }

    List<Employee> getEmployeesFromTable(WebDriver driver){
        driver.get("http://127.0.0.1:5555/components/index.html");
        var rows = driver.findElements(By.tagName("tr"));
        var employees = new ArrayList<Employee>();
        boolean kek = false;
        for(var row: rows){
            if(!kek){
                kek = true;
                continue;
            }
            var cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).getText());
            var name = cells.get(1).getText();
            var yearOfBorth = Integer.parseInt(cells.get(2).getText());
            employees.add(new Employee(id, name, yearOfBorth));
        }
        return employees;
    }
}
