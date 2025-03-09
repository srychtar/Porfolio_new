package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourAddressesPage {
    private final WebDriver driver;

    @FindBy(xpath = "//a[span[text()='Create new address']]")
    private WebElement createNewAddressBtn;

    public YourAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCreateNewAddress(){
        createNewAddressBtn.click();
    }


}
