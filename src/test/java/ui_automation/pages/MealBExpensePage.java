package ui_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class MealBExpensePage {

    public MealBExpensePage(){
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }


    @FindBy(css = ".buttons-colvis")
    public WebElement columnDropdown;

    @FindBy(css = ".buttons-columnVisibility")
    public List<WebElement> columnOptions;
    @FindBy(xpath = "(//button[contains(@class,'add-expense-button')])[2]")
    public WebElement addExpenseDropdown;

    @FindBy(xpath = "(//*[text() = 'Meal & Entertainment'])[4]")
    public WebElement mealAndEntertainmentOption;

    @FindBy(id = "ExpenseDateTime")
    public WebElement calendar;

    @FindBy(id = "Amount")
    public WebElement amountTextField;

    @FindBy(id = "name")
    public WebElement expenseNameTextField;

    @FindBy(id = "BusinessPurpose")
    public WebElement businessPurposeTextField;

    @FindBy(id = "Company")
    public WebElement companyTextField;

    @FindBy(id = "ProjectName")
    public WebElement projectNameTextField;

    @FindBy(xpath = "//*[text()='Save']")
    public WebElement saveBtn;

    @FindBy(id = "expenses-table")
    public WebElement expenseTable;

    public void completeExpenseModal(String date, double amount, String expenseName, String businessPurpose, String company, String projectName) throws InterruptedException {
        calendar.click();
        Thread.sleep(500);
        Driver.getInstance().getDriver().findElement(By.xpath("(//*[@aria-label='"+date+"'])[3]")).click();
        amountTextField.sendKeys(String.valueOf(amount));
        expenseNameTextField.sendKeys(expenseName);
        businessPurposeTextField.sendKeys(businessPurpose);
        companyTextField.sendKeys(company);
        projectNameTextField.sendKeys(projectName);
    }

    public int getRowNumber(String expenseNameFromData) {
        int rowNumber = 1;
        List<WebElement> expenseNames = Driver.getInstance().getDriver().findElements(By.xpath("//table[@id='expenses-table']/tbody/tr/td[2]"));

        for (WebElement expenseName : expenseNames) {

            if (expenseName.equals(expenseNameFromData)) {
                break;
            }
            rowNumber++;

        }

        return rowNumber;
    }

}
