package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.objects.Home_Page;

import java.time.Duration;


public class PositiveRegistrationTest extends TestBase {

    @Test
    public void PutCorrectEmailForRegistration(){

        Home_Page homePage= new Home_Page();
        String startUrl = driver.getCurrentUrl();
        homePage.PutEmailForRegistrationForm("blabla@gmail.com");
        homePage.ClickSubmitForRegistrationForm();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("submitBtnContainer")));
        String redirectedUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(redirectedUrl, startUrl, "Page was redirected after clicking the button.");

    }
}
