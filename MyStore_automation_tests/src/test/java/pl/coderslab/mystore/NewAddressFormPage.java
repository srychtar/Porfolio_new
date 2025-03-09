package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NewAddressFormPage {
    private final WebDriver driver;

    @FindBy(id = "field-alias")
    WebElement alias;

    @FindBy(id="field-address1")
    WebElement address;

    @FindBy(id="field-city")
    WebElement city;

    @FindBy(id="field-postcode")
    WebElement postalCode;

    @FindBy(id="field-id_country")
    WebElement country;

    @FindBy(id="field-phone")
    WebElement phone;

    @FindBy(css = ".btn.btn-primary.form-control-submit.float-xs-right")
    WebElement save;


    public NewAddressFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillFormAndSave(String sAlias, String sAddress, String sCity, String sPostalCode, String sCountry, String sPhone){
        alias.sendKeys(sAlias);
        address.sendKeys(sAddress);
        city.sendKeys(sCity);
        postalCode.sendKeys(sPostalCode);
        Select select = new Select(country);
        select.selectByVisibleText(sCountry);
        phone.sendKeys(sPhone);
        save.click();
    }

}
