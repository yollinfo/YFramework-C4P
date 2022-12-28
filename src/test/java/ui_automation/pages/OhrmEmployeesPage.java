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
}
