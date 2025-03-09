package pl.coderslab.mystore;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/mystore-addingAddress.feature", plugin = {"pretty", "html:out.html"})

public class TestRunner1 {
}
