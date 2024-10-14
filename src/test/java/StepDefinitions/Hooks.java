package StepDefinitions;

import Core.CoreObjects;
import Core.Driver;
import Pages.RandomDataGeneratorPage;
import Utilities.CommonMethods;
import Utilities.ConfigurationReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

public class Hooks extends CoreObjects {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    private static boolean setUpIsDone = true;

    @Before(order = 1)
    public void onStart() {
        if (setUpIsDone) {
            log.info("===============================================================================");
            log.info("|     Demo is Starting...");
            log.info("|     Environment : " + ConfigurationReader.get("url"));
            log.info("|     Operating System : " + System.getProperty("os.name"));
            log.info("|     Browser: " + ConfigurationReader.get("browser"));
            log.info("|     Tested by: " + ConfigurationReader.get("qa"));
            log.info("===============================================================================\n");
            setUpIsDone = false;
        }
    }

    @Before(order = 2, value = "not @hookless")
    public void initializeDriver() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        initObjects();
    }

    @Before(order = 3)
    public void onTestStart(Scenario scenario) {
        log.info("===============================================================");
        log.info("|          Scenario Name: " + scenario.getName());
        log.info("===============================================================");
    }

    @AfterStep
    public void onTestFailure(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            CommonMethods.takeScreenshot(scenario.getName());
        }
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }

    public void initObjects() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        page = new RandomDataGeneratorPage();
    }
}
