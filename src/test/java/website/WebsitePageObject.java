package website;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebsitePageObject {
    private WebDriver driver;

    @FindBy(id = "id-search-field")
    private WebElement searchFieldLink;

    public WebsitePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public WebsitePageObject go() {
        driver.get("https://www.python.org");
        return this;
    }
    public WebsitePageObject clickOnCreateSeachLink() {
        searchFieldLink.click();
        return this;
    }
    public  WebsitePageObject sendSearchterm(String s){
        searchFieldLink.sendKeys(s);
        return this;
    }

    public WebsitePageObject justSubmit(){
        driver.findElement(By.id("submit")).click();
        return this;
    }
    public String didItWork(){
        return driver.findElement(By.cssSelector("h3:nth-child(2)")).getText();
    }

    public WebsitePageObject clickByLinkText(String s){
        driver.findElement(By.linkText(s)).click();
        return this;
    }
    public String getTitle(){
        return driver.getTitle();
    }
}
