package jsonplaceholder.api.tests;

import io.restassured.filter.log.RequestLoggingFilter;
import jsonplaceholder.api.annotations.RequestType;
import jsonplaceholder.api.annotations.ResourceName;
import jsonplaceholder.api.annotations.TestId;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import jsonplaceholder.api.config.EnvironmentSettings;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import java.lang.reflect.Method;

public class TestBase implements ITestListener , EnvironmentSettings {

    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static ExtentSparkReporter sparkReporter;

    @BeforeSuite
    public void configureSettings() {

        // Konfiguracja RestAssured
        RestAssured.baseURI = getUri();
        RestAssured.filters( new ResponseLoggingFilter(), new RequestLoggingFilter());

        // Konfiguracja ExtentReports
        extent = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("test-output/SparkReport.html");
        sparkReporter.config().setDocumentTitle("Tests report");
        sparkReporter.config().setReportName("JsonPlaceholder API tests");
        extent.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();

        // Uzyskiwanie informacji z adnotacji
        ResourceName resourceAnnotation = method.getDeclaringClass().getAnnotation(ResourceName.class);
        String resourceName = resourceAnnotation != null ? resourceAnnotation.value(): "Unknown";

        TestId testIdAnnotation = method.getAnnotation(TestId.class);
        String testId = testIdAnnotation != null ? testIdAnnotation.value() : "Unknown";

        RequestType requestTypeAnnotation = method.getDeclaringClass().getAnnotation(RequestType.class);
        String httpMethod = requestTypeAnnotation != null ? requestTypeAnnotation.value() : "Unknown";

        // Rozpoczęcie testu i przypisanie tagów
        test = extent.createTest("ID: "+testId+" "+result.getMethod().getMethodName())
                .assignAuthor("Sylwia Bajak")
                .assignCategory(resourceName);
        test.info("Starting send "+ httpMethod);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass(MarkupHelper.createLabel("Test passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Zapisanie szczegółów wyjątku w raporcie
        test.fail(result.getThrowable().getMessage());
        test.fail(MarkupHelper.createLabel("Test not passed", ExtentColor.RED));
    }

    @AfterSuite
    public void tearDown() {
        extent.flush(); // Zapewnia zapisanie wszystkich raportów
    }
}

//parallel="classes" thread-count="2"
// parallel="false"