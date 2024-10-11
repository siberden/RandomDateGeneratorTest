package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.BrowserDriver;

public class RandomDateGeneratorSteps {
    private WebDriver driver;

    public RandomDateGeneratorSteps() {
        driver = BrowserDriver.getDriver();
    }

    @Given("I open the random date generator page")
    public void i_open_the_random_date_generator_page() {
        driver.get("https://codebeautify.org/generate-random-date");
    }

    @When("I enter {string} in the field")
    public void i_enter_in_the_field(String value) {
        driver.findElement(By.name("value")).sendKeys(value);
    }

    @When("I enter {string} as start date")
    public void i_enter_as_start_date(String startDate) {
        driver.findElement(By.name("start_date")).sendKeys(startDate);
    }

    @When("I enter {string} as end date")
    public void i_enter_as_end_date(String endDate) {
        driver.findElement(By.name("end_date")).sendKeys(endDate);
    }

    @When("I click the generate button")
    public void i_click_the_generate_button() {
        driver.findElement(By.name("generate_button")).click();
    }
}