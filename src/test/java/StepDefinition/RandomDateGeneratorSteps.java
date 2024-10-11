package StepDefinition;

import io.cucumber.java.en.And;
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

    @When("I enter {int} in the field")
    public void i_enter_in_the_field(int value) {
        driver.findElement(By.xpath("//*[@id=\"count\"]")).sendKeys(String.valueOf(value));
    }

    @And("I enter {int} as start date")
    public void i_enter_as_start_date(int startDate) {
        driver.findElement(By.xpath("//*[@id=\"start\"]")).sendKeys(String.valueOf(startDate));
    }

    @And("I enter {int} as end date")
    public void i_enter_as_end_date(int endDate) {
        driver.findElement(By.xpath("//*[@id=\"end\"]")).sendKeys(String.valueOf(endDate));
    }

    @And("I click the generate button")
    public void i_click_the_generate_button() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/section[2]/div/div[2]/button[1]/span")).click();
    }
}