package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui_automation.pages.OhrmEmployeesPage;
import ui_automation.utilities.*;
public class OhrmEmployeesStep {
    OhrmEmployeesPage ohrmEmployeesPage = new OhrmEmployeesPage();
    GridHelper gridHelper = new GridHelper();
    String firstName = "Omar";
    String lastName = "Romero";
    String middleName="M";

    public static final Logger oLog = LogManager.getLogger(SelectHelper.class);

    @Given("I'm on logged in to OrangeHRM as admin")
    public void i_m_on_logged_in_to_OrangeHRM_as_admin() {
        //Go to URL
        Driver.getInstance().getDriver().get(ConfigurationReader.getProperty("ui-config.properties","yollhrm.url"));
        ohrmEmployeesPage.userNameInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties","yollhrm.username"));
        ohrmEmployeesPage.passwordInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties","yollhrm.password"));
        ohrmEmployeesPage.loginBtn.click();
        oLog.info("I have entered login information and then clicked on login button");
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

    @Then("I click on the Add Employee Tab")
    public void iClickOnTheAddEmployeeTab() throws InterruptedException {
        Helper.hover(ohrmEmployeesPage.pImTab);
        Thread.sleep(1000);
        ohrmEmployeesPage.addEmployeeTab.click();
        oLog.info("Clicked on the employee tab");
    }

    @Then("I fill out employee information")
    public void iFillOutEmployeeInformation() {
        ohrmEmployeesPage.firstNameInput.sendKeys(firstName);
        ohrmEmployeesPage.middleNameInput.sendKeys(middleName);
        ohrmEmployeesPage.lastNameInput.sendKeys(lastName);
        oLog.warn("entered full name details");
    }

    @And("I upload an Employee image")
    public void iUploadAnEmployeeImage() {
        ohrmEmployeesPage.uploadEmpPicBtn.sendKeys(System.getProperty("user.dir")+"/src/test/resources/testData/person.png");
    }

    @Then("I click on Save Employee button")
    public void iClickOnSaveEmployeeButton() {
        ohrmEmployeesPage.saveEmpBtn.click();
    }

    @And("I see employee information saved successfully")
    public void iSeeEmployeeInformationSavedSuccessfully() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertEquals(firstName, ohrmEmployeesPage.viewFirstNameField.getAttribute("value"));
    }
}
