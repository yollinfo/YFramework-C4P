package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

import java.util.List;

public class OhrmEmployeesPage {
    WebDriver driver;
    public OhrmEmployeesPage(){
        driver= Driver.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "txtUsername")
    public WebElement userNameInput;

    @FindBy(id = "txtPassword")
    public WebElement passwordInput;

    @FindBy(id = "btnLogin")
    public WebElement loginBtn;

    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pImTab;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement employeeTab;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr")
    public List<WebElement> employeeRecords;

    @FindBy(id="resultTable")
    public WebElement employeeTable;

    @FindBy(id="menu_pim_addEmployee")
    public WebElement addEmployeeTab;

    @FindBy(id="firstName")
    public WebElement firstNameInput;

    @FindBy(id="lastName")
    public WebElement lastNameInput;

    @FindBy(id="middleName")
    public WebElement middleNameInput;

    @FindBy(id="photofile")
    public WebElement uploadEmpPicBtn;

    @FindBy(id="btnSave")
    public WebElement saveEmpBtn;

    @FindBy(id="personal_txtEmpFirstName")
    public WebElement viewFirstNameField;

}
