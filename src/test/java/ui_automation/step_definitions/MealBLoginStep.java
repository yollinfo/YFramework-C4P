package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import ui_automation.pages.MealBDashboardPage;
import ui_automation.pages.MealBLandingPage;
import ui_automation.pages.MealBLoginPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

public class MealBLoginStep {

    MealBLandingPage mealBLandingPage = new MealBLandingPage();
    MealBDashboardPage mealBDashboardPage = new MealBDashboardPage();
    MealBLoginPage mealBLoginPage = new MealBLoginPage();

    @Given("I'm on the MealB landing page")
    public void i_m_on_the_MealB_landing_page() {
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui-config.properties", "mealb.url"));
    }

    @When("I click on the Sign In button")
    public void i_click_on_the_Sign_In_button() {
        mealBLandingPage.signInButton.click();
    }

    @Then("I enter MealB username as {string}")
    public void i_enter_MealB_username_as(String userName) {
        mealBLoginPage.userNameInput.sendKeys(userName);
    }

    @Then("I enter MealB password as {string}")
    public void i_enter_MealB_password_as(String password) {
        mealBLoginPage.passwordInput.sendKeys(password);
    }

    @Then("I click on the Login button")
    public void i_click_on_the_Login_button() {
        mealBLoginPage.loginButton.click();
    }

    @Then("I am successfully logged in as {string}")
    public void i_am_successfully_logged_in(String userFullName) {
        mealBDashboardPage.bannerXbutton.click();
        Assert.assertEquals(mealBDashboardPage.currentUserFullName.getText(),userFullName);
    }

    @When("I login with username as {string} and password as {string}")
    public void i_login_with_username_as_and_password_as(String userName, String password) {
        mealBLoginPage.login(userName, password);
    }

    @Then("I should see error message as {string}")
    public void i_should_see_error_message_as(String errorMsg) {
        Assert.assertEquals(mealBLoginPage.invalidMsgs.get(0).getText(), errorMsg);
    }

}
