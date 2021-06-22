package Runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= "Feature/Coach.feature",
		glue="StepDefination",
		dryRun=false,
		monochrome=true,
		plugin= {"html:coach/coachReport",
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
		)

public class StepRunner {
	@AfterClass
	public static void writeExtentReport() 
	{
		Reporter.loadXMLConfig(new File("config/report.xml"));
	}
}
