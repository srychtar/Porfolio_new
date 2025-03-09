package pl.coderslab.mystore;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserAccountMainPage {
    private final WebDriver driver;

    @FindBy(xpath = "//a[@class='account']//span[@class='hidden-sm-down']")
    WebElement userName;

    @FindBy(xpath = "//a[@href='https://courses-presta-prod.visuality.pl/index.php?controller=addresses']")
    WebElement addressesSection;

    @FindBy(className = "ui-autocomplete-input")
    WebElement searchArea;

    public UserAccountMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddress(){
        addressesSection.click();
    }

    public void searchProduct(String productName){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchArea));

        searchArea.clear();
        searchArea.sendKeys(productName);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchArea.sendKeys(Keys.ENTER);
    }



}
