package StepDefinitions;

import Core.CoreObjects;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GeneralStepDefinitions extends CoreObjects {

    @Given("I open the random date generator page")
    public void i_open_the_random_date_generator_page() {
        page.openWebsite();
    }

    @When("I enter {int} in the count of dates field")
    public void iEnterInTheCountOfDatesField(int count) {
        page.enterCount(count);
        page.clickGenerateButton();
    }

    @When("I enter {double} as count of dates field")
    public void iEnterAsCountOfDatesField(double count) {
        page.enterCount(count);
    }

    @And("I click the {string} button")
    public void i_click_the_generate_button(String button) {
        switch (button.toLowerCase()) {
            case "generate":
                page.clickGenerateButton();
                break;
            case "copy":
                page.clickCopyButton();
                break;
            default:
                throw new IllegalArgumentException("Invalid button name: " + button);
        }
    }

    @And("I enter {string} as start date")
    public void iEnterAsStartDate(String startDate) {
        page.enterStartDate(startDate);
    }

    @And("I enter {string} as end date")
    public void iEnterAsEndDate(String endDate) {
        page.enterEndDate(endDate);
    }

    @Then("I should not see any dates generated")
    public void iShouldNotSeeAnyDatesGenerated() {
        page.verifyNoDatesGenerated();
    }

    @Then("verify that {int} random dates are generated")
    public void verifyThatRandomDatesAreGenerated(int count) {
        page.verifyRandomDatesGenerated(count);
    }

    @Then("verify that the count of dates field only accepts integers")
    public void verifyThatTheCountOfDatesFieldOnlyAcceptsIntegers() {
        page.verifyCountFieldOnlyAcceptsIntegers();
    }

    @And("I select the {string} as the date format")
    public void iSelectTheAsTheDateFormat(String option) {
        page.selectDateFormat(option);
    }

    @Then("verify that the generated date are generated in the given  {string}")
    public void verifyThatTheGeneratedDateAreGeneratedInTheGiven(String format) {
        page.verifyDateFormat(format);
    }
}