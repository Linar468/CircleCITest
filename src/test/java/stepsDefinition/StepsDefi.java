package stepsDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StepsDefi {
    WebDriver driver;

    public void start() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void finish() {
        driver.quit();
    }

    @Before
    public void initialisation(){
        start();
    }

    @After
    public void teardown(Scenario scenario){
        if (scenario.isFailed()) {
            attachScreenshot();
        }
        finish();
    }

    public byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @When("I go to the site")
    public void iGoToTheSite() {
        driver.get("https://www.google.com/");
    }

    @Then("I check that title is correct")
    public void iCheckThatTitleIsCorrect() {
        assertEquals("Google", driver.getTitle());
    }

    // mvn clean test
    //mvn allure serve

}
