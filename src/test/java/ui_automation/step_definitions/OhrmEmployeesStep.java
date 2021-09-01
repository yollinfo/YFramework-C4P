package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui_automation.pages.OhrmEmployeesPage;
import ui_automation.utilities.*;
public class OhrmEmployeesStep {
    OhrmEmployeesPage ohrmEmployeesPage = new OhrmEmployeesPage();
    GridHelper gridHelper = new GridHelper();
    @Given("I'm on logged in to OrangeHRM as admin")
    public void i_m_on_logged_in_to_OrangeHRM_as_admin() {
        //Go to URL
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui-config.properties","yollhrm.url"));
        ohrmEmployeesPage.userNameInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties","yollhrm.username"));
        ohrmEmployeesPage.passwordInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties","yollhrm.password"));
        ohrmEmployeesPage.loginBtn.click();
    }
    @Given("I click on the Employee List")
    public void i_click_on_the_Employee_List() {
        Helper.hover(ohrmEmployeesPage.pImTab);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ohrmEmployeesPage.employeeTab.click();
    }
    @Then("I see employee list table")
    public void iSeeEmployeeListTable() {
        Assert.assertTrue(ohrmEmployeesPage.employeeTable.isDisplayed());
    }
}
