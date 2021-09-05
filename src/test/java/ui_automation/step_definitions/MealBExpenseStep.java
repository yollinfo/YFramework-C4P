package ui_automation.step_definitions;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui_automation.pages.MealBExpensePage;
import ui_automation.utilities.*;
import ui_automation.utilities.Driver;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class MealBExpenseStep {
    MealBExpensePage mealBExpensePage = new MealBExpensePage();

    String date = "08/19/2021", expenseName, businessPurpose, company, projectName;
    double amount;

    @Then("I see below options when clicked on column dropdown")
    public void i_see_below_options_when_clicked_on_column_dropdown(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        mealBExpensePage.columnDropdown.click();
        for(int i=0; i<maps.size(); i++){
            Assert.assertEquals(maps.get(i).get("option"), mealBExpensePage.columnOptions.get(i).getText());
        }
    }

    @And("I can add expense")
    public void iCanAddExpense() throws InterruptedException {
        Faker faker = new Faker();
        mealBExpensePage.addExpenseDropdown.click();
        Thread.sleep(1000);
        mealBExpensePage.mealAndEntertainmentOption.click();
        Thread.sleep(2000);

        String amount = faker.commerce().price();
        String expenseName = faker.commerce().material();

        mealBExpensePage.calendar.click();
        Thread.sleep(2000);
        Driver.getInstance().getDriver().findElement
                (By.xpath("(//*[contains(@class,'picker__day') and contains(text(),'11')])[5]")).click();
        Thread.sleep(2000);
        mealBExpensePage.amountTextField.sendKeys(amount);
        Thread.sleep(5000);
        mealBExpensePage.expenseNameTextField.sendKeys(expenseName);
        Thread.sleep(5000);

    }

    @Then("I navigate to the expense modal window")
    public void iNavigateToTheExpenseModalWindow() throws InterruptedException {
        mealBExpensePage.addExpenseDropdown.click();
        Thread.sleep(1000);
        mealBExpensePage.mealAndEntertainmentOption.click();
        Thread.sleep(2000);
    }

    @Then("I complete all fields of expense modal window")
    public void iCompleteAllFieldsOfExpenseModalWindow() throws Exception {

        // use Faker class to complete expense modal
        Faker faker = new Faker();
//        String amount1 = faker.commerce().price();
        expenseName = faker.harryPotter().character();

        // use Excel file to complete expense modal V2
        String excelPath = System.getProperty("user.dir") + "/src/test/resources/testData/Keywords.xlsx";
        ExcelUtility.setExcelFile(excelPath, "Sheet1");
//        expenseName = ExcelUtility.getCellData(1, 0);
        businessPurpose = ExcelUtility.getCellData(1, 4);
        company = ExcelUtility.getCellData(1, 5);
        projectName = ExcelUtility.getCellData(1, 6);


        mealBExpensePage.completeExpenseModal(date, amount, expenseName, businessPurpose, company, projectName);
        Thread.sleep(2000);
    }

    @Then("I click on save button")
    public void iClickOnSaveButton() throws InterruptedException {
        mealBExpensePage.saveBtn.click();
        Thread.sleep(5000);
        Driver.getInstance().getDriver().findElement(By.xpath("//table[@id='expenses-table']/thead/tr/th[2]")).click();
        Thread.sleep(2000);
        WaitHelper.waitForVisibility(mealBExpensePage.expenseTable, 10);
    }

    @And("I should be able to verify created expense on expense page")
    public void iShouldBeAbleToVerifyCreatedExpenseOnExpensePage() {

    }

    @And("I should be able to verify UI data with DB data")
    public void iShouldBeAbleToVerifyUIDataWithDBData() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = ConfigurationReader.getProperty("ui-config.properties", "mb.database.url");
        String dbUsername = ConfigurationReader.getProperty("ui-config.properties", "mb.database.username");
        String dbPassword = ConfigurationReader.getProperty("ui-config.properties", "mb.database.password");

        Connection connection; Statement statement; ResultSet resultSet;
        String dataFromUI, dataFromDB;
        String query = "select Name from Expenses as expenses\n" +
                "join AbpUserAccounts as users\n" +
                "on users.UserId = expenses.CreatorUserId\n" +
                "WHERE users.UserName = 'elnar' and expenses.DeletionTime is NULL\n" +
                "order by expenses.Name asc";

        connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        int i = 0;

        while (resultSet.next()) {
            List<WebElement> dataElementsFromUI = ui_automation.utilities.Driver.getInstance().getDriver().findElements(By.xpath("//table[@id='expenses-table']/tbody/tr/td[2]"));
//            List<WebElement> dataElementsFromUI = mealBExpensePage.expenseTable.findElements(By.xpath("/tbody/tr/td[2]"));
            dataFromUI = dataElementsFromUI.get(i).getText();
            dataFromDB = resultSet.getString("Name");
            Assert.assertEquals("Row Number " + i + " DB and UI value match verification failed!", dataFromUI, dataFromDB);
            i++;
        }

        DBUtility.closeConnection();
    }

// something something something




        }



