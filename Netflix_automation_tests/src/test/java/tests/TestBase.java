package tests;
import drive.manager.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    @BeforeMethod
    public void beforeTest() {

        driver = DriverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.netflix.com/");
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.disposeDriver();
    }

}
