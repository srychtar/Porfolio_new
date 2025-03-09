package pl.coderslab.mystore;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectedProductPage {
    private final WebDriver driver;

    @FindBy(id = "group_1")
    WebElement pickingMsize;
    @FindBy(id = "quantity_wanted")
    WebElement itemsNumber;
    @FindBy(xpath = "//button[@class='btn btn-primary add-to-cart']")
    WebElement addingToBasketBtn;
    @FindBy(xpath = "//a[@class='btn btn-primary' and contains(text(), 'Proceed to checkout')]")
    WebElement proceedToCheckoutBtn;


    public SelectedProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void parameteSetting() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(pickingMsize));
        Select select = new Select(pickingMsize);
        select.selectByValue("2");
        wait.until(ExpectedConditions.elementToBeClickable(itemsNumber));
        String wantedNumber = "5";

        itemsNumber.click();
        itemsNumber.sendKeys(Keys.CONTROL + "a");
        itemsNumber.sendKeys(wantedNumber);

        wait.until(ExpectedConditions.elementToBeClickable(addingToBasketBtn));
        addingToBasketBtn.click();

    }

    public void goToShoppingCart(){
        proceedToCheckoutBtn.click();
        proceedToCheckoutBtn.click();
    }

}
