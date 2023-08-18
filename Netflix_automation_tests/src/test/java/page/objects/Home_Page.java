package page.objects;
import drive.manager.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import static org.testng.AssertJUnit.assertEquals;

public class Home_Page {

    @FindBy(id="cookie-disclosure-close")
    private WebElement cookieAlert;

    @FindBy(css ="a[href*='login']" )
    private WebElement SignInWebElement;

    @FindBy(name = "email")
    private WebElement userEmail;

    @FindBy(css = "button[type='submit']")
    private WebElement get_started_button;

    @FindBy(name = "LanguageSelect")
    private WebElement languageWebElement;


    public Home_Page() {
        PageFactory.initElements(DriverManager.getWebDriver(), this);
    }


    public void CloseCookieAlert() {

        cookieAlert.click();

    }

    public void ClickSignIn() {

        SignInWebElement.click();

    }

    public void PutEmailForRegistrationForm(String email) {

        userEmail.sendKeys(email);
        String typeEmailValue = userEmail.getAttribute("value");
        assertEquals(typeEmailValue, email);
    }

    public void ClickSubmitForRegistrationForm(){

        get_started_button.click();

    }

    public void ChoosingLanguage( String DropDownByValue, String expectedLanguage){

        Select languageDropDown= new Select(languageWebElement);
        languageDropDown.selectByValue(DropDownByValue);
        assertEquals(languageDropDown.getFirstSelectedOption().getText(), expectedLanguage);

    }


}
