package pl.coderslab.mystore;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class MyStoreSteps {


    WebDriver driver;
    UserAccountMainPage userAccountMainPage;
    YourAddressesPage yourAddressesPage;
    NewAddressFormPage newAddressFormPage;
    ProductsPage productsPage;
    SelectedProductPage selectedProductPage;
    ShoppingCartPage shoppingCartPage;


    @Given("I'm logged in to page using {string} and {string}")
    public void openMystoreLogInPage(String email, String password) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(email, password);
        userAccountMainPage = new UserAccountMainPage(driver);
    }


    @When("I create new address {string} {string} {string} {string} {string} and {string}")
    public void createNewAddress(String alias, String address, String city, String postalCode, String country, String phone) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='account']//span[@class='hidden-sm-down']")));

        userAccountMainPage.clickAddress();
        yourAddressesPage = new YourAddressesPage(driver);
        yourAddressesPage.clickCreateNewAddress();
        newAddressFormPage = new NewAddressFormPage(driver);
        newAddressFormPage.fillFormAndSave(alias, address, city, postalCode, country, phone);

    }

    @Then("I can see success message with text {string}")
    public void iCanSeeSuccessMessageWithText(String expectedText) {
        WebElement alert = driver.findElement(By.xpath("//article[@class='alert alert-success']//li"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(alert));
        Assert.assertTrue("Alert should be displayed", alert.isDisplayed());
        Assert.assertEquals(expectedText, alert.getText());
    }

    @Then("I can see order success message with text {string}")
    public void iCanSeeOrderSuccessMessageWithTextString(String expectedText) {
        WebElement alert = driver.findElement(By.cssSelector("h3.h1.card-title"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(alert));
        Assert.assertTrue("Alert should be displayed", alert.isDisplayed());
        Assert.assertEquals(expectedText, alert.getText().replaceAll("[^\\x00-\\x7F]", ""));

    }




    @And("I close shop browser")
    public void iCloseShopBrowser() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @And("I can see correct entered information {string} {string} {string} {string} {string} and {string}")
    public void iCanSeeCorrectEnteredInformation(String alias, String address, String city, String postalCode, String country, String phone) {

        WebElement addressElement = driver.findElement(By.id("address-18292"));
        String addressText = addressElement.getText().trim();

        boolean isAliasPresent = addressText.contains(alias);
        boolean isAddressPresent = addressText.contains(address);
        boolean isCityPresent = addressText.contains(city);
        boolean isPostalCodePresent = addressText.contains(postalCode);
        boolean isCountryPresent = addressText.contains(country);
        boolean isPhonePresent = addressText.contains(phone);

        if (isAliasPresent && isAddressPresent && isCityPresent && isPostalCodePresent && isCountryPresent && isPhonePresent) {
            System.out.println("Test PASSED: Wszystkie elementy adresu są obecne.");
        } else {
            System.out.println("Test FAILED: Niektóre elementy adresu są niepoprawne.");
            System.out.println("Oczekiwane wartości:");
            System.out.println("Alias: " + alias);
            System.out.println("Address: " + address);
            System.out.println("City: " + city);
            System.out.println("Postal Code: " + postalCode);
            System.out.println("Country: " + country);
            System.out.println("Phone: " + phone);
            System.out.println("\nZnaleziony adres:");
            System.out.println(addressText);
        }

    }


    @And("I search and pick {string}")
    public void iSearchAndPick(String productName) {
        userAccountMainPage.searchProduct(productName);
        productsPage = new ProductsPage(driver);
        productsPage.pickProduct();
        selectedProductPage = new SelectedProductPage(driver);


    }

    @And("I set parameters and add to cart")
    public void iSetParametersAndAddToCart() {
        selectedProductPage.parameteSetting();
        selectedProductPage.goToShoppingCart();
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @When("I set order details and make order")
    public void iSetOrderDetailsAndMakeOrder() {
        shoppingCartPage.defaultAddressConfirmation();
        shoppingCartPage.payingByCheckCheckbox();
        shoppingCartPage.approvingTermsAndOrder();

    }

    @And("I can do screenshot of the results")
    public void iCanDoScreenshotOfTheResults() {
        shoppingCartPage.takingScreenshotOfOrderAndPrice();
    }


}

