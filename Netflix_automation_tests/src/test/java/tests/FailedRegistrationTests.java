package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.objects.Home_Page;

public class FailedRegistrationTests extends TestBase{

    @Test
    public void PutIncorrectEmailForRegistration(){

        Home_Page homePage= new Home_Page();
        String startUrl = driver.getCurrentUrl();
        homePage.PutEmailForRegistrationForm("blabla");
        homePage.ClickSubmitForRegistrationForm();
        String redirectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(redirectedUrl, startUrl, "Page was redirected after clicking the button.");

    }



}
