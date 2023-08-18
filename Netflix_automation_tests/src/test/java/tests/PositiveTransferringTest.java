package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.objects.Home_Page;

import java.time.Duration;

public class PositiveTransferringTest extends TestBase {

    @Test
    public void CorrectTransferToSingInForm() {

        String startUrl = driver.getCurrentUrl();
        Home_Page homePage= new Home_Page();
        homePage.CloseCookieAlert();
        homePage.ClickSignIn();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-form")));
        String redirectedUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(redirectedUrl, startUrl, "Page was not redirected after clicking the button.");
    }
}
