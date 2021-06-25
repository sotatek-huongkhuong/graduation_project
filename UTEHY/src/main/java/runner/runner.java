package runner;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/resources/features",
        glue= {"step_definitions"},
        plugin={"pretty","html:target/HTML/report.html"},
        //tags = "@login or @create",
        publish = true
)
public class runner {

}
