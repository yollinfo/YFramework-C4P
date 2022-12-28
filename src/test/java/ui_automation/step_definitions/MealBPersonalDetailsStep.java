package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import ui_automation.pages.MealBPersonalDetailsPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.WaitHelper;

public class MealBPersonalDetailsStep {

    MealBPersonalDetailsPage mealBPersonalDetailsPage = new MealBPersonalDetailsPage();


    @Given("I am on the {string} page")
    public void i_am_on_the_page(String pageName) throws InterruptedException {
        WaitHelper.waitForVisibility(mealBPersonalDetailsPage.tennantNameLabel, 3);
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui-config.properties", "mealb.url")+ pageName);
    }

    @When("I enter mismatching passwords")
    public void i_enter_mismatching_passwords() throws InterruptedException {
        mealBPersonalDetailsPage.passwordInput.sendKeys("Apple12345");
        mealBPersonalDetailsPage.confirmationPasswordInput.sendKeys("Water1234");
        mealBPersonalDetailsPage.updateBtn.click();
    }

    @Then("I get password error message {string}")
    public void i_get_password_error_message(String errorMsg) {
        Assert.assertEquals(mealBPersonalDetailsPage.passwordConfirmationError.getText(), errorMsg);
    }

}
