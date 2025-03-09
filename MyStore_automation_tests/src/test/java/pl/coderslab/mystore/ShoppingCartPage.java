package pl.coderslab.mystore;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;


public class ShoppingCartPage {
    private final WebDriver driver;

    @FindBy(name = "confirm-addresses")
    WebElement confirmAddressBtn;

    @FindBy(name = "confirmDeliveryOption")
    WebElement confirmDeliveryBtn;

    @FindBy(xpath = "//div[@id='payment-option-1-container']//label[@for='payment-option-1']")
    WebElement payByCheckBtn;

    @FindBy(xpath = "//label[@for='conditions_to_approve[terms-and-conditions]']")
    WebElement approveTermsBtn;

    @FindBy(css = ".btn.btn-primary.center-block")
    WebElement placeOrderBtn;


    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void defaultAddressConfirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(confirmAddressBtn));
        confirmAddressBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeliveryBtn));
        confirmDeliveryBtn.click();
    }


    public void payingByCheckCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(payByCheckBtn));
        payByCheckBtn.click();
    }

    public void approvingTermsAndOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        wait.until(ExpectedConditions.elementToBeClickable(approveTermsBtn));
        approveTermsBtn.click();
        placeOrderBtn.click();

    }

    public void takingScreenshotOfOrderAndPrice() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshotName = new File("screenshot.png");

        try {
            Files.copy(screenshot.toPath(), screenshotName.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
