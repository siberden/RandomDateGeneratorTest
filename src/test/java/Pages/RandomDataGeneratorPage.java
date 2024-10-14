package Pages;

import Core.CoreObjects;
import Utilities.ConfigurationReader;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RandomDataGeneratorPage extends CoreObjects {
    private static final Logger log = LoggerFactory.getLogger(RandomDataGeneratorPage.class);

    private static final By dateCount = By.id("count");
    private static final By startDateField = By.id("start");
    private static final By endDateField = By.id("end");
    private static final By generateButton = By.xpath("//button[@onclick='generateRandomDate();']");
    private static final By generatedRandomDateTextArea = By.id("generatedRandomDateTextArea");
    private static final By copyButton = By.xpath("//button[@onclick='copyGeneratedRandomDate();']");

    public void openWebsite() {
        driver.get(ConfigurationReader.get("url"));
        log.info("User in on the Random Data Generator page");
    }

    public void verifyNoDatesGenerated() {
        WebElement datesArea = wait.until(ExpectedConditions.elementToBeClickable(generatedRandomDateTextArea));
        String dates = datesArea.getAttribute("value");
        assertTrue(dates.isEmpty(), "Generated date area should  be empty");
    }

    public void enterCount(int count) {
        driver.findElement(dateCount).clear();
        driver.findElement(dateCount).sendKeys(String.valueOf(count));
        log.info("Entered count: {}", count);
    }

    public void enterCount(double count) {
        driver.findElement(dateCount).clear();
        driver.findElement(dateCount).sendKeys(String.valueOf(count));
        log.info("Entered count: {}", count);
    }

    public void enterStartDate(String startDate) {
        driver.findElement(startDateField).sendKeys(String.valueOf(startDate));
        log.info("Entered start date: {}", startDate);
    }

    public void enterEndDate(String endDate) {
        driver.findElement(endDateField).sendKeys(String.valueOf(endDate));
        log.info("Entered end date: {}", endDate);
    }

    public void clickGenerateButton() {
        driver.findElement(generateButton).click();
        log.info("Clicked on the Generate button");
    }

    public void verifyCountFieldOnlyAcceptsIntegers() {
        String value = driver.findElement(dateCount).getAttribute("value");
        try {
            int intValue = Integer.parseInt(value);
            if (intValue >= 0) {
                log.info("Entered value '{}' is a valid entry.", value);
                assertTrue(true, "The count value should be a positive integer.");
            } else {
                log.error("A negative value '{}' is not a valid entry.", value);
                fail("The count value should be a positive integer.");
            }
        } catch (NumberFormatException e) {
            log.error("The value '{}' is not an integer.", value);
            fail("The count value should be an integer.");
        }
    }

    public void clickCopyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(copyButton)).click();
    }

    public int getGeneratedRandomDatesCount() {
        String dates = driver.findElement(generatedRandomDateTextArea).getAttribute("value");
        assertFalse(dates.isEmpty(), "Generated date area should not be empty");
        String[] datesArr = dates.split("\\n");
        log.info(" \nRandom date generated: {}", Arrays.toString(datesArr));
        return datesArr.length;
    }

    public void verifyRandomDatesGenerated(int count) {
        assertEquals(getGeneratedRandomDatesCount(), count, "Random dates should be generated as expected!!!");
    }


    public void selectDateFormat(String option) {
        Select select = new Select(driver.findElement(By.id("format")));
        select.selectByVisibleText(option);
        log.info("Selected date format: {}",option);
    }

    public void verifyDateFormat(String format) {
        String dates = driver.findElement(generatedRandomDateTextArea).getAttribute("value");
        String date = dates.split("\n")[0];         // Get the first date
        switch (format) {
            case "YYYY-MM-DD hh:mm:ss":
                assertTrue(date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"), "The date format is not as expected");
                break;
            case "MM-DD-YYYY hh:mm:ss":
                Assertions.assertTrue(date.matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}"), "The date format is not as expected");
                break;
            case "...":
                // Add more date formats here
                break;
            default:
                log.error("Invalid date format: {}", format);
                fail("Invalid date format: " + format);
        }
    }
}