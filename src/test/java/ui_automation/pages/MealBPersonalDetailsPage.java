package ui_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

public class MealBPersonalDetailsPage {

    public MealBPersonalDetailsPage(){
        PageFactory.initElements(Driver.getInstance().getDriver(), this);
    }

    @FindBy(name = "Password")
    public WebElement passwordInput;

    @FindBy(name="PasswordConfirmation")
    public WebElement confirmationPasswordInput;

    @FindBy(id="RegisterButton")
    public WebElement updateBtn;

    @FindBy(id="PasswordConfirmation-error")
    public WebElement passwordConfirmationError;

    @FindBy(css = ".tenant-name-label")
    public WebElement tennantNameLabel;

}
